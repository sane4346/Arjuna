import axios from "axios";
import TaskDataService from "../../api/tasks/TaskDataService";

class AuthenticationService {

    // executeBasicAuthenticationService(email, password){
    //     let body = {
    //         email : email,
    //         password : password
    //     }
    //     const requestOptions = {
    //         headers: { 'Content-Type': 'application/json' },
    //         body: JSON.stringify({ email, password })
    //     };
    //     //console.log(reqBody)
    //     return axios.post('http://localhost:8080/mobile-app-ws/users/login',{
    //         email : email,
    //         password : password
    //     }, {
    //      headers: {
    //          "Accept" : "application/json",
    //         "Access-Control-Allow-Origin": "*",
    //         "Access-Control-Allow-Methods" : "OPTIONS, GET, POST, HEAD",
    //         "Content-Type": "application/json"
    //         }
    //     })
    // }

    executeBasicAuthenticationService(username, password){
        return axios.post('http://localhost:8080/api/auth/signin',{
            username,
            password
        })
    }

    saveToken(token) {
        console.log("token" + token)
        localStorage.saveToken('token', token)
    }
    getToken(){
        localStorage.getToken('token')
    }

    createJWTToken(token){
        return 'Bearer ' + token
    }

    registerSuccessfulLoginJwt(username,token){
        console.log(token)
        sessionStorage.setItem('authenticatedUser',username);   
        this.setupAxiosInterceptor(this.createJWTToken(token))
    }

    logoutUser(){
        sessionStorage.removeItem('authenticatedUser')
    }

    isUserLoggedIn(){
        let user = sessionStorage.getItem('authenticatedUser')
        if(user === null)
            return false;
        return true;
    }

    getLoggedInUserName(){
        let user = sessionStorage.getItem('authenticatedUser')
        if(user === null)
            return '';
        return user;
    }

    setupAxiosInterceptor(token){

        axios.interceptors.request.use(
            (config) => {
                if(this.isUserLoggedIn()){
                     config.headers.authorization = token
                }
                return config
            }
        )
    }
}
export default new AuthenticationService()