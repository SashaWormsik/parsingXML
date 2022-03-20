package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

public enum TypeCard {

    ADVERTISING("advertising"),
    CONGRATULATORY("congratulatory"),
    ARTISTIC("artistic"),
    USUAL("usual");

    private final String name;

    TypeCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TypeCard getTypeCard(String name) throws ParseXMLException {
        for (TypeCard typeCard : TypeCard.values()) {
            if (typeCard.getName().equals(name)) {
                return typeCard;
            }
        }
        throw new ParseXMLException("TypeCard with name '" + name + "' doesn't exist");
    }
}
