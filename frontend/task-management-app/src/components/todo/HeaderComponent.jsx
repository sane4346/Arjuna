import React, {Component} from 'react'
import {Link} from 'react-router-dom'
import {withRouter} from 'react-router'
import AuthenticationService from './AuthenticationService.js'

class HeaderComponent extends Component {
    render(){
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        //console.log(isUserLoggedIn)
        return(
            <header>
                <nav className = "navbar navbar-expand-md navbar-dark bg-dark">
                    <ul className = "navbar-nav">
                        {isUserLoggedIn && <li ><Link className = "nav-link" to = "/welcome/santosh">Home</Link></li>}
                        {isUserLoggedIn && <li><Link className = "nav-link" to = "/tasks">Tasks</Link></li>}
                    </ul>
                    <ul className = "navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className = "nav-link" to = "/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className = "nav-link" to = "/logout" onClick = {AuthenticationService.logoutUser}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}

export default withRouter(HeaderComponent);