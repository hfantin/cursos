import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    Dimensions,
    Button,
    AsyncStorage
} from 'react-native';

import CustomInput from '../components/CustomInput';

const width = Dimensions
    .get('screen')
    .width;

export default class Login extends Component {

    constructor() {
        super();
        this.state = {
            usuario: '',
            senha: '',
            mensagem: ''
        }
    }

    efetuaLogin() {
        const uri = "https://instalura-api.herokuapp.com/api/public/login";
        this.setState({mensagem: ''});

        const requestInfo = {
            method: 'POST',
            body: JSON.stringify({login: this.state.usuario, senha: this.state.senha}),
            headers: new Headers({'Content-type': 'application/json'})
        };

        fetch(uri, requestInfo).then(response => {
            if (response.ok) 
                return response.text();
            throw new Error('Não foi possível efetuar login');
        }).then(token => {

            const usuario = {
                nome: this.state.usuario,
                token: token
            }
            AsyncStorage.setItem('usuario', JSON.stringify(usuario));
            this.props.navigator.resetTo({
                screen: 'Feed',
                title: 'Instalura',
            })
            // return AsyncStorage.getItem('usuario');
        })
        // .then(token => console.warn(token))
        .catch(error => this.setState({mensagem: error.message}));
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.titulo}>Instalura</Text>
                <View style={styles.form}>
                    <CustomInput
                        placeholder="Usuário"
                        capitalize="none"
                        onChange={text => this.setState({usuario: text})}/>

                    <CustomInput
                        placeholder="Senha"
                        capitalize="none"
                        secure={true}
                        onChange={text => this.setState({senha: text})}/>
                    <Button title="Login" onPress={() => this.efetuaLogin()}/>
                </View>

                <Text style={styles.mensagem}>
                    {this.state.mensagem}
                </Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center'
    },
    titulo: {
        fontWeight: 'bold',
        fontSize: 26
    },
    form: {
        width: width * 0.8
    },
    mensagem: {
        marginTop: 15,
        color: '#e74c3c'
    }
})