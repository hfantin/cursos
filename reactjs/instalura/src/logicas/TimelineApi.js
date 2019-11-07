import {listagem,comentario,like,notifica} from '../actions/actionCreator';

export default class TimelineApi {

    static lista(login){
      let urlPerfil;
      if (login === undefined) {
          urlPerfil = `https://instalura-api.herokuapp.com/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
      } else {
          urlPerfil = `https://instalura-api.herokuapp.com/api/public/fotos/${login}`;
      }
      return dispatch => {
        fetch(urlPerfil)
        .then(response => response.json())
        .then(fotos => {
          dispatch(listagem(fotos));
          return fotos;
        });
      }
       //TODO LISTA COM ASYNC/AWAIT        
        // try{
        //     const response = await fetch(urlPerfil);
        //     if(response.ok){
        //         const json = await response.json();
        //         this.setState({fotos: json});
        //     } else {
        //         throw new Error('nao foi possivel obter a lista');
        //     }
        // } catch (e) {
        //     console.log(e.message);
        // }
    }

    static comenta(fotoId,textoComentario) {
      return dispatch => {
        const requestInfo = {
          method:'POST',
          body:JSON.stringify({texto:textoComentario}),
          headers: new Headers({
            'Content-type':'application/json'
          })
        };

        fetch(`https://instalura-api.herokuapp.com/api/fotos/${fotoId}/comment?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,requestInfo)
          .then(response => {
            if(response.ok){
              return response.json();
            } else {
              throw new Error("não foi possível comentar");
            }
          })
          .then(novoComentario => {
              dispatch(comentario(fotoId,novoComentario));  
              return novoComentario;
          });
        }
        //TODO fazer com async/await
        // const requestInfo = {
        //   method:'POST',
        //   body:JSON.stringify({texto:textoComentario}),
        //   headers: new Headers({
        //     'Content-type':'application/json'
        //   })
        // };
  
        // fetch(`https://instalura-api.herokuapp.com/api/fotos/${fotoId}/comment?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,requestInfo)
        //   .then(response => {
        //     if(response.ok){
        //       return response.json();
        //     } else {
        //       throw new Error("não foi possível comentar");
        //     }
        //   })
        //   .then(novoComentario => {
        //     Pubsub.publish('novos-comentarios',{fotoId,novoComentario});
        //   });    
    }    

    static like(fotoId){
      return dispatch => {
        fetch(`https://instalura-api.herokuapp.com/api/fotos/${fotoId}/like?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,{method:'POST'})
          .then(response => {
            if(response.ok) {
              return response.json();
            } else {            
              throw new Error("não foi possível realizar o like da foto");
            }
          })
          .then(liker => {
              dispatch(like(fotoId,liker));  
              return liker;
          });
        }
    }

    static pesquisa(login){
      return dispatch => {
        fetch(`https://instalura-api.herokuapp.com/api/public/fotos/${login}`)
          .then(response => response.json())
          .then(fotos => {
            if(login==='' || /^\s*$/.test(login)){
              console.log('string vazia');
            } 

            if(fotos.length === 0){
              dispatch(notifica('usuario não encontrado'));
            } 
            // else {
            //   dispatch(notifica('usuario encontrado'));
            // }

            dispatch(listagem(fotos));
            return fotos;
          });      
      }
    }
}