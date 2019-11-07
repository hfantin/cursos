import { Negociacoes, Negociacao} from '../domain';
import { NegociacoesView, MensagemView, Mensagem, DateConverter }  from '../ui';
import { getNegociacaoDao, Bind, getExceptionMessage, debounce, controller, bindEvent } from '../util';


@controller('#data', '#quantidade', '#valor')
export class NegociacaoController {

    constructor(_inputData, _inputQuantidade, _inputValor) {

        Object.assign(this, { _inputData, _inputQuantidade, _inputValor });

        this._negociacoes = new Bind(
            new Negociacoes(), 
            new NegociacoesView('#negociacoes'),
            'adiciona', 'esvazia'
        );

        this._mensagem = new Bind(
            new Mensagem(), 
            new MensagemView('#mensagemView'),
            'texto'
        );
        // this._service = new NegociacaoService();  

        this._init();

        /* solucao antiga movida para Proxy.js
        this._negociacoes = new Proxy(new Negociacoes(), {
           
            get(target, prop, receiver) {
                
                // quando o javascript chama um método, ele realiza um get para obter uma
                // referencia e depois um apply pra passar os parametros, por isso verificamos
                // se a target[prop] é do tipo function(por exemplo, negociacaoController.esvazia).
                // Devemos também verificar qual funcao queremos interceptar, por isso usamos includes()
                
                if(typeof(target[prop]) == typeof(Function) && ['adiciona', 'esvazia']
                    .includes(prop)) {
                        // retorna uma nova funcao com o contexto dinamico antes que o apply
                        // seja aplicado por padrao pelo interpretador chamado.
                        // essa function substitui o metodo original antes que o apply enre em ação 
                        return function() { // arguments é uma variavel implícita que dá acesso aos parametros, mesmo que eles não sejam explicitos
                            console.log(`"${prop}" disparou a armadilha - target=${JSON.stringify(target)} - arguments=${JSON.stringify(arguments)}`);
                            target[prop].apply(target, arguments);
                            self._negociacoesView.update(target);
                        }

                } else {

                    return target[prop];
                }
            }
        });

        this._negociacoesView = new NegociacoesView('#negociacoes');
        this._negociacoesView.update(this._negociacoes);

        this._mensagem = new Mensagem();
        this._mensagemView = new MensagemView('#mensagemView');
        this._mensagemView.update(this._mensagem);
        */
    }

    async _init() {
        try {
            const dao = await getNegociacaoDao();
            const negociacoes = await dao.listaTodos();
            negociacoes.forEach(negociacao => this._negociacoes.adiciona(negociacao));
        } catch (err) {
            this._mensagem.texto = getExceptionMessage(erro)
        }

    }

    /*
    adicionaAntigo(event) { // antes do capitulo 12
        event.preventDefault();
        this._negociacoes.adiciona(this._criaNegociacao());
        this._mensagem.texto = 'Negociação adicionada com sucesso';
        // this._mensagemView.update(this._mensagem); --> não precisa mais, foi pra dentro de Bind.js
        this._limpaFormulario();
    }
    */

    @bindEvent('submit', '.form')
    async adiciona(event) {
        try {
            event.preventDefault();
            const negociacao = this._criaNegociacao();
            const dao = await getNegociacaoDao();
            await dao.adiciona(negociacao);
            this._negociacoes.adiciona(negociacao);
            this._mensagem.texto = 'Negociação adicionada com sucesso';
            this._limpaFormulario();

        } catch (err) {
            this._mensagem.texto  = getExceptionMessage(err);
        }
    }

    _limpaFormulario() {
        this._inputData.value = '';
        this._inputQuantidade.value = 1;
        this._inputValor.value = 0.0
        this._inputData.focus();
    }

    _criaNegociacao() {
        return new Negociacao(DateConverter.paraData(this._inputData.value), parseInt(this._inputQuantidade.value), parseFloat(this._inputValor.value));
    }

    apaga() {
        this._negociacoes.esvazia();
        this._mensagem.texto = 'Negociações apagadas com sucesso';
        // this._mensagemView.update(this._mensagem); --> não precisa mais, foi pra dentro de Bind.js
    }
    
    @bindEvent('click', '#botao-importa')
    @debounce
    async importaNegociacoes() {

        try {
            const { NegociacaoService } = await import('../domain/negociacao/NegociacaoService');
            const service = new NegociacaoService();
            const negociacoes = await service.obtemNegociacoesDoPeriodo();
            // console.log(negociacoes);
            negociacoes.filter(novaNegociacao =>

                !this._negociacoes.paraArray().some(negociacaoExistente =>
                    novaNegociacao.equals(negociacaoExistente)))

                .forEach(negociacao => this._negociacoes.adiciona(negociacao));
            this._mensagem.texto = 'Negociações do período importadas com sucesso';
        } catch (err) {
            this._mensagem.texto  = getExceptionMessage(err);
        }
    }

    @bindEvent('click', '#botao-apaga')
    async apaga() {

        try {
            const dao = await getNegociacaoDao();
            await dao.apagaTodos();
            this._negociacoes.esvazia();
            this._mensagem.texto = 'Negociações apagadas com sucesso';
        } catch (err) {
            this._mensagem.texto  = getExceptionMessage(err);
        }
    }
    
    /*
    importaNegociacoes() {

        this._service
            .obtemNegociacoesDoPeriodo()
            .then(negociacoes => {

                negociacoes.filter(novaNegociacao =>

                    !this._negociacoes.paraArray().some(negociacaoExistente =>

                        novaNegociacao.equals(negociacaoExistente)))

                    .forEach(negociacao => this._negociacoes.adiciona(negociacao));

                this._mensagem.texto = 'Negociações do período importadas com sucesso';
            })
            .catch(err => this._mensagem.texto = err);
    }

    apaga() {

        getNegociacaoDao()
        .then(dao => dao.apagaTodos())
        .then(() => {
            this._negociacoes.esvazia();
            this._mensagem.texto = 'Negociações apagadas com sucesso';
        })
        .catch(err => this._mensagem.texto = err);
    } 
    */
    
}