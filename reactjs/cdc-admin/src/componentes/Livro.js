import React, { Component } from 'react';
import InputCustomizado from './InputCustomizado';
import SelectCustomizado from './SelectCustomizado';
import BotaoSubmitCustomizado from './BotaoSubmitCustomizado';
import PubSub from 'pubsub-js';
import TratadorErros from './TratadorErros';

const uriLivros = 'https://cdc-react.herokuapp.com/api/livros';
const uriAutores = 'https://cdc-react.herokuapp.com/api/autores';

class FormularioLivro extends Component {

    constructor() {
        super();
        this.state = { titulo: '', preco: '', autorId: '' };
        this.gravaLivro = this.gravaLivro.bind(this);
    }

    salvaAlteracao(nomeInput,evento){
        this.setState({[nomeInput] : evento.target.value});   
    }

    async gravaLivro(evento) {
        evento.preventDefault();
        PubSub.publish("limpa-erros", {});

        const requestInfo = this.getPostRequestInfo({
            titulo: this.state.titulo,
            preco: this.state.preco,
            autorId: this.state.autorId
        });
        try {
            const response = await fetch(uriLivros, requestInfo);
            const json = await response.json();
            if(json.status === 400) {
                new TratadorErros().publicaErros(json);
            }else{
                PubSub.publish('atualiza-lista-livros', json);
                this.setState({ titulo: '', preco: '', autorId: '' });
            }
        } catch (e) {
            console.error('erro ao gravar livro: ' + e);
        }
    }

    getPostRequestInfo(dados){
        return {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        };
    }

   
    render() {
        return (
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned" onSubmit={this.gravaLivro} method="post">
                    <InputCustomizado id="titulo" type="text" name="titulo" value={this.state.titulo} onChange={this.salvaAlteracao.bind(this, 'titulo')} label="Titulo" />
                    <InputCustomizado id="preco" type="text" name="preco" value={this.state.preco} onChange={this.salvaAlteracao.bind(this, 'preco')} label="Preco" />
                    <SelectCustomizado id="autorId" name="autorId" value={this.state.autorId} onChange={this.salvaAlteracao.bind(this, 'autorId')} label="Autor" lista={this.props.autores}/>
                    <BotaoSubmitCustomizado label="Gravar" />
                </form>
            </div>
        );
    }
}

class TabelaLivros extends Component {

    render() {
        return (
            <div>
                <table className="pure-table">
                    <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Preço</th>
                            <th>Autor</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.props.livros.map(item => {
                                return (
                                    <tr key={item.id}>
                                        <td>{item.titulo}</td>
                                        <td>{item.preco}</td>
                                        <td>{item.autor.nome}</td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default class LivroBox extends Component {

    constructor() {
        super();
        this.state = { livros: [], autores: [] };
    }

    componentDidMount() {
        this.consultaLivros();
        this.consultaAutores();
        PubSub.subscribe('atualiza-lista-livros', (_, novaLista) => { 
            this.setState({ livros: novaLista });
        });
    }

    componentWillUnmount(){
        PubSub.unsubscribe('atualiza-lista-livros');
    }

    async consultaLivros() {
        try {
            const response = await fetch(uriLivros);
            const json = await response.json();
            this.setState({ livros: json });
        } catch (e) {
            console.error('erro: ' + e);
        }
    }

    async consultaAutores() {
        try {
            const response = await fetch(uriAutores);
            const json = await response.json();
            this.setState({ autores: json });
        } catch (e) {
            console.error('erro: ' + e);
        }
    }

    render() {
        return (
            <div>
                <div className="header">
                <h1>Cadastro de livros</h1>
                </div>
                <div className="content" id="content">                            
                    <FormularioLivro autores={this.state.autores}/>
                    <TabelaLivros livros={this.state.livros}/>
                </div>      
            </div>
        );
    }
}