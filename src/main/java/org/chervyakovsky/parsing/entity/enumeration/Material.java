package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "material-type")
@XmlEnum
public enum Material {

    @XmlEnumValue("paper")
    PAPER("paper"),
    @XmlEnumValue("carton")
    CARTON("carton");

    private final String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Material getMaterial(String name) throws ParseXMLException {
        for(Material material : Material.values()){
            if(material.getName().equals(name)){
                return material;
            }
        }
        throw new ParseXMLException("Material with name '" + name + "' doesn't exist");
    }
}
