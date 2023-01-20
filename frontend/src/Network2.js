import React from "react";
import './Network2.css'
class Network2 extends React.Component {
    constructor(props) {
        super(props);
        this.state={}
    }

    render() {
        return (
            this.props.newslist.map((newlist,i)=>{
                return (
                    <ul key={newlist.id} className="text-center">
                        <li className='title'>{newlist.title}</li>
                        <li>{newlist.description}</li>
                    </ul>
                )
            })
        );
    }
}
export default Network2;

