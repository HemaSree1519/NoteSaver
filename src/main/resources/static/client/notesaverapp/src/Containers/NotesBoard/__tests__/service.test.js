import React from 'react';
import {formNoteDetails, formUpdatedNoteDetails, hadEdited} from "../service";

describe('service', () => {
    const note = {
        "email": '',
        "title": 'title',
        "content": 'content',
        "createdAt": new Date(),
        "updatedAt": new Date()
    };
    describe('formNoteDetails()', () => {
        it('should return note json with given email and password', () => {
            const formedNote = formNoteDetails('title', 'content');
            const expectedNoteDetails = {
                "email": '',
                "title": 'title',
                "content": 'content',
                "createdAt": formedNote.createdAt,
                "updatedAt": formedNote.updatedAt
            };
            expect(formedNote).toEqual(expectedNoteDetails)
        })
    });
    describe('formUpdatedNoteDetails()', () => {
        it('should return user json with given email and password', () => {
            const updatedNote = formUpdatedNoteDetails(note, 'update title', 'update content');
            const expectedUpdatedNoteDetails = {
                "email": '',
                "title": 'update title',
                "content": 'update content',
                "createdAt": note.createdAt,
                "updatedAt": updatedNote.updatedAt
            };
            expect(updatedNote).toEqual(expectedUpdatedNoteDetails)
        })
    });
    describe('hadEdited()', () => {
        it('should return true if found edited', () => {
            const response = hadEdited(note, 'edited', 'content');
            expect(response).toBeTruthy()
        });
        it('should return false if found not edited', () => {
            const response = hadEdited(note, 'title', 'content');
            expect(response).toBeFalsy()
        })
    })
});
