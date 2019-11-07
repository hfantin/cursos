import React, { Component } from 'react';
// import { NavigationActions } from 'react-navigation';

export default class Login extends Component {

    constructor(props){
        super(props);
        const params = new URLSearchParams(props.location.search);
        const msgParam = params.get('msg');
        this.state = {msg: msgParam};
    }

    async logar(event){
        event.preventDefault();
        const requestInfo = {
            method:'POST',
            body:JSON.stringify({login:this.login.value,senha:this.senha.value}),
            headers:new Headers({
                'Content-type' : 'application/json' 
            })
        };

        try{
            const response = await fetch('https://instalura-api.herokuapp.com/api/public/login',requestInfo);
            if(response.ok) {
                const token = await response.text();
                localStorage.setItem('auth-token',token);
                this.props.history.push('/timeline');
                // NavigationActions.navigate({ routeName: '/timeline' });
            } else {
                throw new Error('não foi possível fazer o login');
            }
        } catch(e) {
            this.setState({msg:e.message});
        };
    }

    render(){ 
            return (
            <div className="login-box">
                <h1 className="header-logo">Instalura</h1>
                <span>{this.state.msg}</span>
                <form onSubmit={this.logar.bind(this)}>
                    <input type="text" ref={(input) => this.login = input}/>
                    <input type="password" ref={(input) => this.senha = input}/>
                    <input type="submit" value="login"/>
                </form>
            </div>
     );
  }   
}