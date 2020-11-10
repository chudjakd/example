import React from 'react'





class crudMenu extends React.Component{

    constructor(props) {
        super(props);
        this.state={
            actualOperation:"",
            number1:null,
            number2:null,
            id:null,
            dataFromResponse:"Jazda"
        }

        this.addCreateComponent=this.addCreateComponent.bind(this)
        this.addCreateComponentToState=this.addCreateComponentToState.bind(this)
        this.escFunction=this.escFunction.bind(this)
        this.createCalc=this.createCalc.bind(this)
        this.handleChange=this.handleChange.bind(this)
    }

    //Pridanie listenera na detekciu stlacenie esc
    componentDidMount(){
        document.addEventListener("keydown", this.escFunction, false);
    }
    componentWillUnmount(){
        document.removeEventListener("keydown", this.escFunction, false);
    }

    //Delete div with actual operation
    escFunction(event){
        if(event.keyCode === 27){
            this.setState({
                actualOperation:"",
                number1:null,
                number2:null,
                id:null,
                skuska:2
            })
        }
    }

    handleChange(event){
        const target=event.target;
        const value=target.value;
        const name=target.name;

        this.setState({
            [name]:value
        });
    }

    //function on create calc by post to backend
    createCalc(){
        // Simple POST request with a JSON body using fetch
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ number1: this.state.number1.valueOf(),number2:this.state.number2.valueOf() })
        };
        console.log(requestOptions)
        fetch('http://localhost:8080/calc', requestOptions)
            .then(response => response.json())
            .then(
                (result) => {
                    this.setState({
                        dataFromResponse:result
                    });

                },
                (error) => {
                    this.setState({

                    });
                }
            )

    }



    //Div which serve on create calc
    addCreateComponent(){
        return(
            <div>
                <input type="text" pattern="[0-9]/" name="number1" id="inputNumber1" value={this.state.number1} onChange={this.handleChange}/>
                <input type="text" pattern="[0-9]/" name="number2" id="inputNumber2" value={this.state.number2} onChange={this.handleChange}/>
                <button onClick={this.createCalc}>Create</button>
            </div>
        )
    }

    addCreateComponentToState(){
        this.setState({
            actualOperation:this.addCreateComponent()
        })
    }




    render(){
        return(
            <div>
                <div className="menuBar">
                    <button id="buttonAdd" onClick={this.addCreateComponentToState}>+</button>
                </div>
                <div>
                    {this.state.actualOperation}
                </div>
            </div>

        )
    }


}

export default crudMenu