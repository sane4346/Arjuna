import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LoginComponent from './LoginComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import WelcomeComponent from './WelcomeComponent.jsx'
import ListTaskComponent from './ListTaskComponent.jsx'
import TaskComponent from './TaskComponent'



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
                        <AuthenticatedRoute path = "/tasks/:id" component = {TaskComponent}/>
                        <AuthenticatedRoute path = "/tasks" component = {ListTaskComponent}/>
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