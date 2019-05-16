import {Card, CardBody, CardText, CardTitle} from "reactstrap";
import React, {Component} from "react";

export default class Note extends Component {
    render() {
        return (
            <Card name="card" className="note"
                  onClick={() => {
                      this.props.setEditState(this.props.note);
                  }}>
                <CardBody>
                    <CardTitle name="title" className="card-title">{this.props.note["title"]}</CardTitle>
                    <CardText name="content">{this.props.note["content"]}</CardText>
                </CardBody>
            </Card>
        )
    }
};