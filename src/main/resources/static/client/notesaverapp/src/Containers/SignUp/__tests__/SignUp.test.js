import React from 'react';
import {shallow} from 'enzyme';
import Index from '../index';
import {areMatchingPasswords} from "../service";

jest.mock('../../../RestService/User');
jest.mock('../service');
jest.mock('../../../Session/UserSession');
describe('SignUp', () => {
    let component;
    let instance;
    beforeEach(() => {
        component = shallow(<Index/>);
        instance = component.instance();
    });
    it('should render correctly', () => {
        expect(component).toMatchSnapshot();
    });
    it("should signUp with given details", () => {
        instance.handleSignUp = jest.fn(instance.handleSignUp);
        component.find('Input[name="email"]').simulate('change', {target: {value: 'Tester'}});
        component.find('Input[name="password"]').simulate('change', {target: {value: 'password'}});
        component.find('Input[name="rePassword"]').simulate('change', {target: {value: 'password'}});
        component.find('Button').simulate('click');
        expect(instance.handleSignUp).toHaveBeenCalled();
    });
});