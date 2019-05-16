import React from "react";
import {Route, Switch} from "react-router-dom";
import Home from "../Containers/Home/index";
import Login from "../Containers/Login/index";
import SignUp from "../Containers/SignUp/index";
import NotesBoard from "../Containers/NotesBoard/index"
import NotFound from "../Containers/PageNotFound/index";
import AppliedRoute from "./AppliedRoute";

export default ({childProps}) =>
    <Switch>
        <Route path="/" exact component={Home}/>
        <Route path="/notes" exact component={NotesBoard}/>
        <AppliedRoute path="/signup" exact component={SignUp} props={childProps}/>
        <AppliedRoute path="/login" exact component={Login} props={childProps}/>
        <Route component={NotFound}/>
    </Switch>;