package org.chervyakovsky.parsing.builder.handler;

import org.chervyakovsky.parsing.exception.ParseXMLException;

public enum OldCardXmlAttribute {
    ID("id"),
    TYPE_CARD("type-card");

    private final String name;

    OldCardXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OldCardXmlAttribute getAttribute(String name) throws ParseXMLException {
        for (OldCardXmlAttribute attribute : OldCardXmlAttribute.values()) {
            if (attribute.getName().equals(name)) {
                return attribute;
            }
        }
        throw new ParseXMLException("Attribute with name " + name + " doesn't exist");
    }
}
