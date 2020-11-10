
import React from 'react';
import './App.css';
import TableAllClc from './tableAllCalc'
import CrudMenu from './crudMenu'

class App extends React.Component {


  render(){

  return (
    <div className="App">
        <header className="App-header">
            <TableAllClc/>
            <CrudMenu/>
        </header>

    </div>
  );
  }
}

export default App;
