package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "type-card-type")
@XmlEnum
public enum TypeCard {

    @XmlEnumValue("advertising")
    ADVERTISING("advertising"),
    @XmlEnumValue("congratulatory")
    CONGRATULATORY("congratulatory"),
    @XmlEnumValue("artistic")
    ARTISTIC("artistic"),
    @XmlEnumValue("usual")
    USUAL("usual");

    private final String name;

    TypeCard(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static TypeCard getTypeCard(String name) throws ParseXMLException {
        for(TypeCard typeCard : TypeCard.values()){
            if(typeCard.getName().equals(name)){
                return typeCard;
            }
        }
        throw new ParseXMLException("TypeCard with name '" + name + "' doesn't exist");
    }
}
