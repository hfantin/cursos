import {List} from 'immutable';

function trocaFoto(lista,fotoId,callbackAtualizaPropriedades){

    const fotoEstadoAntigo = lista.find(foto => foto.id === fotoId);        
    const novasPropriedades = callbackAtualizaPropriedades(fotoEstadoAntigo);

    const fotoEstadoNovo = Object.assign({},fotoEstadoAntigo,novasPropriedades);
    const indiceDaLista = lista.findIndex(foto => foto.id === fotoId);

    return lista.set(indiceDaLista,fotoEstadoNovo);    
}


export function timeline(state=new List(),action){

    if(action.type === 'LISTAGEM'){  
        return new List(action.fotos);
    }
  
    if(action.type === 'COMENTARIO'){
        // const fotoAchada = state.find(foto => foto.id === action.fotoId);        
        // fotoAchada.comentarios.push(action.novoComentario); 
  
        // return state;
        return trocaFoto(state,action.fotoId,fotoEstadoAntigo => {
            const novosComentarios = fotoEstadoAntigo.comentarios.concat(action.novoComentario); 
            return {comentarios:novosComentarios};
        });
    }
  
    if(action.type === 'LIKE'){
        return trocaFoto(state,action.fotoId,fotoEstadoAntigo => {
            const likeada = !fotoEstadoAntigo.likeada;

            const liker = action.liker;
            const possivelLiker = fotoEstadoAntigo.likers.find(likerAtual => likerAtual.login === liker.login);

            let novosLikers;
            if(possivelLiker === undefined){
                novosLikers = fotoEstadoAntigo.likers.concat(liker);
            } else {
                novosLikers = fotoEstadoAntigo.likers.filter(likerAtual => likerAtual.login !== liker.login);            
            }

            return {likeada,likers:novosLikers};
        });
        // const fotoAchada = state.find(foto => foto.id === action.fotoId);
        // fotoAchada.likeada = !fotoAchada.likeada;

        // const liker = action.liker;
        // const possivelLiker = fotoAchada.likers.find(likerAtual => likerAtual.login === liker.login);

        // if(possivelLiker === undefined){
        //     fotoAchada.likers.push(liker);
        // } else {
        //     const novosLikers = fotoAchada.likers.filter(likerAtual => likerAtual.login !== liker.login);
        //     fotoAchada.likers = novosLikers;
        // }

        // return state;
    }
  
    return state;
  }