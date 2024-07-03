package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

public enum Country {

    USA("usa"),
    BLR("blr"),
    AUS("aus"),
    ARG("arg"),
    BEL("bel"),
    DNK("dnk");

    private final String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Country getCountry(String name) throws ParseXMLException {
        for (Country country : Country.values()) {
            if (country.getName().equals(name)) {
                return country;
            }
        }
        throw new ParseXMLException("Country with name " + name + " doesn't exist");
    }
}
