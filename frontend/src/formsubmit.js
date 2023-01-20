import axios from "axios";
import React from "react";


function formsubmit() {
    return (
        <form method='get'>
            <br />
            <button onClick={()=> {
                axios.get('/apiLighthouse/list')
                    .then(
                        res => { console.log(res.data);},
                        error=> { console.log(error); }
                    )

            }}>submit</button>
        </form>
    );
}



export default formsubmit;

