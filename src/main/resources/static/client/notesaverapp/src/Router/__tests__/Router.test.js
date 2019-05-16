import React from 'react';
import { shallow } from 'enzyme';
import Router from '../Routes';
describe('Router', () => {
    it('should render correctly', () => {
        const component = shallow(<Router/>);
        expect(component).toMatchSnapshot();
    });
});