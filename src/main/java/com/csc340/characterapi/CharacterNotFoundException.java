package com.csc340.characterapi.exception;

public class CharacterNotFoundException extends RuntimeException {

    public CharacterNotFoundException(Long characterId) {
        super("Character with id " + characterId + " was not found.");
    }
}
