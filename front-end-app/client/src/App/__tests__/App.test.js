import React from 'react';
import App from '../index';
import {shallow} from "enzyme/build";
describe('App', () =>{
    it('should render correctly',()=>{
        const component = shallow(<App/>);
        expect(component).toMatchSnapshot();
    });

});
