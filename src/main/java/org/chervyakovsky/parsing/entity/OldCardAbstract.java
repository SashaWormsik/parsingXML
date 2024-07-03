package org.chervyakovsky.parsing.entity;

import org.chervyakovsky.parsing.entity.enumeration.Country;
import org.chervyakovsky.parsing.entity.enumeration.ImageTheme;
import org.chervyakovsky.parsing.entity.enumeration.TypeCard;
import org.chervyakovsky.parsing.entity.enumeration.TypeValuable;

import java.time.Year;

public abstract class OldCardAbstract {

    public static final TypeCard DEFAULT_TYPE_CARD = TypeCard.USUAL;

    private String id;
    private TypeCard typeCard;
    private ImageTheme imageTheme;
    private Author author;
    private Country country;
    private Year year;
    private TypeValuable typeValuable;
    private boolean wasSent;

    public OldCardAbstract() {
        this.author = new Author();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeCard getTypeCard() {
        return typeCard;
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

    public TypeValuable getTypeValuable() {
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
        result = id.hashCode()
                + typeCard.hashCode()
                + imageTheme.hashCode()
                + author.hashCode()
                + country.hashCode()
                + year.hashCode()
                + typeValuable.hashCode();
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
