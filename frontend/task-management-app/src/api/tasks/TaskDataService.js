import axios from 'axios'

class TaskDataService {
    
    retrieveAllTasks(name){
        return axios.get(`http://localhost:8080/users/${name}/todos`)
    }

    deleteTask(name, id){
        return axios.delete(`http://localhost:8080/users/${name}/todos/${id}`)
    }

    getTask(username, id){
        return axios.get(`http://localhost:8080/users/${username}/todos/${id}`)
    }

    updateTask(username, id, todo){
        return axios.put(`http://localhost:8080/users/${username}/todos/${id}`, todo)
    }

    createTask(username, todo){
        return axios.put(`http://localhost:8080/users/${username}/todos`, todo)
    }
}

export default new TaskDataService()