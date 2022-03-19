package org.chervyakovsky.parsing.entity;

import org.chervyakovsky.parsing.entity.enumeration.*;
import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.*;
import java.time.Year;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "digital-card-type", propOrder = {"pictureFormat"})
public class DigitalCard extends OldCardAbstract {

    @XmlElement(name = "picture-format", required = true)
    @XmlSchemaType(name = "string")
    private PictureFormat pictureFormat;

    public DigitalCard() {
    }

    public DigitalCard(String id, TypeCard typeCard, ImageTheme imageTheme, Author author, Country country, Year year, TypeValuable typeValuable, boolean wasSent, PictureFormat pictureFormat) {
        super(id, typeCard, imageTheme, author, country, year, typeValuable, wasSent);
        this.pictureFormat = pictureFormat;
    }

    public PictureFormat getPictureFormat() {
        return pictureFormat;
    }

    public void setPictureFormat(PictureFormat pictureFormat) {
        this.pictureFormat = pictureFormat;
    }

    public static DigitalCardBuilder getBuilder() { //FIXME
        return new DigitalCardBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!super.equals(o)) {
            return false;
        }
        DigitalCard that = (DigitalCard) o;
        return pictureFormat == that.pictureFormat;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + pictureFormat.hashCode();
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        StringBuilder stringBuilder = new StringBuilder(className);
        stringBuilder.append("{").
                append(super.toString()).
                append(", pictureFormat=").
                append(pictureFormat).append("}");
        return stringBuilder.toString();
    }

    public static class DigitalCardBuilder { //FIXME

        private DigitalCard card = new DigitalCard();

        private DigitalCardBuilder() {
        }

        public DigitalCardBuilder id(String id) {
            card.setId(id);
            return this;
        }

        public DigitalCardBuilder typeCard(TypeCard typeCard) {
            card.setTypeCard(typeCard);
            return this;
        }

        public DigitalCardBuilder imageTheme(ImageTheme imageTheme) {
            card.setImageTheme(imageTheme);
            return this;
        }

        public DigitalCardBuilder author(String firstName, String lastName) {
            card.setAuthor(new Author(firstName, lastName));
            return this;
        }

        public DigitalCardBuilder country(Country country) {
            card.setCountry(country);
            return this;
        }

        public DigitalCardBuilder year(String year) {
            card.setYear(Year.parse(year));
            return this;
        }

        public DigitalCardBuilder typeValuable(TypeValuable typeValuable) {
            card.setTypeValuable(typeValuable);
            return this;
        }

        public DigitalCardBuilder wasSent(boolean wasSent) {
            card.setWasSent(wasSent);
            return this;
        }

        public DigitalCardBuilder pictureFormat(PictureFormat pictureFormat) {
            card.setPictureFormat(pictureFormat);
            return this;
        }

        public DigitalCard buildDigitalCard() throws ParseXMLException {
            if (card.getId() == null) {
                throw new ParseXMLException("DigitalCard can not build without ID");
            }
            return card;
        }
    }
}
