<<<<<<< HEAD
import { Negociacao } from './negociacao';
=======
import { Negociacao } from './negociacao'
>>>>>>> e8e1951e0eb6a9fe9b7303f79f5328152672b06d

export class Negociacoes {

     // ou Negociacao[] == Array<Negociacao>
    private _negociacoes: Array<Negociacao> = [];

    adiciona(negociacao: Negociacao) {
        this._negociacoes.push(negociacao);
    }

    paraArray(){
        return this._negociacoes;
    }
}