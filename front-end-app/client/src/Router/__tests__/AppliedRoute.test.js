import React from 'react';
import { shallow } from 'enzyme';
import AppliedRoute from '../AppliedRoute';
describe('AppliedRoute', () => {
    it('should render correctly', () => {
        const component = shallow(<AppliedRoute/>);
        expect(component).toMatchSnapshot();
    });
});