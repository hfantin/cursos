export class ProxyFactory {
    
    static create(objeto, props, armadilha) {
        
        return new Proxy(objeto, {

            get(target, prop, receiver) {

                 // quando o javascript chama um método, ele realiza um get para obter uma
                // referencia e depois um apply pra passar os parametros, por isso verificamos
                // se a target[prop] é do tipo function(por exemplo, negociacaoController.esvazia).
                // Devemos também verificar qual funcao queremos interceptar, por isso usamos includes()
                
                if(ProxyFactory._ehFuncao(target[prop]) 
                    && props.includes(prop)) {
                        // retorna uma nova funcao com o contexto dinamico antes que o apply
                        // seja aplicado por padrao pelo interpretador chamado.
                        // essa function substitui o metodo original antes que o apply enre em ação 
                    return function() { // arguments é uma variavel implícita que dá acesso aos parametros, mesmo que eles não sejam explicitos

                        // console.log(`"${prop}" disparou a armadilha - target=${JSON.stringify(target)} - arguments=${JSON.stringify(arguments)}`);
                        target[prop].apply(target, arguments);
                        armadilha(target);
                    }

                } else {

                    return target[prop];
                }
            },

             set(target, prop, value, receiver) {
                
                 const updated = Reflect.set(target, prop, value);
                 if(props.includes(prop)) armadilha(target);
                 return updated;
             }
             
         });
   }

    static _ehFuncao(fn) {

        return typeof(fn) == typeof(Function);
    }
}