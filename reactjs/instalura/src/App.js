import React, { Component } from 'react';
import Header from './componentes/Header';
import Timeline from './componentes/Timeline';

class App extends Component {

  render() {
    const { match: { params } } = this.props;
    return (
      <div id="root">
        <div className="main">
          <Header history={this.props.history} />
          <Timeline login={params.login}/>
        </div>
      </div>
    );
  }
}

/*
App.contextTypes = {
  store : React.PropTypes.object.isRequired
} */

export default App;
