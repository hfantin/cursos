import React, { Component } from 'react';
import InputCustomizado from './InputCustomizado';
import BotaoSubmitCustomizado from './BotaoSubmitCustomizado';
import PubSub from 'pubsub-js';
import TratadorErros from './TratadorErros';


const uri = 'https://cdc-react.herokuapp.com/api/autores';

class FormularioAutor extends Component {

    constructor() {
        super();
        this.state = { nome: '', email: '', senha: '' };
        this.gravaAutor = this.gravaAutor.bind(this);
    }

    salvaAlteracao(nomeInput,evento){
        this.setState({[nomeInput] : evento.target.value});   
    }

    async gravaAutor(evento) {
        evento.preventDefault();
        PubSub.publish("limpa-erros", {});

        const requestInfo = this.getPostRequestInfo({
            nome: this.state.nome,
            email: this.state.email,
            senha: this.state.senha
        });
        try {
            const response = await fetch(uri, requestInfo);
            const json = await response.json();
            if(json.status === 400) {
                new TratadorErros().publicaErros(json);
            }else{
                PubSub.publish('atualiza-lista-autores', json);
                this.setState({ nome: '', email: '', senha: '' });
            }
        } catch (e) {
            console.error(e);
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
                <form className="pure-form pure-form-aligned" onSubmit={this.gravaAutor} method="post">
                    <InputCustomizado id="nome" type="text" name="nome" value={this.state.nome} onChange={this.salvaAlteracao.bind(this, 'nome')} label="Nome" />
                    <InputCustomizado id="email" type="email" name="email" value={this.state.email} onChange={this.salvaAlteracao.bind(this, 'email')} label="Email" />
                    <InputCustomizado id="senha" type="password" name="senha" value={this.state.senha} onChange={this.salvaAlteracao.bind(this, 'senha')} label="Senha" />
                    <BotaoSubmitCustomizado label="Gravar" />
                </form>
            </div>
        );
    }
}

class TabelaAutores extends Component {

    render() {
        return (
            <div>
                <table className="pure-table">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>email</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.props.lista.map(item => {
                                return (
                                    <tr key={item.id}>
                                        <td>{item.nome}</td>
                                        <td>{item.email}</td>
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

export default class AutorBox extends Component {

    constructor() {
        super();
        this.state = { lista: [] };
    }

    componentDidMount() {
        this.consultaAutores();
        PubSub.subscribe('atualiza-lista-autores', (_, novaLista) => {
            this.setState({ lista: novaLista });
        });
    }

    componentWillUnmount(){
        PubSub.unsubscribe('atualiza-lista-autores');
    }

    async consultaAutores() {
        try {
            const response = await fetch(uri);
            const json = await response.json();
            if(json.status && json.status !== 200) {
                console.info('json: ' + JSON.stringify(json));
            }else{
                this.setState({ lista: json });
            }

            
        } catch (e) {
            console.error('erro: ' + e);
        }
    }

    render() {
        return (
            <div>
                <div className="header">
                <h1>Cadastro de autores</h1>
                </div>
                <div className="content" id="content">                            
                    <FormularioAutor/>
                    <TabelaAutores lista={this.state.lista}/>        
                </div>      
            </div>
        );
    }
}