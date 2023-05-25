package io.nuwe.Hackaton_FundacionEsplai.model.exception;

public class UserDuplicatedException extends RuntimeException {

    public UserDuplicatedException(String message) {
        super(message);
    }

}