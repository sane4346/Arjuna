import React, {Component } from 'react'
import {Route, Redirect} from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'

class AuthenticatedRoute extends Component {
    render(){
            if(AuthenticationService.isUserLoggedIn()) {
                return <Route path={this.props.path} component={this.props.component}></Route>
            }else  {
                return (<Redirect to = "/login"/>)  
            }
    }
}

export default AuthenticatedRoute