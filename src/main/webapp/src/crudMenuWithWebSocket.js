import React from 'react'

const socket = new WebSocket('ws://127.0.0.1:8080/fruit/jazda');
const socketForGetById = new WebSocket('ws://127.0.0.1:8080/fruit/jazda/getbyid');

class crudMenuWithWebSocket extends React.Component{

    constructor(props) {
        super(props);
        this.state={
            actualOperation:"",
            name:null,
            season:null,
            id:null,
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
                name:null,
                season:null,
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
            if(id==="updateName" || id==="updateSeason"){
                this.addUpdateComponentToState()
            }
        });

    }

    clickOnIdFetchDataByClickedId(event){

        console.log(event.target.className)

        //Kontrola ci sa kliklo na id kde odoberame class name z kliknuteho elementu
        if(event.target.className==='idInTableFruit'){

            const clickedId=event.target.innerHTML;

            var msg = {
                type: "getFruitById",
                id: clickedId,
            };

            socketForGetById.send(JSON.stringify(msg));
            socketForGetById.onmessage = (message) => {

                const dataFromWebSocket=JSON.parse(message.data)
                var fruitis=JSON.parse(dataFromWebSocket.map.Data);

                this.setState({
                    name:fruitis.name,
                    season:fruitis.season,
                    id:fruitis.id
                },() => {
                    this.addUpdateComponentToState();
                });

            }

        }

    }

    //CREATE//
    //function on create calc by post to backend
    createCalc(){

        var msg = {
            type: "create-fruit",
            data: {name:this.state.name,season:this.state.season},
        };

        socket.send(JSON.stringify(msg))

        this.setState({
            actualOperation:"",
            name:null,
            season:null,
            id:null
        });

    }

    //Div which serve on create calc
    addCreateComponent(){
        return(
            <div className="createComponentFruit">
                <label>Name</label>
                <input type="text"  name="name" id="inputName"  value={this.state.name} onChange={this.handleChange}/>
                <label>Season</label>
                <input type="text"  name="season" id="inputSeason" value={this.state.season} onChange={this.handleChange}/>
                <button onClick={this.createCalc}>Create</button>
            </div>
        )
    }

    addCreateComponentToState(){
        this.setState({
            name:null,
            season:null,
            id:null
        },()=>{
            this.setState({
                actualOperation:this.addCreateComponent()
            })
        })
    }

    //UPDATE//

    updateCalc(){

        var msg = {
            type: "update-fruit",
            data: {id:this.state.id,name:this.state.name,season:this.state.season},
        };

        socket.send(JSON.stringify(msg))


        this.setState({
            actualOperation:"",
            name:null,
            season:null,
            id:null
        });

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
                <label for="updateName">Name</label>
                <input type="text"  name="name" id="updateName"  value={this.state.name} onChange={this.handleChange}/>
                <label for="updateSeason">Season</label>
                <input type="text"  name="season" id="updateSeason" value={this.state.season} onChange={this.handleChange}/>
                <button onClick={this.updateCalc}>Update</button>
                <button onClick={this.deleteCalc}>Delete</button>

            </div>
        )
    }

    //DELETE//

    deleteCalc(){

        var msg = {
            type: "delete-fruit",
            data: {id:this.state.id},
        };

        socket.send(JSON.stringify(msg))

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

export default crudMenuWithWebSocket