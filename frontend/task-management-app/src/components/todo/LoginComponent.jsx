import React, {Component} from 'react'
import {Link} from 'react-router-dom'
import AuthenticationService from './AuthenticationService.js'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser, faLock } from '@fortawesome/free-solid-svg-icons'

class LoginComponent extends Component {

    constructor(props){
        super()
        this.state = {
            username : '',
            password : '',
            hasLoginFailed : false,
            isLoginSuccessful : false,
            networkError : ''
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
            AuthenticationService.registerSuccessfulLoginJwt(response.data.username, response.data.accessToken)
            this.props.history.push(`/welcome/${this.state.username}`)
            }) 
            .catch ((error) => {
                // Error 😨

                 this.setState({isLoginSuccessful : false})
                 this.setState({hasLoginFailed : true})
                if (error.response) {
                    /*
                     * The request was made and the server responded with a
                     * status code that falls out of the range of 2xx
                     */
                    if (error.response.status !== 200)
                    {
                        this.setState({networkError : 'Network Error occured'})
                    } else {
                        this.setState({networkError : 'Invalid Credentials'})
                    }
                } else if (error.request) {
                    /*
                     * The request was made but no response was received, `error.request`
                     * is an instance of XMLHttpRequest in the browser and an instance
                     * of http.ClientRequest in Node.js
                     */
                    this.setState({networkError : 'No reponse Received'})
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request and triggered an Error
                    this.setState({networkError : 'Something went wrong, Please try after some time!'})
                    console.log('Error', error.message);
                }
   //             console.log(error.config);
            }); 

    }

    render(){
        return(
            
            <div>
                <div class="login-form">
              
                    {this.state.hasLoginFailed && <div className = "alert alert-warning">Invalid Credentials</div>}
                    <form className="text-left">
                    <h2 className="text-center">Login</h2> 
                   <div className="form-group text-left">
                     <label> <FontAwesomeIcon icon={faUser} /> User Name </label>
                      <input className="form-control" type = "text" name = "username" value = {this.state.username} onChange = {this.handleChange}/>				
                  </div>
                    <div class="form-group">
                    <label><FontAwesomeIcon icon={faLock} /> Password </label> 
                       <input className="form-control" type = "password" name = "password" value = {this.state.password} onChange = {this.handleChange}/>				
                      </div>
                </form>
                <div class="form-group text-center">
                    <button className = "btn btn-success " onClick = {this.loginClicked}>Login</button>
                    </div>
                    <p className="text-center text-muted small">Not have an account?</p>
                    <div className="text-center social-btn">
                    <a className="btn btn-primary"><Link to = "/signup" className="text-light">Signup here !</Link></a>
                    </div>
                </div>
            </div>
        )
    }

}

export default LoginComponent
