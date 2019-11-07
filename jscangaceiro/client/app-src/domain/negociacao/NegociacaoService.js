import { HttpService } from '../../util/HttpService';
import { ApplicationException } from '../../util/ApplicationException';
import { Negociacao } from './Negociacao';

export class NegociacaoService {

    constructor() {

        this._http = new HttpService();
    }


    async obtemNegociacoesDaSemanaTeste() {
        try { 
            const dados = await this._http.get(`${SERVICE_URL}/negociacoes/semana`);
            console.log('dados=' + JSON.stringify(dados)); 
            return dados.map(objeto => new Negociacao(new Date(objeto.data), objeto.quantidade, objeto.valor));
        } catch(e) {
            throw new ApplicationException('Não foi possível obter as negociações da semana teste');
        }
    }

    obtemNegociacoesDaSemana() {
    
        return this._http
            .get(`${SERVICE_URL}/negociacoes/semana`)
            .then(
                dados => 
                    dados.map(objeto => 
                        new Negociacao(new Date(objeto.data), objeto.quantidade, objeto.valor))
                ,
                err => {

                    throw new ApplicationException('Não foi possível obter as negociações da semana');
                }
            );
    }

    obtemNegociacoesDaSemanaAnterior() {
        
        return this._http
            .get(`${SERVICE_URL}/negociacoes/anterior`)
            .then(
                dados => dados.map(objeto =>
                    new Negociacao(new Date(objeto.data), objeto.quantidade, objeto.valor))
                ,
                err => {
                    
                    throw new ApplicationException('Não foi possível obter as negociações da semana anterior');
                }
            );
    }

    obtemNegociacoesDaSemanaRetrasada() {
        
        return this._http
            .get(`${SERVICE_URL}/negociacoes/retrasada`)
            .then(
                dados => dados.map(objeto =>
                    new Negociacao(new Date(objeto.data), objeto.quantidade, objeto.valor))
                ,
                err => {
                    throw new ApplicationException('Não foi possível obter as negociações da semana retrasada');
                }
            );
    }  

    async obtemNegociacoesDoPeriodo() {
        try {
            let periodo = await Promise.all([
                this.obtemNegociacoesDaSemana(),
                this.obtemNegociacoesDaSemanaAnterior(),
                this.obtemNegociacoesDaSemanaRetrasada()
            ]);
            return periodo
                .reduce((novoArray, item) => novoArray.concat(item), [])
                .sort((a, b) => b.data.getTime() - a.data.getTime());

        } catch (err) {
            console.log(err);
            throw new ApplicationException('Não foi possível obter as negociações do período')
        };

        // return Promise.all([
        //     this.obtemNegociacoesDaSemana(),
        //     // this.obtemNegociacoesDaSemanaTeste(),
        //     this.obtemNegociacoesDaSemanaAnterior(),
        //     this.obtemNegociacoesDaSemanaRetrasada()
        // ])
        // .then(periodo => periodo
        //     .reduce((novoArray, item) => novoArray.concat(item), [])
        //     .sort((a, b) => b.data.getTime() - a.data.getTime())
        // )
        // .catch(err => {
        //     console.log(err);
        //     throw new ApplicationException('Não foi possível obter as negociações do período')
        // });
    }             

    // com callback 

    obterNegociacoesDaSemanaOld(cb) {

        const xhr = new XMLHttpRequest();
        xhr.open('GET', `${SERVICE_URL}/negociacoes/semana`);

        xhr.onreadystatechange = () => {
        
            if(xhr.readyState == 4) {

                if(xhr.status == 200) {
                    
                    const negociacoes = JSON
                        .parse(xhr.responseText)
                        .map(objeto => new Negociacao(new Date(objeto.data), objeto.quantidade, objeto.valor));
                        
                    cb(null, negociacoes);

                } else {
                     console.log(xhr.responseText);
                     cb('Não foi possível obter nas negociações da semana', null);
                }
            }
        };

        xhr.send();
    }

}