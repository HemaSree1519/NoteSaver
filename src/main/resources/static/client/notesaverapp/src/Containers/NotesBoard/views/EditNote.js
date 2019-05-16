import {Button, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import Moment from "moment";
import React, {Component} from "react";

export default class EditNote extends Component {
    render() {
        return (
            <Modal isOpen={this.props.childProps.isEditingNote} toggle={this.props.childProps.setEditState}>
                <ModalHeader>
                    <Input type="textarea"
                           name="title"
                           className="note-card-title-input"
                           value={this.props.childProps.editingNoteTitle}
                           onChange={this.props.childProps.onEditNoteTitle}
                    />
                </ModalHeader>
                <ModalBody>
                    <Input type="textarea"
                           name="content"
                           className="note-card-content-input"
                           value={this.props.childProps.editingNoteContent}
                           onChange={this.props.childProps.onEditNoteContent}
                           rows={5}/>
                    <br/>
                    <div className="footer-container">
                        <Label className="footer-edited-time">
                            Edited {Moment(this.props.childProps.editingNote['updatedAt']).format('lll')}</Label>
                    </div>
                </ModalBody>
                <ModalFooter className="modal-footer">
                    <Button name="delete" className="footer-button"
                            onClick={this.props.childProps.onDelete}>Delete</Button>{'  '}
                    <Button name="close" className="footer-button"
                            onClick={this.props.childProps.onUpdateNote}>Close</Button>
                </ModalFooter>
            </Modal>
        )
    }
};