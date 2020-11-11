import React from 'react'

class crudMenu extends React.Component{

    constructor(props) {
        super(props);
        this.state={
            actualOperation:"",
            number1:null,
            number2:null,
            id:null,
            dataFromResponse:"Jazda",
            isLoaded:false
        }

        this.addCreateComponent=this.addCreateComponent.bind(this)
        this.addCreateComponentToState=this.addCreateComponentToState.bind(this)
        this.escFunction=this.escFunction.bind(this)

        this.createCalc=this.createCalc.bind(this)
        this.deleteCalc=this.deleteCalc.bind(this)
        this.updateCalc=this.updateCalc.bind(this)


        this.handleChange=this.handleChange.bind(this)
        this.clickOnIdFetchDataByClickedId=this.clickOnIdFetchDataByClickedId.bind(this)
        this.addUpdateComponent=this.addUpdateComponent.bind(this);
        this.addUpdateComponentToState=this.addUpdateComponentToState.bind(this);
    }

    //Pridanie listenera na detekciu stlacenie esc
    componentDidMount(){
        document.addEventListener("keydown", this.escFunction, false);
        document.addEventListener("click", this.clickOnIdFetchDataByClickedId, false);
    }
    componentWillUnmount(){
        document.removeEventListener("keydown", this.escFunction, false);
        document.removeEventListener("click", this.clickOnIdFetchDataByClickedId, false);
    }

    //Delete div with actual operation
    escFunction(event){
        if(event.keyCode === 27){
            this.setState({
                actualOperation:"",
                number1:null,
                number2:null,
                id:null,
            })
        }
    }

    handleChange = (event) => {
        const target=event.target;
        const value=target.value;
        const name=target.name;
        const id=target.id;
        this.setState({
            [name]:value
        },()=>{
            if(id==="updateNumber1" || id==="updateNumber2"){
                this.addUpdateComponentToState()
            }
        });

    }

    clickOnIdFetchDataByClickedId(event){

        //Kontrola ci sa kliklo na id kde odoberame class name z kliknuteho elementu
        if(event.target.className==='idInTable'){
            const clikedId=event.target.innerHTML;

            let url="http://localhost:8080/calc/"+clikedId
            fetch(url)
                .then(response => response.json())
                .then(
                    (result) => {
                        this.setState({
                            number1:result.number1,
                            number2:result.number2,
                            id:result.id,
                            isLoaded:true
                        },() => {
                            this.addUpdateComponentToState();
                        });

                    },
                    (error) => {
                        this.setState({
                            isLoaded: false,
                            error
                        });
                    }
                )
        }
    }

    //CREATE//
    //function on create calc by post to backend
    createCalc(){

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ number1: this.state.number1.valueOf(),number2:this.state.number2.valueOf() })
        };

        fetch('http://localhost:8080/calc', requestOptions)
            .then(response => response.json())
            .then(
                (result) => {
                    this.setState({
                        dataFromResponse:result,
                        actualOperation:"",
                        number1:null,
                        number2:null,
                        id:null
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
            <div className="createComponent">
                <label for="inputNumber1">Number 1</label>
                <input type="number"  name="number1" id="inputNumber1"  value={this.state.number1} onChange={this.handleChange}/>
                <label for="inputNumber2">Number 2</label>
                <input type="number"  name="number2" id="inputNumber2" value={this.state.number2} onChange={this.handleChange}/>
                <button onClick={this.createCalc}>Create</button>
            </div>
        )
    }

    addCreateComponentToState(){
        this.setState({
            number1:null,
            number2:null,
            id:null
        },()=>{
            this.setState({
                actualOperation:this.addCreateComponent()
            })
        })
    }

    //UPDATE//

    updateCalc(){

        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ number1: this.state.number1.valueOf(),number2:this.state.number2.valueOf() })
        };

        const url='http://localhost:8080/calc/'+this.state.id
        fetch(url, requestOptions)
            .then(
                (result) => {
                    this.setState({
                        dataFromResponse:result,
                        actualOperation:"",
                        number1:null,
                        number2:null,
                        id:null
                    });

                },
                (error) => {
                    this.setState({

                    });

                }
            )
    }

    addUpdateComponentToState(){
        this.setState({
            actualOperation:this.addUpdateComponent()
        })
    }

    //Add div with components to update
    addUpdateComponent(){
        return(
            <div className="UpdateComponent">

                <output>ID:{this.state.id} </output>
                <label for="updateNumber1">Number 1</label>
                <input type="number"  name="number1" id="updateNumber1"  value={this.state.number1} onChange={this.handleChange}/>
                <label for="updateNumber2">Number 2</label>
                <input type="number"  name="number2" id="updateNumber2" value={this.state.number2} onChange={this.handleChange}/>
                <button onClick={this.updateCalc}>Update</button>
                <button onClick={this.deleteCalc}>Delete</button>

            </div>
        )
    }

    //DELETE//

    deleteCalc(){

        let url="http://localhost:8080/calc/"+this.state.id

        fetch(url,{
            method:'DELETE',
        })
            .then(response => response.text())
            .then(data => console.log(data));

        this.setState({
            actualOperation:"",
            number1:null,
            number2:null,
            id:null,
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