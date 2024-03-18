package com.br.sga.ApplicationException;

public class EmptyListException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EmptyListException() {
        super("Lista vazia");
    }
}
