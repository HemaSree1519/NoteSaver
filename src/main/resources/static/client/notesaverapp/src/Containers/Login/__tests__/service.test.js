import React from 'react';
import {isAuthenticated} from "../service";

describe('service', () => {
    describe('isAuthenticated()', () =>{
        it("should return error code if user does not exist", async () => {
            await isAuthenticated("testing@gmail.com","tester").then((response) =>{
                expect(response).toBe("Email does not exist")
            })
        });
    })
});
