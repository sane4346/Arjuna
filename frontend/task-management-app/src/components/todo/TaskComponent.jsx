import React, {Component} from 'react'

class TasksComponent extends Component {

    constructor(){
        super()
        this.state = {
            tasks : [
                {id : 1, description : "Learn React",duedate:new Date(), status:"onGoing"},
                {id : 2, description : "Visit India", duedate:new Date(), status:"onGoing"},
                {id : 2, description : "Evening Meeting", duedate:new Date(), status:"start"},
                {id : 3, description : "StackHack", duedate:new Date(), status:"start"}
            ]
        }
    }
    render(){
        return(
            <div>
                <h1>Tasks List</h1>
                <div className = "container">
                    <table className = "table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Description</th>
                                <th>Due-Date</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.tasks.map( 
                                    task =>
                                    <tr key = {task.id}>
                                        <td>{task.id}</td>
                                        <td>{task.description}</td>
                                        <td>{task.duedate.toString()}</td>
                                        <td>{task.status}</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default TasksComponent