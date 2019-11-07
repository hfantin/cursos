import {AsyncStorage} from 'react-native';
import {Navigation} from 'react-native-navigation';
import Feed from './src/components/Feed';
import Login from './src/screens/Login';

Navigation.registerComponent('Login', () => Login);
Navigation.registerComponent('Feed', () => Feed);
Navigation.registerComponent('PerfilUsuario', () => Feed);
AsyncStorage
    .getItem('usuario')
    .then(usuario => {
        if (usuario) {
            return {screen: 'Feed', title: 'Instalura'};
        }
        return {screen: 'Login', title: 'Login'};
    })
    .then(screen => Navigation.startSingleScreenApp({screen}));