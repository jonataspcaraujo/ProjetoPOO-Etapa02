package br.com.vidaplena.exceptions;

public class OperacaoInvalidaException extends Exception {
    public OperacaoInvalidaException(String msg) {
        super(msg);
    }

    public OperacaoInvalidaException(String msg, Throwable causa) {
        super(msg, causa);
    }
}