import React, { Component } from 'react';
import './App.css';

import NestedList from './components/NestedList';
import Main from './components/Main';

class App extends Component {
    render() {
        return (
            <div className="App">
                <NestedList />
                <Main />
            </div>
        );
    }
}

export default App;