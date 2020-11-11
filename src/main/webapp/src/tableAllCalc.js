import React from 'react'



class tableAllCalc extends React.Component {

    constructor(props) {
        super(props);

        this.state={
            calc:[],
            isLoaded:false,
            dataToReactTable:[{id:1,name:'Picko'},{id:2,name:'Picko'}]
        }

        this.renderTableCalc=this.renderTableCalc.bind(this)
        this.getDataForTable=this.getDataForTable.bind(this)
    }

    componentDidMount() {
        this.getDataForTable();
        setInterval(this.getDataForTable,3000)
    }

    //Fetch data from backend
    getDataForTable(){
        console.log('------------------Fetchujem Data-----------------------')
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
                        isLoaded: true,
                        error
                    });
                }
            )


    }

    renderTableCalc(){

        if(this.state.calc!=undefined){

            return this.state.calc.map((calculator,index) => {
                const {id,number1,number2,countofnumbers} =calculator
                return (
                    <tr key={id}>
                        <td>{id}</td>
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
                <h1 id='title'>React Dynamic Table</h1>
                <table id='calcs' row>
                    <tbody>
                    <th>ID</th>
                    <th>Number 1</th>
                    <th>Number 2</th>
                    <th>Count</th>
                    {this.renderTableCalc()}
                    </tbody>
                </table>


            </div>
        )
    }

}

export default tableAllCalc