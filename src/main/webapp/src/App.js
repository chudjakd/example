
import React from 'react';
import './App.css';
import TableAllClc from './tableAllCalc'
import CrudMenu from './crudMenu'
import FruitSocket  from "./fruitSocket";
import Example from "./SkuskaCounter";

class App extends React.Component {


  render(){

  return (
    <div className="App">
        <div className="Appka">
            <TableAllClc>
            </TableAllClc>
            <Example></Example>
            <FruitSocket>

            </FruitSocket>
        </div>
    </div>
  );
  }
}

export default App;
