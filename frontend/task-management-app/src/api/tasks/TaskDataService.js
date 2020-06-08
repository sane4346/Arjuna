import axios from 'axios'
import {API_URL} from '../../constants'

class TaskDataService {

    signUpUser(name,username,email, password){
        return axios.post(`${API_URL}/api/auth/signup`,{
            name,
            username,
            email,
            password
        })
    }
    //let name = 'wKUxcyXrboyKkeKf1QtxlBFjS2WKSX'
    retrieveAllTasks(username){
        return axios.get(`${API_URL}/users/${username}/todos`)
    }

    retrieveArchivedTasks(username){
        return axios.get(`${API_URL}/users/${username}/archived/todos`)
    }

    deleteTask(username, id){
        return axios.delete(`${API_URL}/users/${username}/todos/${id}`)
    }

    getTask(username, id){
        return axios.get(`${API_URL}/users/${username}/todos/${id}`)
    }

    updateTask(username, id, todo){
        return axios.put(`${API_URL}/users/${username}/todos/${id}`, todo)
    }

    archiveTask(username, id, todo){
        return axios.put(`${API_URL}/users/${username}/archived/todos/${id}`, todo)
    }

    createTask(username, todo){
        return axios.post(`${API_URL}/users/${username}/todos`, todo)
    }
}

export default new TaskDataService()