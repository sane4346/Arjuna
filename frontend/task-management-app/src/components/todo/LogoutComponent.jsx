import React, {Component} from 'react'
import AuthenticationService from './AuthenticationService.js'

class LogoutComponent extends Component {

    logoutUser(){
        AuthenticationService.logoutUser()
    }
    render(){
        return(
            <>
                <h1> You are logged out </h1>
                <div className = "container">
                    Thank You for Using Our Application
                </div>
            </>
        )
    }
}

export default LogoutComponent