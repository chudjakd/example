import React, { useState, useEffect } from 'react';

function Example() {
    const [count, setCount] = useState(0);
    const [fruit, setFruit] = useState('banana');

    useEffect(() => {
        console.log("Vykonava to vkuse")
        setFruit(prevState => "Apple")
        console.log(fruit)
        setCount(prevState => count+1)
    },[]);

    useEffect(() => {
        //ComponentdidUpdate
    }); // No second argument, so run after every render.

    useEffect(() => {
        //ComponentDidMount after specific component, this type is after count
    }, [count]);

    useEffect(() => {
        // It will be called before unmounting.
        return () => {
            console.log('componentWillUnmount!');
        };
    }, []);

    return (
        <div>
            <p>You clicked {count} times</p>
            <p>This is fruit:{fruit}</p>
            <button onClick={() => setCount(count + 1)}>
                Click me
            </button>
        </div>
    );
}
export default Example