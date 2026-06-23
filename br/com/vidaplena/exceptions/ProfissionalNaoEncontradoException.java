package br.com.vidaplena.exceptions;

public class ProfissionalNaoEncontradoException extends Exception {
    public ProfissionalNaoEncontradoException(String msg) {
        super(msg);
    }

    public ProfissionalNaoEncontradoException(String msg, Throwable causa) {
        super(msg, causa);
    }
}