package br.com.vidaplena.exceptions;

public class ConsultaInvalidaException extends Exception {
    public ConsultaInvalidaException(String msg) {
        super(msg);
    }

    public ConsultaInvalidaException(String msg, Throwable causa) {
        super(msg, causa);
    }
}