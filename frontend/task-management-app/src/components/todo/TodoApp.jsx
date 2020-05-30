import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LoginComponent from './LoginComponent.jsx'
import WelcomeComponent from './WelcomeComponent.jsx'
import TasksComponent from './TaskComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'


import './TodoApp.css'


class TodoApp extends Component {
    render(){
        return(
            <div className = "todoApp">
                <Router>
                    <>
                    <HeaderComponent/>
                      <Switch>
                        <Route path = "/" exact component={LoginComponent}/>
                        <Route path = "/login" component={LoginComponent}/>
                        <AuthenticatedRoute path = "/welcome/:name" component = {WelcomeComponent}/>
                        <AuthenticatedRoute path = "/tasks" component = {TasksComponent}/>
                        <AuthenticatedRoute path = "/logout" component = {LogoutComponent}/>
                        <Route component={ErrorComponent}/>
                       </Switch>
                    <FooterComponent/>
                    </>
                </Router>
               {/*<LoginComponent/>*/ }
            </div>
        )
    }
}

export default TodoApp