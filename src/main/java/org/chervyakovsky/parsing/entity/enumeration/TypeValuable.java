package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.XmlEnumValue;

public enum TypeValuable {
    @XmlEnumValue("historical")
    HISTORICAL("historical"),
    @XmlEnumValue("collectible")
    COLLECTIBLE("collectible"),
    @XmlEnumValue("thematic")
    THEMATIC("thematic"),
    @XmlEnumValue("no-value")
    NO_VALUE("no-value");

    private final String name;

    TypeValuable(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static TypeValuable getTypeValuable(String name) throws ParseXMLException {
        for(TypeValuable typeValuable : TypeValuable.values()){
            if(typeValuable.getName().equals(name)){
                return typeValuable;
            }
        }
        throw new ParseXMLException("TypeValuable with name '" + name + "' doesn't exist");
    }
}
