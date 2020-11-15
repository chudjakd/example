import React from 'react';
//import { w3cwebsocket as W3CWebSocket } from "websocket";

//const clientis= new W3CWebSocket('ws://127.0.0.1:8080/username/jazda');
const socket = new WebSocket('ws://127.0.0.1:8080/fruit/jazda');


class FruitSocket extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            fruits:undefined
        }

        this.onMessageGetData=this.onMessageGetData.bind(this);
        this.sendDataToClient=this.sendDataToClient.bind(this);
        this.renderTableFruit=this.renderTableFruit.bind(this);
    }

    componentWillMount(){
        socket.onopen = () => {
            console.log('WebSocket Client Connected')
        }

    }
    componentDidMount() {
        this.onMessageGetData();
    }

    onMessageGetData = () =>{
        console.log("Som v on message")
        socket.onmessage = (message) => {

            var allFruits= JSON.parse(message.data)

            this.setState({
                fruits:allFruits
            })
        }
    }

    sendDataToClient = () => {
        console.log("Sendujem data to server")
        socket.send("AAAAAAA")
    }



    renderTableFruit(){

        if(this.state.fruits!==undefined){

            return this.state.fruits.map((fruit,index) => {
                const {id,name,season} =fruit
                return (
                    <tr key={id}>
                        <td className="idInTableFruit">{id}</td>
                        <td>{name}</td>
                        <td>{season}</td>
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
                </tr>
            )
        }

    }


    render() {
        return (
            <div>

                Practical Intro To WebSockets.

                <button onClick={this.sendDataToClient} >Hellou</button>
                <button onClick={this.onMessageGetData}>GetData</button>

                <h1 id='title'>Fruit Table</h1>
                <table id='fruits' row>
                    <tbody>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Season</th>
                    {this.renderTableFruit()}
                    </tbody>
                </table>

            </div>
        );
    }

}

export default FruitSocket