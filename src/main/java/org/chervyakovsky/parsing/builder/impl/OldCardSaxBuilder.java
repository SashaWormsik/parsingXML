package org.chervyakovsky.parsing.builder.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.parsing.builder.AbstractOldCardBuilder;
import org.chervyakovsky.parsing.builder.handler.OldCardHandler;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.chervyakovsky.parsing.util.ResourcePathUtil;
import org.chervyakovsky.parsing.validator.XmlFileValidatorSAX;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class OldCardSaxBuilder extends AbstractOldCardBuilder {

    private static final Logger LOGGER = LogManager.getLogger();

    private final OldCardHandler handler = new OldCardHandler();
    private XMLReader reader;

    public OldCardSaxBuilder() throws ParseXMLException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new OldCardHandler());
        } catch (SAXException | ParserConfigurationException exception) {
            LOGGER.error("OldCardSaxBuilder not created", exception);
            throw new ParseXMLException(exception);
        }
    }

    @Override
    public void buildSetOldCards(String xmlName, String xsdName) throws ParseXMLException {
        String xmlLocation = ResourcePathUtil.getResourcePath(xmlName);
        String xsdLocation = ResourcePathUtil.getResourcePath(xsdName);
        XmlFileValidatorSAX validatorSAX = XmlFileValidatorSAX.getInstance();
        if (validatorSAX.validate(xmlLocation, xsdLocation)) {
            try {
                reader.parse(xmlName);
            } catch (IOException | SAXException exception) {
                LOGGER.error("Error building a Set of OldCards", exception);
                throw new ParseXMLException(exception);
            }
            oldCards = handler.getOldCards();
        } else {
            LOGGER.log(Level.ERROR, "File {} does not match schema {}", xmlName, xsdName);
            throw new ParseXMLException("File and schema are not valid " + xmlName + " " + xsdName);
        }
    }
}
