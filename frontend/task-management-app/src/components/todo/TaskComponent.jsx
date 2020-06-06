import React, { Component } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import moment from "moment";
import AuthenticationService from './AuthenticationService'
import TaskDataService from "../../api/tasks/TaskDataService";

class TaskComponent extends Component {

    constructor(props)
    {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            description : '',
            dueDate : '',
            status : '',
            userId : '',
            todoId :''
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        console.log(this.state.id)
    }

    componentDidMount(){
        if(this.state.id === -1)
                return;
        let user = AuthenticationService.getLoggedInUserName()
        this.getTask(user)
    }

    getTask(user){
        TaskDataService.getTask(user, this.state.id)
        .then(response => {
                  console.log(response)
                  console.log(moment(response.data.dueDate).format("YYYY-MM-DD"))
                    this.setState(
                        {
                            description : response.data.description, 
                            dueDate : moment(response.data.dueDate).format("YYYY-MM-DD"),
                            status : response.data.status
                        }
                    )
                }
        )
    }

    validate(values) {
        let errors = {}
        
        if(!values.description)
            errors.description = 'Enter a Description'
        else if (values.description.length < 5)
            errors.description = 'Enter atleast 5 characters in Description'
        
        if(!moment(values.dueDate).isValid())
            errors.dueDate = "Enter a valid Due Date"
        if(!values.status)
        errors.description = 'Please select a status value'
        return errors            
            
    }
    onSubmit(values) {

        //console.log(values)
        let username = AuthenticationService.getLoggedInUserName()
        //console.log(username)
        let todo = {
            todoId: this.state.id,
            description: values.description,
            dueDate: values.dueDate,
            status : values.status,    
            username : username       
        }
       // console.log(todo.todoId)
        if (todo.todoId == -1) {
            console.log(todo)
            TaskDataService.createTask(username, todo)
                .then(() => this.props.history.push("/tasks"))
        } else { 
            TaskDataService.updateTask(username, this.state.id,todo)
                .then(() => this.props.history.push("/tasks"))
        }
    }

    render(){
        let {description , dueDate , status} = this.state
        return(
        <div>
            <h1>Task</h1>
            <div className= "container">
                <Formik 
                 initialValues = {{description, dueDate, status}}
                 onSubmit = {this.onSubmit}
                 validateOnChange = {false}
                 validateOnBlur = {false}
                 validate = {this.validate }
                 enableReinitialize = {true}
                >
                    {
                        (props) => (
                            <Form>
                                <ErrorMessage name = "description" component = "div" className = "alert alert-warning" />
                                <ErrorMessage name = "dueDate" component = "div" className = "alert alert-warning" />
                                <fieldset className = "from-group" >
                                    <label>Description</label>
                                    <Field className="form-control" type= "text" name ="description"></Field>
                                </fieldset>
                                <fieldset className = "from-group">
                                    <label>DueDate</label>
                                    <Field className="form-control" type= "date" name = "dueDate"></Field>
                                </fieldset>
                                <fieldset className = "from-group">
                                    <label>Status</label>
                                    <Field className="form-control" as = "Select" name = "status">
                                        <option value = "Select">Select</option>
                                        <option value = "New">New</option>
                                        <option value = "In progress">In progress</option>
                                        <option value = "Completed">Completed</option>
                                    </Field>
                                </fieldset>
                                <button className = "btn btn-success" type = "submit">Save</button> 
                            </Form>
                        )                     
                    }
                </Formik>          
            </div>
        </div>
        )
    }


}

export default TaskComponent