package org.chervyakovsky.parsing.builder.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.parsing.builder.AbstractOldCardBuilder;
import org.chervyakovsky.parsing.builder.handler.OldCardXmlAttribute;
import org.chervyakovsky.parsing.builder.handler.OldCardXmlTag;
import org.chervyakovsky.parsing.entity.Author;
import org.chervyakovsky.parsing.entity.DigitalCard;
import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.entity.PhysicalCard;
import org.chervyakovsky.parsing.entity.enumeration.*;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.chervyakovsky.parsing.util.ResourcePathUtil;
import org.chervyakovsky.parsing.validator.XmlFileValidatorSAX;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Year;

public class OldCardStaxBuilder extends AbstractOldCardBuilder {

    private static final Logger LOGGER = LogManager.getLogger();


    private static final String DIGITAL_CARD_ELEMENT = OldCardXmlTag.DIGITAL_CARD.getName();
    private static final String PHYSICAL_CARD_ELEMENT = OldCardXmlTag.PHYSICAL_CARD.getName();
    private static final String ID_ATTRIBUTE = OldCardXmlAttribute.ID.getName();
    private static final String TYPE_CARD_ATTRIBUTE = OldCardXmlAttribute.TYPE_CARD.getName();

    private final XMLInputFactory inputFactory;

    public OldCardStaxBuilder() {
        this.inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetOldCards(String xmlName, String xsdName) throws ParseXMLException {
        String xmlLocation = ResourcePathUtil.getResourcePath(xmlName);
        String xsdLocation = ResourcePathUtil.getResourcePath(xsdName);
        XmlFileValidatorSAX validatorSAX = XmlFileValidatorSAX.getInstance();
        if (validatorSAX.validate(xmlLocation, xsdLocation)) {
            String name;
            try (FileInputStream inputStream = new FileInputStream(new File(xmlName))) {
                XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

                while (reader.hasNext()) {
                    int type = reader.next();
                    if (type == XMLStreamConstants.START_ELEMENT) {
                        name = reader.getLocalName();
                        if (name.equals(DIGITAL_CARD_ELEMENT)) {
                            OldCardAbstract oldCard = new DigitalCard();
                            buildOldCard(reader, oldCard);
                            oldCards.add(oldCard);
                        }
                        if (name.equals(PHYSICAL_CARD_ELEMENT)) {
                            OldCardAbstract oldCard = new PhysicalCard();
                            buildOldCard(reader, oldCard);
                            oldCards.add(oldCard);
                        }
                    }
                }
            } catch (IOException | XMLStreamException | ParseXMLException exception) {
                LOGGER.error("Error building a Set of OldCards", exception);
                throw new ParseXMLException(exception);
            }
        } else {
            LOGGER.log(Level.ERROR, "File {} does not match schema {}", xmlName, xsdName);
            throw new ParseXMLException("File and schema are not valid " + xmlName + " " + xsdName);
        }

    }

    private OldCardAbstract buildOldCard(XMLStreamReader reader, OldCardAbstract oldCardAbstract) throws XMLStreamException, ParseXMLException {
        String oldCardId = reader.getAttributeValue(null, ID_ATTRIBUTE);
        String typeCard = reader.getAttributeValue(null, TYPE_CARD_ATTRIBUTE);

        oldCardAbstract.setId(oldCardId);
        oldCardAbstract.setTypeCard(typeCard != null ? TypeCard.getTypeCard(typeCard) : OldCardAbstract.DEFAULT_TYPE_CARD);

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    OldCardXmlTag tag = OldCardXmlTag.getTag(name);
                    String data = getXMLText(reader);
                    switch (tag) {
                        case IMAGE_THEME:
                            oldCardAbstract.setImageTheme(ImageTheme.getImageTheme(data));
                            break;
                        case AUTHOR:
                            oldCardAbstract.setAuthor(builtAuthor(reader));
                            break;
                        case COUNTRY:
                            oldCardAbstract.setCountry(Country.getCountry(data));
                            break;
                        case YEAR:
                            oldCardAbstract.setYear(Year.parse(data));
                            break;
                        case TYPE_VALUABLE:
                            oldCardAbstract.setTypeValuable(TypeValuable.getTypeValuable(data));
                            break;
                        case WAS_SENT:
                            oldCardAbstract.setWasSent(Boolean.parseBoolean(data));
                            break;
                        case PICTURE_FORMAT:
                            ((DigitalCard) oldCardAbstract).setPictureFormat(PictureFormat.getPictureFormat(data));
                            break;
                        case MATERIAL:
                            ((PhysicalCard) oldCardAbstract).setMaterial(Material.getMaterial(data));
                            break;
                        case SIZE:
                            ((PhysicalCard) oldCardAbstract).setSize(data);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals(DIGITAL_CARD_ELEMENT) || name.equals(PHYSICAL_CARD_ELEMENT)) {
                        return oldCardAbstract;
                    }
                    break;
            }

        }
        throw new XMLStreamException("Unknown element");
    }

    private Author builtAuthor(XMLStreamReader reader) throws XMLStreamException, ParseXMLException {
        Author author = new Author();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    OldCardXmlTag tag = OldCardXmlTag.getTag(name);
                    String data = getXMLText(reader);
                    switch (tag) {
                        case FIRST_NAME:
                            author.setFirstName(data);
                            break;
                        case LAST_NAME:
                            author.setLastName(data);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals(OldCardXmlTag.AUTHOR.getName())) {
                        return author;
                    }

            }
        }
        throw new XMLStreamException("Unknown element");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
