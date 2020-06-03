import axios from 'axios'

class TaskDataService {

    signInUser(username, password){
        let reqBody = {
            email : username,
            password : password
        }
        return axios.get('http://localhost:8080/login',reqBody)
    }

    retrieveAllTasks(name){
        return axios.get(`http://localhost:8080/guru84-task-ws/users/${name}/todos`)
    }

    deleteTask(name, id){
        return axios.delete(`http://localhost:8080/guru84-task-ws/users/${name}/todos/${id}`)
    }

    getTask(username, id){
        return axios.get(`http://localhost:8080/guru84-task-ws/users/${username}/todos/${id}`)
    }

    updateTask(username, id, todo){
        return axios.put(`http://localhost:8080/guru84-task-ws/users/${username}/todos/${id}`, todo)
    }

    createTask(username, todo){
        return axios.put(`http://localhost:8080/guru84-task-ws/users/${username}/todos`, todo)
    }
}

export default new TaskDataService()