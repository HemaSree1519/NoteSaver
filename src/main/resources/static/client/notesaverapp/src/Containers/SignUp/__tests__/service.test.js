import React from 'react';
import {areMatchingPasswords, formUserDetails} from "../service";

// jest.mock('../service');
describe('service', () => {
    describe('areMatchingPasswords()', () => {
        it("should return true if password are matching", async () => {
            const response = areMatchingPasswords("tester", "tester");
            expect(response).toBeTruthy()
        });
        it("should return false if password are not matching", async () => {
            const response = areMatchingPasswords("tester", "tester2");
            expect(response).toBeFalsy()
        });
    })
    describe('formUserDetails()', () => {
        const expectedUserDetails = {
            email: 'tester@gmail.com',
            password: 'password',
            role: null,
            userName: 'tester'
        };
        it('should return user json with given email and password', () => {
            const user = formUserDetails('tester@gmail.com', 'password');
            expect(user).toEqual(expectedUserDetails)
        })
    })
});
