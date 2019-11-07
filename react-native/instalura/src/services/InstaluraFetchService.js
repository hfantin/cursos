import {AsyncStorage} from 'react-native';

const retornaPossivelJson = (resposta) => {
    if (resposta.ok) 
        return resposta.json();
    
    throw new Error('Não foi possível completar a operação');
};

export default class InstaluraFetchService {

    static get(recurso) {
        const uri = 'https://instalura-api.herokuapp.com/api' + recurso;

        const jsonPromisse = AsyncStorage
            .getItem('usuario')
            .then(usuario => {
                const usr = JSON.parse(usuario);
                return {
                    headers: new Headers({"X-AUTH-TOKEN": usr.token})
                }
            })
            .then(requestInfo => fetch(uri, requestInfo))
            .then(resposta => retornaPossivelJson(resposta));

        return jsonPromisse;
    }

    static post(recurso, dados) {
        const uri = 'https://instalura-api.herokuapp.com/api' + recurso;

        const jsonPromisse = AsyncStorage
            .getItem('usuario')
            .then(usuario => {
                const usr = JSON.parse(usuario);
                return {
                    method: 'POST',
                    body: JSON.stringify(dados),
                    headers: new Headers({"Content-type": "application/json", "X-AUTH-TOKEN": usr.token})
                };
            })
            .then(requestInfo => fetch(uri, requestInfo))
            .then(resposta => retornaPossivelJson(resposta));

        return jsonPromisse;
    }

}