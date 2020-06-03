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
            status : "Select"
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
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
        return errors            
            
    }
    onSubmit(values) {
        //console.log(values)
        let username = AuthenticationService.getLoggedInUserName()
        console.log("status = " + values.status)
        let todo = {
            id: this.state.id,
            description: values.description,
            dueDate: values.dueDate,
            status : values.status
            
        }

        if (this.state.id === -1) {
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
                                        <option value = "Start">Start</option>
                                        <option value = "OnGoing">OnGoing</option>
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