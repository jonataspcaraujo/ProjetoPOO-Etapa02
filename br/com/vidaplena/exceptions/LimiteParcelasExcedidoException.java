package br.com.vidaplena.exceptions;

public class LimiteParcelasExcedidoException extends Exception {
    public LimiteParcelasExcedidoException(String msg) {
        super(msg);
    }

    public LimiteParcelasExcedidoException(String msg, Throwable causa) {
        super(msg, causa);
    }
}