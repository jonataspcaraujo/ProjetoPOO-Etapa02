package br.com.vidaplena.exceptions;

public class PacienteInativoException extends Exception {
    public PacienteInativoException(String msg) {
        super(msg);
    }

    public PacienteInativoException(String msg, Throwable causa) {
        super(msg, causa);
    }
}