import React from 'react';
import {shallow} from 'enzyme';
import EditNote from '../views/EditNote';

describe('EditNote', () => {
    let props;
    let component;
    beforeEach(() => {
        props = {
            isEditingNote: true,
            editingNote: {
                "id": 2,
                "title": "test",
                "content": "Content here",
                "email": "test@gmail.com",
                "createdAt": "2019-05-12T13:20:40.735Z",
                "updatedAt": "2019-05-12T13:20:40.735Z"
            },
            editingNoteTitle: '',
            editingNoteContent: '',
            onDelete: jest.fn(),
            onUpdateNote: jest.fn(),
            onEditNoteContent: jest.fn(),
            onEditNoteTitle: jest.fn(),
        };
        component = shallow(<EditNote childProps={props}/>);
    });
    it('should render correctly', () => {
        expect(component).toMatchSnapshot();
    });
    it("should call delete function upon delete button pressed", () => {
        component.find('Button[name="delete"]').simulate('click');
        expect(props.onDelete).toHaveBeenCalledTimes(1);
    });
    it("should call update function upon close button pressed", () => {
        component.find('Button[name="close"]').simulate('click');
        expect(props.onUpdateNote).toHaveBeenCalledTimes(1);
    });
    it("should call title editing function when value changed", () => {
        component.find('Input[name="title"]').simulate('change', {value: 'title'});
        expect(props.onEditNoteTitle).toHaveBeenCalledTimes(1);
    });
    it("should call content editing function when value changed", () => {
        component.find('Input[name="content"]').simulate('change', {value: 'content'});
        expect(props.onEditNoteContent).toHaveBeenCalledTimes(1);
    });
});