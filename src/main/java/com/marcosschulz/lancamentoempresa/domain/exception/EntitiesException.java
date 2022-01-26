package com.marcosschulz.lancamentoempresa.domain.exception;

public class EntitiesException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public EntitiesException(String mensagem) {
        super(mensagem);
    }
}
