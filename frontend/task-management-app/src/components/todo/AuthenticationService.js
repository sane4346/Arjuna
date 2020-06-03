import axios from "axios";
import TaskDataService from "../../api/tasks/TaskDataService";

class AuthenticationService {

    executeBasicAuthenticationService(username, password){
        let reqBody = {
            email : username,
            password : password
        }
        const options = {
            headers: {'Content-Type':'application/json'}
          };
        console.log(reqBody)
        return axios.post('http://localhost:8080/guru84-task-ws/users/login',{
            email : username,
            password : password
        },options)
        //  {
        //     headers : {authorization : this.createBasicAuthToken(username, password)}
        // })
    }

    saveToken(token) {
        console.log("token" + token)
        localStorage.saveToken('token', token)
    }
    getToken(){
        localStorage.getToken('token')
    }

    createBasicAuthToken(username, password){
        return 'Basic ' + window.btoa(username + ":" + password)
    }
    registerSuccessfulLogin(userId, password,token){
        sessionStorage.setItem('authenticatedUser',userId);   
        this.setupAxiosInterceptor(token)
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

    setupAxiosInterceptor(basicAuthHeader){

        axios.interceptors.request.use(
            (config) => {
                if(this.isUserLoggedIn()){
                     config.headers.authorization = basicAuthHeader
                }
                return config
            }
        )
    }
}
export default new AuthenticationService()