
import React, { Component } from 'react';
import TimelineApi from '../logicas/TimelineApi';
import {connect} from 'react-redux';
// import { NavigationActions } from 'react-navigation';

class Header extends Component {

  constructor(props){
    super(props);
  }

  pesquisa(event){
    event.preventDefault();
    this.props.pesquisa(this.loginPesquisado.value);
  }

  logout(event){
    event.preventDefault();
    console.log('logout ' + JSON.stringify(this.props));
    this.props.history.push('/logout');
    // this.props.logout();
    // NavigationActions.navigate({ routeName: '/logout' });
  }

  render(){
    return (
      <header className="header container">
        <h1 className="header-logo">
          Instalura
        </h1>

        <form className="header-busca" onSubmit={this.pesquisa.bind(this)}>
          <input type="text" name="search" placeholder="Pesquisa" className="header-busca-campo" ref={input => this.loginPesquisado = input}/>
          <input type="submit" value="Buscar" className="header-busca-submit"/>
        </form>


        <nav>
          <ul className="header-nav">
            <li className="header-nav-item">
              <span>{this.props.msg}</span>
            </li>
          </ul>
        </nav>

        <form className="header-busca" onSubmit={this.logout.bind(this)}>
          <input type="submit" value="Logout" />
        </form>

      </header>
    );
  }
}

const mapStateToProps = state => {
  return {msg : state.notificacao}
};

const mapDispatchToProps = dispatch => {
  return {
      pesquisa : (login) => {
          dispatch(TimelineApi.pesquisa(login));
      },
      // logout : () => {
      //   dispatch(NavigationActions.navigate({ routeName: 'logout' }));
      // }

  }
}

const HeaderContainer = connect(mapStateToProps,mapDispatchToProps)(Header);

export default HeaderContainer