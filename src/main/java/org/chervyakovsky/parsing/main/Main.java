package org.chervyakovsky.parsing.main;



import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.chervyakovsky.parsing.util.ResourcePathUtil;
import org.chervyakovsky.parsing.validator.XmlFileValidatorSAX;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ParseXMLException {
        XmlFileValidatorSAX validatorSAX = XmlFileValidatorSAX.getInstance();
        System.out.println(validatorSAX.validate("./src/main/resources/data/cards.xml", "./src/main/resources/data/cards.xsd"));

    }
}
