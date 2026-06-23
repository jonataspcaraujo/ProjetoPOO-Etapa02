package br.com.vidaplena.exceptions;

public class ConvenioNaoCobreException extends Exception {
    public ConvenioNaoCobreException(String msg) {
        super(msg);
    }

    public ConvenioNaoCobreException(String msg, Throwable causa) {
        super(msg, causa);
    }
}