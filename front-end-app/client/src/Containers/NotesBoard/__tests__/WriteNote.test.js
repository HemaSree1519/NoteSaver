import React from 'react';
import {shallow} from 'enzyme';
import EditNote from '../views/EditNote';
import WriteNote from "../views/WriteNote";

describe('EditNote', () => {
    let props;
    let component;
    beforeEach(() => {
        props = {
            writingNoteTitle: '',
            writingNoteContent: '',
            onCloseNewNote: jest.fn(),
            onWriteNoteContent: jest.fn(),
            onWriteNoteTitle: jest.fn(),
        };
        component = shallow(<WriteNote childProps={props}/>);
    });
    it('should render correctly', () => {
        expect(component).toMatchSnapshot();
    });
    it("should call title writing function when value changed", () => {
        component.find('Input[name="title"]').simulate('change', {value: 'title'});
        expect(props.onWriteNoteTitle).toHaveBeenCalledTimes(1);
    });
    it("should call content writing function when value changed", () => {
        component.find('Input[name="content"]').simulate('change', {value: 'content'});
        expect(props.onWriteNoteContent).toHaveBeenCalledTimes(1);
    });
    it("should call close function upon close button pressed", () => {
        component.find('Button[name="close"]').simulate('click');
        expect(props.onCloseNewNote).toHaveBeenCalledTimes(1);
    });
});