import React, {Component} from 'react'
import TaskDataService from '../../api/tasks/TaskDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'

class ListTaskComponent extends Component {

    constructor(){
        super()
        this.state = {
            tasks : [],
            message: null,
            archivedTasks: [],
            description : '',
            dueDate : '',
            status : '',
            userId : '',
            todoId :'', 
            isArchived: false
        }
        this.refreshTasks = this.refreshTasks.bind(this)
        this.deleteTask =  this.deleteTask.bind(this)
        this.updateTaskClicked =  this.updateTaskClicked.bind(this)
        this.addTaskClicked = this.addTaskClicked.bind(this)
        this.refreshAllTasks = this.refreshAllTasks.bind(this)
        this.archiveTask = this.archiveTask.bind(this)
    }

    componentDidMount(){
        let user = AuthenticationService.getLoggedInUserName()
        this.refreshAllTasks(user)
    }

    refreshTasks(user){
        TaskDataService.retrieveAllTasks(user)
        .then(
            response => {
               // console.log(response)
                this.setState({ tasks : response.data})
            }
        )
    }

    refreshAllTasks(username) {
        this.refreshTasks(username)
        TaskDataService.retrieveArchivedTasks(username)
        .then(
            response => {
               // console.log(response)
               this.setState({ 
                archivedTasks : response.data
            })
               // this.setState({ archivedTasks : response.data})
                
            }
        )
    }
    deleteTask(todoId){
        let username = AuthenticationService.getLoggedInUserName()
        TaskDataService.deleteTask(username, todoId)
        .then(
                response => {
                   // console.log(response)
                    this.setState({message : `Delete of task ${todoId} Successful`})
                    this.refreshAllTasks(username)
                    this.message = null
                }
        )
    }

    archiveTask(todoId){
        let username = AuthenticationService.getLoggedInUserName()
        TaskDataService.getTask(username, todoId)
        .then(response => {
                  //console.log(response)
                  //console.log(moment(response.data.dueDate).format("YYYY-MM-DD"))
                    this.setState(
                        {
                            description : response.data.description, 
                            dueDate : moment(response.data.dueDate).format("YYYY-MM-DD"),
                            status : response.data.status
                        }
                    )
                }
        )
        let todo = {
            todoId: todoId,
            description: this.state.description,
            dueDate: this.state.dueDate,
            status : this.state.status,    
            username : username,
            isArchived: false       
        }
        TaskDataService.archiveTask(username, todoId, todo)
        .then(
                response => {
                   // console.log(response)
                    this.setState({message : `Task archived Successfully`})
                    this.refreshAllTasks(username)
                    this.message = null
                }
        )
    }
    updateTaskClicked(todoId){
       // console.log(`update called for ${todoId}`)
        this.props.history.push(`/tasks/${todoId}`)
    }

    addTaskClicked(){
       // console.log('Add called')
        this.props.history.push(`/tasks/-1`)
    }

    render(){
        return(
            <div>
                <div>
                    <h1>Tasks List</h1>
                    {this.state.message && <div className = "alert alert-success">{this.state.message}</div>}
                    <div className = "container">
                       { (this.state.tasks.length > 0 ) && <table className = "table">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Due-Date</th>
                                    <th>Status</th>
                                    <th>Update</th>
                                    <th>Archive</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.tasks.map( 
                                        task =>
                                        <tr key = {task.todoId}>
                                            <td>{task.description}</td>
                                            <td>{moment(task.dueDate).format("YYYY-MM-DD")}</td>
                                            <td>{task.status}</td>
                                            <td><button className = "btn btn-success" onClick = {() => this.updateTaskClicked(task.todoId)}>Update</button></td>
                                            <td><button className = "btn btn-warning" onClick = {() => this.archiveTask(task.todoId)}>Archive</button></td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>}
                        <div className = "row" >
                            <button className = "btn btn-success" onClick = {this.addTaskClicked}>Add New Task</button>
                        </div>
                    </div>
                </div> 
                {(this.state.archivedTasks.length > 0) && <div>
                    <h1>Archieved Tasks</h1>
                    <div className = "container">    
                    <table className = "table">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Due-Date</th>
                                    <th>Status</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.archivedTasks.map( 
                                        task =>
                                        <tr key = {task.todoId}>
                                            <td>{task.description}</td>
                                            <td>{task.dueDate.toString()}</td>
                                            <td>{task.status}</td>
                                            <td><button className = "btn btn-warning" onClick = {() => this.deleteTask(task.todoId)}>Delete</button></td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table> 
                    </div>
                </div> }
            </div>
        )
    }

}

export default ListTaskComponent