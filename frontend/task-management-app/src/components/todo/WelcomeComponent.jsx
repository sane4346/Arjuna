import React, {Component} from 'react'
import { Link } from 'react-router-dom'

class WelcomeComponent extends Component {
    render(){
        return(
            <>
            <h1>Welcome!</h1>
            <div className = "container">
                Welcome {this.props.match.params.name}
                <p><button className = "myTasks"><Link to="/tasks">My Tasks</Link></button></p>
            </div>
            </>
        )
    }
}

export default WelcomeComponent