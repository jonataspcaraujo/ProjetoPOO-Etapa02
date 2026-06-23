package br.com.vidaplena.exceptions;

public class HorarioIndisponivelException extends Exception {
    public HorarioIndisponivelException(String msg) {
        super(msg);
    }

    public HorarioIndisponivelException(String msg, Throwable causa) {
        super(msg, causa);
    }
}