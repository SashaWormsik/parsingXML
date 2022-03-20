package org.chervyakovsky.parsing.exception;

public class ParseXMLException extends Exception {

    public ParseXMLException() {
        super();
    }

    public ParseXMLException(String message) {
        super(message);
    }

    public ParseXMLException(String message, Exception exception) {
        super(message, exception);
    }

    public ParseXMLException(Exception exception) {
        super(exception);
    }
}
