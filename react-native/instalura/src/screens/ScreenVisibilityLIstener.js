//TODO registrar antes do inicio da app
import {ScreenVisibilityListener as RNNScreenVisibilityListener} from 'react-native-navigation';

export class ScreenVisibilityListener {
  constructor() {
    this.listener = new RNNScreenVisibilityListener({
      didAppear: ({screen, startTime, endTime, commandType}) => {
        console.warn('Screen Visibility Logs: ', `A tela ${screen} foi apresentada ${endTime - startTime} milisegundos após [${commandType}]`);
      },
    });
  }

  register() {
    this.listener.register();
  }

  unregister() {
    if (this.listener) {
      this.listener.unregister();
      this.listener = null;
    }
  }
}