import React, {Component} from 'react'
import AuthenticationService from './AuthenticationService.js'

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
        //console.log(this.state)
        this.setState({ [event.target.name]: event.target.value })
    }

    loginClicked(){
      AuthenticationService.
      executeBasicAuthenticationService(this.state.username, this.state.password)
        .then((response) => {
            console.log(response)
            AuthenticationService.registerSuccessfulLoginJwt(this.state.username, response.data.accessToken)
            this.props.history.push(`/welcome/${this.state.username}`)
            }) 
            .catch ( () => {
                console.log('failed')
                this.setState({isLoginSuccessful : false})
                this.setState({hasLoginFailed : true})
            })

    }

    render(){
        return(
            <div className = "container">
                <h1>Login</h1>
                <div>
                    {this.state.hasLoginFailed && <div className = "alert alert-warning">Invalid Credentials</div>}
                    User Name : <input type = "text" name = "username" value = {this.state.username} onChange = {this.handleChange}/>
                    Password : <input type = "password" name = "password" value = {this.state.password} onChange = {this.handleChange}/>
                    <button className = "btn btn-success" onClick = {this.loginClicked}>Login</button>
                </div>
            </div>
        )
    }

}

export default LoginComponent
