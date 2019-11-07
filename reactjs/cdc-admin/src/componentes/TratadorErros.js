import PubSub from 'pubsub-js';

export default class TratadorErros {
    publicaErros(e) {
        if(e.errors){
            e.errors.forEach(erro=>PubSub.publish("erro-validacao", erro));
        } else {
            alert(e.message);
        }
    }
}