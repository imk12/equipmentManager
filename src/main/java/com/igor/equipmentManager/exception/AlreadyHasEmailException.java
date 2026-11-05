package com.igor.equipmentManager.exception;

public class AlreadyHasEmailException extends RuntimeException {
    public AlreadyHasEmailException(String email) {
        super("O email " + email + " já foi cadastrado");
    }
}
