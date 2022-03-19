package org.chervyakovsky.parsing.entity;

import org.chervyakovsky.parsing.entity.enumeration.*;
import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.*;
import java.time.Year;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "physical-card-type", propOrder = {
        "material",
        "size"
})
public class PhysicalCard extends OldCardAbstract {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private Material material;

    @XmlElement(required = true)
    private String size;

    public PhysicalCard() {
    }

    public PhysicalCard(String id, TypeCard typeCard, ImageTheme imageTheme, Author author, Country country, Year year, TypeValuable typeValuable, boolean wasSent, Material material, String size) {
        super(id, typeCard, imageTheme, author, country, year, typeValuable, wasSent);
        this.material = material;
        this.size = size;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!super.equals(o)) {
            return false;
        }
        PhysicalCard that = (PhysicalCard) o;
        return material == that.material &&
                size.equals(that.size);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + material.hashCode() + size.hashCode();
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        StringBuilder stringBuilder = new StringBuilder(className);
        stringBuilder.append("{").
                append(", material=").
                append(material).
                append(", size=").
                append(size).append("}");
        return stringBuilder.toString();
    }

    public static class PhysicalCardBuilder { //FIXME

        private PhysicalCard card = new PhysicalCard();

        private PhysicalCardBuilder() {
        }

        public PhysicalCardBuilder id(String id) {
            card.setId(id);
            return this;
        }

        public PhysicalCardBuilder typeCard(TypeCard typeCard) {
            card.setTypeCard(typeCard);
            return this;
        }

        public PhysicalCardBuilder imageTheme(ImageTheme imageTheme) {
            card.setImageTheme(imageTheme);
            return this;
        }

        public PhysicalCardBuilder author(String firstName, String lastName) {
            card.setAuthor(new Author(firstName, lastName));
            return this;
        }

        public PhysicalCardBuilder country(Country country) {
            card.setCountry(country);
            return this;
        }

        public PhysicalCardBuilder year(String year) {
            card.setYear(Year.parse(year));
            return this;
        }

        public PhysicalCardBuilder typeValuable(TypeValuable typeValuable) {
            card.setTypeValuable(typeValuable);
            return this;
        }

        public PhysicalCardBuilder wasSent(boolean wasSent) {
            card.setWasSent(wasSent);
            return this;
        }

        public PhysicalCardBuilder material(Material material) {
            card.setMaterial(material);
            return this;
        }

        public PhysicalCardBuilder size(String size) {
            card.setSize(size);
            return this;
        }

        public PhysicalCard buildPhysicalCard() throws ParseXMLException {
            if (card.getId() == null) {
                throw new ParseXMLException("PhysicalCard can not build without ID");
            }
            return card;
        }
    }
}
