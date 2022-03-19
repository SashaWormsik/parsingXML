package org.chervyakovsky.parsing.marshal;

import org.chervyakovsky.parsing.entity.OldCards;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;


public class UnMarshalJAXB {

    private static final String PATH_TO_XSD = "./src/main/resources/data/cards.xsd";
    private static final String PATH_TO_XML = "./src/main/resources/data/";

    public OldCards readXml(String fileName){
        OldCards oldCards = null;
        try {
            JAXBContext context = JAXBContext.newInstance(OldCards.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File fileXSD = new File(PATH_TO_XSD);
            Schema schema = factory.newSchema(fileXSD);
            unmarshaller.setSchema(schema);
            oldCards = (OldCards) unmarshaller.unmarshal(new File(PATH_TO_XML+fileName));

        } catch (JAXBException | SAXException e) {
            System.out.println(e.getMessage());
        }
        return oldCards;
    }

}
