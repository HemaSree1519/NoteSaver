import {Button, Card, Input} from "reactstrap";
import React, {Component} from "react";

export default class WriteNote extends Component{
    render(){
        return (
            <Card className="note-input">
                <Input className="note-card-title-input" type="text" placeholder="Title" name="title"
                       value={this.props.childProps.writingNoteTitle}
                       onChange={this.props.childProps.onWriteNoteTitle}
                />
                <Input className="note-card-content-input" type="textarea" placeholder="Write a note..." name="content"
                       value={this.props.childProps.writingNoteContent}
                       onChange={this.props.childProps.onWriteNoteContent}
                />
                <div className="footer-container">
                    <Button name="close" className="footer-button" onClick={() => this.props.childProps.onCloseNewNote()}>Close</Button>
                </div>
            </Card>
        )
    }
};