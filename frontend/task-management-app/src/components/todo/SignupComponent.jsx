import React, {Component} from 'react'
import {Link} from 'react-router-dom'
import AuthenticationService from './AuthenticationService'
import TaskDataService from '../../api/tasks/TaskDataService'

class SignupComponent extends Component {

    constructor(props){
        super()
        this.state = {
            name: '',
            username: '',
            email : '',
            password : '',
            hasSignupFailed : false,
            isSignupSuccessful : false,
            message : ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.signUpClicked = this.signUpClicked.bind(this)
    }

    handleChange(event){
        //console.log(this.state)
        this.setState({ [event.target.name]: event.target.value })
    }

    signUpClicked(){
        console.log("Sign up called")
        TaskDataService.signUpUser(this.state.name, this.state.username, this.state.email, this.state.password)
        .then(
            (response) => {
                //console.log(response)
                this.setState({ isSignupSuccessful : response.data.success})
                this.setState({ message : response.data.message})
            }
        )
        .catch( () => {
            //console.log(response)
            this.setState({hasSignupFailed : true})
            this.setState({ isSignupSuccessful: false})
        })

    }
    render(){
        return(
    <div>
        <div className="login-form">
                    {this.state.hasSignupFailed && <div classNameName = "alert alert-warning">Username or email already registered!</div>}
                    {this.state.isSignupSuccessful && <div> {this.state.message} , Click on "Login here!" to go to Login Page</div>}
                <form className="text-left">
                <h2 className="text-center">Sign Up</h2> 
        
        <div className="form-group">
        	<div className="input-group">
              
                <input type="text" className="form-control" name="name" placeholder="Name" required="required" name = "name" value = {this.state.name} onChange = {this.handleChange}/>				
            </div>
        </div>
        <div className="form-group">
        <div className="input-group">
                <input type="text" className="form-control" name="username" placeholder="User Name" required="required"value = {this.state.username} onChange = {this.handleChange}/>				
            </div>
        </div>
        <div className="form-group">
        	<div className="input-group">
               
                <input type="text" className="form-control" name="email" placeholder="Email address" required="required"value = {this.state.email} onChange = {this.handleChange}/>				
            </div>
        </div>
		<div className="form-group">
            <div className="input-group">
                
                <input type="password" className="form-control" name="password" placeholder="Password" required="required"value = {this.state.password} onChange = {this.handleChange}/>				
            </div>
        </div>   
        </form>     
        <div className="form-group">
            <button type="submit" className="btn btn-success login-btn btn-block" name="signup" onClick = {this.signUpClicked}>Sign Up</button>
        </div>
        <p className="text-center text-muted small">Already have an account?</p>
        <div className="text-center social-btn">
        <a  className="btn btn-primary"><Link to = "/login" className="text-light">Login here !</Link></a>
        </div>
        
        </div>
    </div>
        )
    }

}

export default SignupComponent
