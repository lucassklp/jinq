package com.github.lucassklp.jinq.exceptions;

public class NoElementException extends Exception {
    public NoElementException(){
        super("No element was found in list.");
    }
}
