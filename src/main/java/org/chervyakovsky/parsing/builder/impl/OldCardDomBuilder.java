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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Year;

public class OldCardDomBuilder extends AbstractOldCardBuilder {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DIGITAL_CARD_ELEMENT = OldCardXmlTag.DIGITAL_CARD.getName();
    private static final String PHYSICAL_CARD_ELEMENT = OldCardXmlTag.PHYSICAL_CARD.getName();

    private DocumentBuilder documentBuilder;

    public OldCardDomBuilder() throws ParseXMLException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            LOGGER.error("OldCardDomBuilder not created", exception);
            throw new ParseXMLException(exception);
        }
    }


    @Override
    public void buildSetOldCards(String xmlName, String xsdName) throws ParseXMLException {
        String xmlLocation = ResourcePathUtil.getResourcePath(xmlName);
        String xsdLocation = ResourcePathUtil.getResourcePath(xsdName);
        XmlFileValidatorSAX validatorSAX = XmlFileValidatorSAX.getInstance();
        if (validatorSAX.isMatchesScheme(xmlLocation, xsdLocation)) {
            Document document;
            try {
                document = documentBuilder.parse(xmlLocation);
                Element root = document.getDocumentElement();
                NodeList digitalOldCards = root.getElementsByTagName(DIGITAL_CARD_ELEMENT);
                NodeList physicalOldCards = root.getElementsByTagName(PHYSICAL_CARD_ELEMENT);

                for (int i = 0; i < digitalOldCards.getLength(); i++) {
                    Element element = (Element) digitalOldCards.item(i);
                    OldCardAbstract oldCard = buildDigitalOldCard(element);
                    oldCards.add(oldCard);
                }

                for (int i = 0; i < physicalOldCards.getLength(); i++) {
                    Element element = (Element) physicalOldCards.item(i);
                    OldCardAbstract oldCard = buildPhysicalOldCard(element);
                    oldCards.add(oldCard);
                }
            } catch (SAXException | IOException | ParseXMLException exception) {
                LOGGER.error("Error building a Set of OldCards", exception);
                throw new ParseXMLException(exception);
            }
        } else {
            LOGGER.log(Level.ERROR, "File {} does not match schema {}", xmlName, xsdName);
            throw new ParseXMLException("File and schema are not valid " + xmlName + " " + xsdName);
        }
    }

    private OldCardAbstract buildDigitalOldCard(Element oldCardElement) throws ParseXMLException {
        DigitalCard digitalCard = new DigitalCard();
        buildOldCard(oldCardElement, digitalCard);
        digitalCard.setPictureFormat(getElementPictureFormat(oldCardElement));
        return digitalCard;
    }

    private OldCardAbstract buildPhysicalOldCard(Element oldCardElement) throws ParseXMLException {
        PhysicalCard physicalCard = new PhysicalCard();
        buildOldCard(oldCardElement, physicalCard);
        physicalCard.setSize(getElementTextContent(oldCardElement, OldCardXmlTag.SIZE.getName()));
        physicalCard.setMaterial(getElementMaterial(oldCardElement));
        return physicalCard;
    }

    private void buildOldCard(Element oldCardElement, OldCardAbstract oldCard) throws ParseXMLException {
        oldCard.setId(oldCardElement.getAttribute(OldCardXmlAttribute.ID.getName()));
        oldCard.setTypeCard(getElementTypeCard(oldCardElement));
        oldCard.setAuthor(buildAuthor(oldCardElement));
        oldCard.setCountry(getElementCountry(oldCardElement));
        oldCard.setYear(getElementYear(oldCardElement));
        oldCard.setTypeValuable(getElementTypeValuable(oldCardElement));
        oldCard.setWasSent(getElementWasSent(oldCardElement));
        oldCard.setImageTheme(getElementImageType(oldCardElement));
    }

    private Author buildAuthor(Element oldCardElement) {
        Author author = new Author();
        Element elementAuthor = (Element) oldCardElement.
                getElementsByTagName(OldCardXmlTag.AUTHOR.getName()).
                item(0);
        author.setFirstName(getElementTextContent(elementAuthor, OldCardXmlTag.FIRST_NAME.getName()));
        author.setLastName(getElementTextContent(elementAuthor, OldCardXmlTag.LAST_NAME.getName()));
        return author;
    }

    private TypeCard getElementTypeCard(Element element) throws ParseXMLException {
        if (element.hasAttribute(OldCardXmlAttribute.TYPE_CARD.getName())) {
            String typeCard = element.getAttribute(OldCardXmlAttribute.TYPE_CARD.getName());
            return TypeCard.getTypeCard(typeCard);
        } else {
            return OldCardAbstract.DEFAULT_TYPE_CARD;
        }
    }

    private ImageTheme getElementImageType(Element element) throws ParseXMLException {
        String typeName = getElementTextContent(element, OldCardXmlTag.IMAGE_THEME.getName());
        return ImageTheme.getImageTheme(typeName);
    }

    private Country getElementCountry(Element element) throws ParseXMLException {
        String country = getElementTextContent(element, OldCardXmlTag.COUNTRY.getName());
        return Country.getCountry(country);
    }

    private TypeValuable getElementTypeValuable(Element element) throws ParseXMLException {
        String typeValuable = getElementTextContent(element, OldCardXmlTag.TYPE_VALUABLE.getName());
        return TypeValuable.getTypeValuable(typeValuable);
    }

    private Material getElementMaterial(Element element) throws ParseXMLException {
        String material = getElementTextContent(element, OldCardXmlTag.MATERIAL.getName());
        return Material.getMaterial(material);
    }

    private PictureFormat getElementPictureFormat(Element element) throws ParseXMLException {
        String format = getElementTextContent(element, OldCardXmlTag.PICTURE_FORMAT.getName());
        return PictureFormat.getPictureFormat(format);
    }

    private Year getElementYear(Element element) {
        String year = getElementTextContent(element, OldCardXmlTag.YEAR.getName());
        return Year.parse(year);
    }

    private boolean getElementWasSent(Element element) {
        String wasSent = getElementTextContent(element, OldCardXmlTag.WAS_SENT.getName());
        return Boolean.parseBoolean(wasSent);
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
