import axios from 'axios'

class TaskDataService {

    signUpUser(name,username,email, password){
        let reqBody = {
            name : name,
            username : username,
            email : email,
            password : password
        }
        return axios.get('http://localhost:8080/auth/api/singup',reqBody)
    }
    //let name = 'wKUxcyXrboyKkeKf1QtxlBFjS2WKSX'
    retrieveAllTasks(username, authToken){
        return axios.get(`http://localhost:8080/guru84-task-ws/users/${username}/todos`,{
            headers : {
                Authorization : authToken            
            }
        })
    }

    deleteTask(username, id, authToken){
        return axios.delete(`http://localhost:8080/guru84-task-ws/users/${username}/todos/${id}`,
        {
            headers : {
                Authorization : authToken            }
        })
    }

    getTask(username, id, authToken){
        return axios.get(`http://localhost:8080/guru84-task-ws/users/${username}/todos/${id}`,
        {
            headers : {
                Authorization : authToken
            }
        })
    }

    updateTask(username, id, todo, authToken){
        return axios.put(`http://localhost:8080/guru84-task-ws/users/${username}/todos/${id}`, todo,{
            headers : {
                Authorization : authToken            
            }
        })
    }

    createTask(username, todo, authToken){
        return axios.post(`http://localhost:8080/guru84-task-ws/users/${username}/todos`, todo,{
            headers : {
                Authorization : authToken            
            }
        })
    }
}

export default new TaskDataService()