import React from "react";
import axios from "axios";
import Network2 from "./Network2"
class Network extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            newslist:[]
        };

    }
    componentDidMount() {
        /* Http = axios
         * Http调用里面的get方法
         * data是参数 get需要key params post不需要直接{id: '007'}
         * .then()成功回调
         * .catch()失败回调
         */

        let data = {
            params: {
                key: "2fa8bdec666c46942e03ae3a158e2339",
            },
        };
        axios.get("1.json", data).then(
            (res) => {
                const newslist = res.data.newslist;
                // const listItems = newslist.map((newlist) => {
                //     console.log(newlist.title + "----" + newlist.description);

                //   return <li>{newlist}</li>;
                // });
                this.setState({newslist:newslist});
                console.log(newslist);
                // return listItems;
            },
            (error) => {
                console.log("失败了", error);
            }
        );
    }

    render() {
        return (
            <div>
                <Network2 newslist={ this.state.newslist}/>
            </div>
        );
    }
}
export default Network;

