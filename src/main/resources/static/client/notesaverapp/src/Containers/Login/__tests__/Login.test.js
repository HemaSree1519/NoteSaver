import React from 'react';
import {shallow} from 'enzyme';
import Index from '../index';

describe('Login', () => {
    let component;
    beforeEach(() => {
        component = shallow(<Index/>)
    });
    it('should render correctly', () => {
        expect(component).toMatchSnapshot();
    });
    it("should Login with given details", () => {
        const instance = component.instance();
        instance.handleLogin = jest.fn(instance.handleLogin);
        component.find('Input[name="email"]').simulate('change', {target: {value: 'Tester'}});
        component.find('Input[name="password"]').simulate('change', {target: {value: 'password'}});
        component.find('Button').simulate('click');
        expect(instance.handleLogin).toHaveBeenCalled();
    });
});
