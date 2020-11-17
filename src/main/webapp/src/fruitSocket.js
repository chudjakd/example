import React from 'react';
//import { w3cwebsocket as W3CWebSocket } from "websocket";
import CrudMenuWithWebSocket from "./crudMenuWithWebSocket";
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
        this.sendDataToClient=this.sendDataToClient.bind(this);
    }

    componentDidMount() {

        socket.onopen = () => {
            console.log('WebSocket Client Connected')
        }

        this.onMessageGetData();
    }


    onMessageGetData = () =>{

        socket.onmessage = (message) => {

            console.log("TEJKUJEM TO MOZNO JA: "+message)
            var allFromMessage= JSON.parse(message.data)

            const responseMessageFromWebsocket=allFromMessage.map.Response;
            if(responseMessageFromWebsocket==="ResponseGetAllFruit"){
                var allFruits= allFromMessage.map.Data.list



                this.setState({
                    fruits:allFruits
                })
            }
        }
    }

    sendDataToClient = (data) => {
        console.log('toto su data'+JSON.stringify(data))

        socket.send(JSON.stringify(data));
    }

    createFruit = () => {
        var name='Orange';
        var season='Summer'
        var msg = {
            type: "create-fruit",
            data: {name:name,season:season},
        };
        this.sendDataToClient(msg);
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

                <CrudMenuWithWebSocket>

                </CrudMenuWithWebSocket>

            </div>
        );
    }

}

export default FruitSocket