package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "country-type")
@XmlEnum
public enum Country {

    @XmlEnumValue("usa")
    USA("usa"),
    @XmlEnumValue("blr")
    BLR("blr"),
    @XmlEnumValue("aus")
    AUS("aus"),
    @XmlEnumValue("arg")
    ARG("arg"),
    @XmlEnumValue("bel")
    BEL("bel"),
    @XmlEnumValue("dnk")
    DNK("dnk");

    private final String name;

    Country(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Country getCountry(String name) throws ParseXMLException {
        for(Country country : Country.values()){
            if(country.getName().equals(name)){
                return country;
            }
        }
        throw new ParseXMLException("Country with name " + name + " doesn't exist");
    }
}
