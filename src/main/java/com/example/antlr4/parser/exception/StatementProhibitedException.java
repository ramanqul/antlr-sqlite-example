package com.example.antlr4.parser.exception;

public class StatementProhibitedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StatementProhibitedException(String msg) {
        super(msg);
    }
    
}
