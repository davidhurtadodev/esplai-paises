package io.nuwe.Hackaton_FundacionEsplai.model.exception;

public class EmailDuplicatedException extends RuntimeException {

    public EmailDuplicatedException(String message) {
        super(message);
    }

}
