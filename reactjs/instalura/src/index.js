import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './css/login.css';
import './css/reset.css';
import './css/timeline.css';
import Login from './componentes/Login';
import Logout from './componentes/Logout';
import registerServiceWorker from './registerServiceWorker';
import {BrowserRouter as Router, Route, Switch, Redirect, matchPath} from 'react-router-dom';

import {createStore, applyMiddleware, combineReducers} from 'redux';
import thunkMiddleware from 'redux-thunk';
import {timeline} from './reducers/timeline';
import {notificacao} from './reducers/header';

import {Provider} from 'react-redux';

const reducers = combineReducers({timeline, notificacao});
const store = createStore(reducers, applyMiddleware(thunkMiddleware));


function verificaAutenticacao(props) {
    const match = matchPath('/timeline', {
        path: props.match.url,
        exact: true
    });
    let exibir = match === null; //match !== null  && match.isExact;
    return exibir || localStorage.getItem('auth-token') !== null
        ? <App {...props}/>
        : <Redirect to="/?msg=Você precisa estar logado para acessar a Timeline!"/>;
}

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <Switch>
                <Route exact path="/" component={Login}/>
                <Route
                    path="/timeline/:login?"
                    render={ props => verificaAutenticacao(props) }
                    />
                <Route path="/logout" component={Logout}/>
            </Switch>
        </Router>
    </Provider>,
    document.getElementById('root'));
registerServiceWorker();
