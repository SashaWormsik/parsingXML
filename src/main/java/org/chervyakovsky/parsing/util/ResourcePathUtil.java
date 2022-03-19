package org.chervyakovsky.parsing.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.chervyakovsky.parsing.validator.XmlFileValidatorSAX;

import java.net.URL;

public class ResourcePathUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    public static String getResourcePath(String resourceName) throws ParseXMLException {
        final int startPosition = 6;
        ClassLoader loader = XmlFileValidatorSAX.class.getClassLoader();
        URL resource = loader.getResource(resourceName);
        if (resource == null) {
            LOGGER.info("Resource " + resourceName + " is not found");
            throw new ParseXMLException("Resource " + resourceName + " is not found");
        }
        return resource.toString().substring(startPosition);
    }
}
