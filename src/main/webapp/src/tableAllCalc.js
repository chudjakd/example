import React from 'react'
import CrudMenu from './crudMenu'
import FruitSocket from "./fruitSocket";


class tableAllCalc extends React.Component {

    constructor(props) {
        super(props);

        this.state={
            calc:[],
            isLoaded:false,
            clickedId:null

        }

        this.renderTableCalc=this.renderTableCalc.bind(this)
        this.getDataForTable=this.getDataForTable.bind(this)
    }

    componentDidMount() {
        this.getDataForTable();
        setInterval(this.getDataForTable,1800)
    }

    //Fetch data from backend for my table
    getDataForTable(){

        fetch("http://localhost:8080/calc")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        calc: result,
                        isLoaded:true
                    });
                    //console.log(result)

                },

                (error) => {
                    this.setState({
                        isLoaded: false,
                        error
                    });
                }
            )


    }
    renderTableCalc(){

        if(this.state.calc!==undefined){

            return this.state.calc.map((calculator,index) => {
                const {id,number1,number2,countofnumbers} =calculator
                return (
                    <tr key={id}>
                        <td className="idInTable">{id}</td>
                        <td>{number1}</td>
                        <td>{number2}</td>
                        <td>{countofnumbers}</td>
                    </tr>
                )
            })

        }
        else {
            return (
                <tr key="Error">
                    <td>Error</td>
                    <td>Error</td>
                    <td>Error</td>
                    <td>Error</td>
                </tr>
            )
        }

    }

    render() {
        return (
            <div>

                <h1 id='title'>Calc Table</h1>
                <table id='calcs' row>
                    <tbody>
                    <th>ID</th>
                    <th>Number 1</th>
                    <th>Number 2</th>
                    <th>Count</th>
                    {this.renderTableCalc()}
                    </tbody>
                </table>

                <CrudMenu/>

                <FruitSocket/>

            </div>
        )
    }

}

export default tableAllCalc