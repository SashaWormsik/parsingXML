package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;


public enum Material {

    PAPER("paper"),
    CARTON("carton");

    private final String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Material getMaterial(String name) throws ParseXMLException {
        for (Material material : Material.values()) {
            if (material.getName().equals(name)) {
                return material;
            }
        }
        throw new ParseXMLException("Material with name '" + name + "' doesn't exist");
    }
}
