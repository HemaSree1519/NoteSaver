import React from 'react';
import {shallow} from 'enzyme';
import Note from "../views/Note";

describe('EditNote', () => {
    let note;
    let setEditState;
    let component;
    beforeEach(() => {
        note = {
            "id": 2,
            "title": "test",
            "content": "Content here",
            "email": "test@gmail.com",
            "createdAt": "2019-05-12T13:20:40.735Z",
            "updatedAt": "2019-05-12T13:20:40.735Z"
        };
        setEditState = jest.fn();
        component = shallow(<Note note={note} setEditState={setEditState}/>);
    });
    it('should render correctly', () => {
        expect(component).toMatchSnapshot();
    });
    it('should call edit state function when clicked on card', () => {
        component.find('Card[name="card"]').simulate('click');
        expect(setEditState).toHaveBeenCalledTimes(1);
    })
});