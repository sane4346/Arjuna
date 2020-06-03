import React, {Component} from 'react'
import AuthenticationService from './AuthenticationService.js'

class LoginComponent extends Component {

    constructor(props){
        super()
        this.state = {
            username : 'santosh',
            password : '',
            token : '',
            userId : '',
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
        // if(this.state.username === 'santosh' && this.state.password === 'dummy'){
        //     console.log('successful')
        //     // AuthenticationService.registerSuccessfulLogin(this.state.username, this.state.password)
        //     // this.props.history.push(`/welcome/${this.state.username}`)
        // } else {
        //                 console.log('failed')
        //     this.setState({isLoginSuccessful : false})
        //     this.setState({hasLoginFailed : true})
        // }
        AuthenticationService.executeBasicAuthenticationService(this.state.username, this.state.password)
        .then( 
            response => {
                console.log('successful')
            this.setState({
                token : response.headers.authorization,
                userId : response.headers.userId
            })
            AuthenticationService.registerSuccessfulLogin(this.state.username, this.state.password, this.state.token)
            this.props.history.push(`/welcome/${this.state.username}`)
            }
        ) .catch (
            () => {
                console.log('failed')
                this.setState({isLoginSuccessful : false})
                this.setState({hasLoginFailed : true})
            }
        )

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
