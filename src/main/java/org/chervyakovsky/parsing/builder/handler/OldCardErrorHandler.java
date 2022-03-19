package org.chervyakovsky.parsing.builder.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class OldCardErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger();
    private boolean inaccuracy;

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        LOGGER.warn(getLineAddress(exception) + "-" + exception.getMessage());
        inaccuracy = true;
        //throw new SAXException(); // FIXME

    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        LOGGER.error(getLineAddress(exception) + "-" + exception.getMessage());
        inaccuracy = true;
        //throw new SAXException();
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        LOGGER.fatal(getLineAddress(exception) + "-" + exception.getMessage());
        inaccuracy = true;
        //throw new SAXException();
    }

    public boolean isInaccuracy() {
        return inaccuracy;
    }

    private String getLineAddress(SAXParseException exception) {
        return "line: " + exception.getLineNumber() + "| column: " + exception.getColumnNumber();
    }
}
