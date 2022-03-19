package org.chervyakovsky.parsing.entity;

import org.chervyakovsky.parsing.adapter.YearAdapter;
import org.chervyakovsky.parsing.entity.enumeration.Country;
import org.chervyakovsky.parsing.entity.enumeration.ImageTheme;
import org.chervyakovsky.parsing.entity.enumeration.TypeCard;
import org.chervyakovsky.parsing.entity.enumeration.TypeValuable;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Year;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "old-card-type", propOrder = {
        "imageTheme",
        "author",
        "country",
        "year",
        "typeValuable",
        "wasSent"
})
@XmlSeeAlso({
        PhysicalCard.class,
        DigitalCard.class
})
public abstract class OldCardAbstract {

    public static final TypeCard DEFAULT_TYPE_CARD = TypeCard.USUAL;

    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String id;

    @XmlAttribute(name = "type-card")
    private TypeCard typeCard;

    @XmlElement(name = "image-theme", required = true)
    @XmlSchemaType(name = "string")
    private ImageTheme imageTheme;

    @XmlElement(required = true)
    private Author author;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private Country country;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(YearAdapter.class)
    @XmlSchemaType(name = "gYear")
    private Year year;

    @XmlElement(name = "type-valuable", required = true, defaultValue = "no-value")
    @XmlSchemaType(name = "string")
    private TypeValuable typeValuable;

    @XmlElement(name = "was-sent")
    private boolean wasSent;

    public OldCardAbstract() {
        this.author = new Author();
    }

    public OldCardAbstract(String id, TypeCard typeCard, ImageTheme imageTheme, Author author, Country country, Year year, TypeValuable typeValuable, boolean wasSent) {
        this.id = id;
        this.typeCard = typeCard;
        this.imageTheme = imageTheme;
        this.author = author;
        this.country = country;
        this.year = year;
        this.typeValuable = typeValuable;
        this.wasSent = wasSent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeCard getTypeCard() { //FIXME
        if (typeCard == null) {
            return TypeCard.USUAL;
        } else {
            return typeCard;
        }
    }

    public void setTypeCard(TypeCard typeCard) {
        this.typeCard = typeCard;
    }

    public ImageTheme getImageTheme() {
        return imageTheme;
    }

    public void setImageTheme(ImageTheme imageTheme) {
        this.imageTheme = imageTheme;
    }

    public Author getAuthor() {
        return new Author(this.author.getFirstName(), this.author.getLastName());
    }

    public void setAuthor(Author author) {
        this.author.setLastName(author.getLastName());
        this.author.setFirstName(author.getFirstName());
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public TypeValuable getTypeValuable() { //FIXME
        if (typeValuable == null) {
            return TypeValuable.NO_VALUE;
        }
        return typeValuable;
    }

    public void setTypeValuable(TypeValuable typeValuable) {
        this.typeValuable = typeValuable;
    }

    public boolean isWasSent() {
        return wasSent;
    }

    public void setWasSent(boolean wasSent) {
        this.wasSent = wasSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        OldCardAbstract that = (OldCardAbstract) o;
        return wasSent == that.wasSent &&
                id.equals(that.id) &&
                typeCard == that.typeCard &&
                imageTheme == that.imageTheme &&
                author.equals(that.author) &&
                country == that.country &&
                year.equals(that.year) &&
                typeValuable == that.typeValuable;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode() + typeCard.hashCode() + imageTheme.hashCode() + author.hashCode() + country.hashCode() + year.hashCode() + typeValuable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append(id).
                append(", typeCard= ").append(typeCard).
                append(", imageTheme= ").append(imageTheme).
                append(", Author= ").append(author).
                append(", country= ").append(country).
                append(", year=").append(year).
                append(", typeValuable= ").append(typeValuable).
                append(", wasSent= ").append(wasSent);
        return stringBuilder.toString();
    }
}
