import React, {Component} from "react";
import "./style.css";
import {Button, Col, Form, FormGroup, Input} from "reactstrap";
import {areMatchingPasswords, formUserDetails} from "./service";
import {createUser} from "../../RestService/User";
import {CODES} from "../../ErrorCodes/codes";
import {setUser} from "../../Session/UserSession";

let userEmail = '';
let userPassword = '';
let userReEnteredPassword = '';
export default class Index extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isError: false,
            errorMessage: ''
        }
    }

    handleSignUp = async () => {
        // event.preventDefault();
        try {
            if (areMatchingPasswords(userPassword, userReEnteredPassword)) {
                await createUser(formUserDetails(userEmail, userPassword)).then((response) => {
                    switch (response) {
                        case 200 :
                            this.props.userHasAuthenticated(true);
                            setUser(userEmail);
                            userEmail = '';
                            userPassword = '';
                            console.log("200 called");
                            this.props.history.push('/notes');
                            break;
                        case 404 :
                            this.setErrorState(true, CODES["102"]);
                            console.log("404 called");
                            break;
                        default :
                            this.setErrorState(true, CODES["105"]);
                            console.log("default called");
                            break;
                    }
                });
            }
            else {
                this.setErrorState(true, CODES["104"])
            }
        } catch (e) {
            this.setErrorState(true, CODES["103"])
        }
    };

    setErrorState = (flag, message) => {
        this.setState({
            isError: flag,
            errorMessage: message
        });
    };
    onEmail = (email) => {
        this.setErrorState(false, '');
        userEmail = email.target.value;
    };

    onPassword = (password) => {
        this.setErrorState(false, '');
        userPassword = password.target.value;
    };

    onReEnteredPassword = (rePassword) => {
        userReEnteredPassword = rePassword.target.value;
    };

    render() {
        return (
            <Form>
                {this.state.isError && <p className="error">{this.state.errorMessage}</p>}
                <Col>
                    <FormGroup>
                        <Input type="email" name="email" id="exampleEmail" placeholder="example@gmail.com"
                               onChange={this.onEmail}/>
                    </FormGroup>
                </Col>
                <Col>
                    <FormGroup>
                        <Input type="password" name="password" placeholder="Password" onChange={this.onPassword}/>
                    </FormGroup>
                </Col>
                <Col>
                    <FormGroup>
                        <Input type="password" name="rePassword" placeholder="Re-Enter Password"
                               onChange={this.onReEnteredPassword}/>
                    </FormGroup>
                </Col>
                <Button onClick={this.handleSignUp}>SignUp</Button>
            </Form>
        );
    }
}
