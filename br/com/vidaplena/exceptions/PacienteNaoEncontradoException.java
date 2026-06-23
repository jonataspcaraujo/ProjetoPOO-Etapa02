package br.com.vidaplena.exceptions;

public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException(String msg){
        super(msg);
    }

    public PacienteNaoEncontradoException(String msg, Throwable causa){
        super(msg, causa);
    }
}