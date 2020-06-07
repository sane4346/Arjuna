import axios from "axios";
import TaskDataService from "../../api/tasks/TaskDataService";
import {API_URL} from '../../constants'

export const USER_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
export const ACCESS_TOKEN = 'accessToken'

class AuthenticationService {

    executeBasicAuthenticationService(username, password){
        return axios.post(`${API_URL}/api/auth/signin`,{
            username,
            password
        })
    }
    
    saveToken(token) {
       // console.log("token" + token)
        localStorage.setItem(ACCESS_TOKEN, token)
    }
    getToken(){
        localStorage.getItem(ACCESS_TOKEN)
    }

    createJWTToken(token){
        return 'Bearer ' + token
    }

    registerSuccessfulLoginJwt(username,token){
        //console.log(username)
        sessionStorage.setItem(USER_SESSION_ATTRIBUTE_NAME, username); 
        this.saveToken(token);  
        this.setupAxiosInterceptors(this.createJWTToken(token))
    }

    logoutUser(){
        sessionStorage.removeItem(USER_SESSION_ATTRIBUTE_NAME)
    }

    isUserLoggedIn(){
        let user = sessionStorage.getItem(USER_SESSION_ATTRIBUTE_NAME)
        if(user === null)
            return false;
        return true;
    }

    getLoggedInUserName(){
        let user = sessionStorage.getItem(USER_SESSION_ATTRIBUTE_NAME)
        if(user === null)
            return '';
        return user;
    }

    setupAxiosInterceptors(token){

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