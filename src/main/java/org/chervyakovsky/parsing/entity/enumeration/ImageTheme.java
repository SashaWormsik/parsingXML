package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;


public enum ImageTheme {

    NATURE("nature"),
    PEOPLE("people"),
    SPORT("sport"),
    ARCHITECTURE("architecture"),
    CITY("city"),
    ANIMAL("animal"),
    CAR("car"),
    ANOTHER_THEME("another-theme");

    private final String name;

    ImageTheme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ImageTheme getImageTheme(String name) throws ParseXMLException {
        for (ImageTheme imageTheme : ImageTheme.values()) {
            if (imageTheme.getName().equals(name)) {
                return imageTheme;
            }
        }
        throw new ParseXMLException("ImageTheme with name " + name + " doesn't exist");
    }
}
