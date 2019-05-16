import React from 'react';
import { shallow } from 'enzyme';
import Index from '../index';
describe('NotesBoard', () => {
    it('should render correctly', () => {
        const component = shallow(<Index/>);
        expect(component).toMatchSnapshot();
    });
});