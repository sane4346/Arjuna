import React, {Component} from 'react'
import {BrowserRouter as Router, Route} from 'react-router-dom'
import './TodoApp.css'

class TodoApp extends Component {
    render(){
        return(
            <div className = "todoApp">
                <Router>
                    <>
                        <Route path = "/" exact component={LoginComponent}/>
                        <Route path = "/login" component={LoginComponent}/>
                        <Route path = "/welcome" component = {WelcomeComponent}/>
                    </>
                </Router>
               {/*<LoginComponent/>*/ }
            </div>
        )
    }
}

class LoginComponent extends Component {

    constructor(props){
        super()
        this.state = {
            username : 'santosh',
            password : '',
            hasLoginFailed : false,
            isLoginSuccessful : false
        }
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
    }

    handleChange(event){
        console.log(this.state)
        this.setState({ [event.target.name]: event.target.value })
    }

    loginClicked(){
        console.log(this.state)
        if(this.state.username === 'santosh' && this.state.password == 'dummy'){
            console.log('successful')
            this.props.history.push("/welcome")
        } else {
            console.log('failed')
            this.setState({isLoginSuccessful : false})
            this.setState({hasLoginFailed : true})
        }
    }

    render(){
        return(
            <div className = "loginComponent">
            {this.state.hasLoginFailed && <div>Invalid Credentials</div>}
            {/*{this.state.isLoginSuccessful && <div>Login successful</div>}*/}
            User Name : <input type = "text" name = "username" value = {this.state.username} onChange = {this.handleChange}/>
            Password : <input type = "password" name = "password" value = {this.state.password} onChange = {this.handleChange}/>
            <button onClick = {this.loginClicked}>Login</button>
            </div>
        )
    }

}

class WelcomeComponent extends Component {

    render(){
        return(
            <div className = "welcomeComponent">
                Welcome Component
            </div>
        )
    }
}
export default TodoApp