package org.chervyakovsky.parsing.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.parsing.builder.handler.OldCardErrorHandler;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlFileValidatorSAX {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static XmlFileValidatorSAX instance;
    private SchemaFactory factory;

    private XmlFileValidatorSAX() {
        factory = SchemaFactory.newInstance(LANGUAGE);
    }

    public static XmlFileValidatorSAX getInstance() {
        if (instance == null) {
            instance = new XmlFileValidatorSAX();
        }
        return instance;
    }

    public boolean isMatchesScheme(String fileName, String schemaName) throws ParseXMLException {
        try {
            File schemaLocation = new File(schemaName);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(fileName));
            OldCardErrorHandler handler = new OldCardErrorHandler();
            validator.setErrorHandler(handler);
            validator.validate(source);
            if (handler.isInaccuracy()) {
                throw new SAXException(); // FIXME
            }
        } catch (SAXException exception) {
            LOGGER.warn("XML is invalid " + fileName, exception.getMessage());
            return false;
        } catch (IOException exception) {
            LOGGER.error("Can`t read file" + fileName, exception.getMessage());
            throw new ParseXMLException(exception);
        }
        return true;
    }
}
