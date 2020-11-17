
import React from 'react';
import './App.css';
import TableAllClc from './tableAllCalc'
import CrudMenu from './crudMenu'
import FruitSocket  from "./fruitSocket";

class App extends React.Component {


  render(){

  return (
    <div className="App">
        <div className="Appka">
            <TableAllClc>
            </TableAllClc>
            <FruitSocket>

            </FruitSocket>
        </div>
    </div>
  );
  }
}

export default App;
