package org.chervyakovsky.parsing.builder.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.parsing.entity.DigitalCard;
import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.entity.PhysicalCard;
import org.chervyakovsky.parsing.entity.enumeration.*;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Year;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class OldCardHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DIGITAL_CARD_ELEMENT = OldCardXmlTag.DIGITAL_CARD.getName();
    private static final String PHYSICAL_CARD_ELEMENT = OldCardXmlTag.PHYSICAL_CARD.getName();
    private static final String ID_ATTRIBUTE = OldCardXmlAttribute.ID.getName();
    private static final String TYPE_CARD_ATTRIBUTE = OldCardXmlAttribute.TYPE_CARD.getName();

    private final Set<OldCardAbstract> oldCards;
    private OldCardAbstract currentOldCard;
    private OldCardXmlTag currentXmlTag;
    private final EnumSet<OldCardXmlTag> withText;

    public OldCardHandler() {
        oldCards = new HashSet<>();
        withText = EnumSet.range(OldCardXmlTag.IMAGE_THEME, OldCardXmlTag.LAST_NAME);
    }

    public Set<OldCardAbstract> getOldCards() {
        return oldCards;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (DIGITAL_CARD_ELEMENT.equals(qName) || PHYSICAL_CARD_ELEMENT.equals(qName)) {
            currentOldCard = DIGITAL_CARD_ELEMENT.equals(qName) ? new DigitalCard() : new PhysicalCard();
            if (attrs.getLength() == 2) {
                currentOldCard.setId(attrs.getValue(ID_ATTRIBUTE));
                String typeCard = attrs.getValue(TYPE_CARD_ATTRIBUTE);
                try {
                    currentOldCard.setTypeCard(TypeCard.getTypeCard(typeCard));
                } catch (ParseXMLException exception) {
                    LOGGER.warn(exception);
                }
            } else {
                currentOldCard.setId(attrs.getValue(ID_ATTRIBUTE));
                currentOldCard.setTypeCard(OldCardAbstract.DEFAULT_TYPE_CARD);
            }
        } else {
            try {
                OldCardXmlTag temp = OldCardXmlTag.getTag(qName);
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
            } catch (ParseXMLException exception) {
                LOGGER.warn("Unknown start element '" + qName + "'.", exception);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (DIGITAL_CARD_ELEMENT.equals(qName) || PHYSICAL_CARD_ELEMENT.equals(qName)) {
            oldCards.add(currentOldCard);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case IMAGE_THEME:
                    try {
                        currentOldCard.setImageTheme(ImageTheme.getImageTheme(data));
                    } catch (ParseXMLException exception) {
                        LOGGER.warn(exception);
                    }
                    break;
                case FIRST_NAME:
                    currentOldCard.getAuthor().setFirstName(data);
                    break;
                case LAST_NAME:
                    currentOldCard.getAuthor().setLastName(data);
                    break;
                case COUNTRY:
                    try {
                        currentOldCard.setCountry(Country.getCountry(data));
                    } catch (ParseXMLException exception) {
                        LOGGER.warn(exception);
                    }
                    break;
                case YEAR:
                    currentOldCard.setYear(Year.parse(data));
                    break;
                case TYPE_VALUABLE:
                    try {
                        currentOldCard.setTypeValuable(TypeValuable.getTypeValuable(data));
                    } catch (ParseXMLException exception) {
                        LOGGER.warn(exception);
                    }
                    break;
                case WAS_SENT:
                    currentOldCard.setWasSent(Boolean.parseBoolean(data));
                    break;
                case PICTURE_FORMAT:
                    try {
                        ((DigitalCard) currentOldCard).setPictureFormat(PictureFormat.getPictureFormat(data));
                    } catch (ParseXMLException exception) {
                        LOGGER.warn(exception);
                    }
                    break;
                case MATERIAL:
                    try {
                        ((PhysicalCard) currentOldCard).setMaterial(Material.getMaterial(data));
                    } catch (ParseXMLException exception) {
                        LOGGER.warn(exception);
                    }
                    break;
                case SIZE:
                    ((PhysicalCard) currentOldCard).setSize(data);
                    break;
                default:
                    LOGGER.error("Unknown tag " + currentXmlTag.name());
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }

        }
        currentXmlTag = null;
    }
}
