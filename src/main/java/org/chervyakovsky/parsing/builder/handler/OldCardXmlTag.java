package org.chervyakovsky.parsing.builder.handler;

import org.chervyakovsky.parsing.exception.ParseXMLException;

public enum OldCardXmlTag {
    OLD_CARDS("old-cards"),
    AUTHOR("author"),
    DIGITAL_CARD("digital-card"),
    PHYSICAL_CARD("physical-card"),
    IMAGE_THEME("image-theme"),
    COUNTRY("country"),
    YEAR("year"),
    TYPE_VALUABLE("type-valuable"),
    WAS_SENT("was-sent"),
    PICTURE_FORMAT("picture-format"),
    MATERIAL("material"),
    SIZE("size"),
    FIRST_NAME("first-name"),
    LAST_NAME("last-name");

    private final String name;

    OldCardXmlTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OldCardXmlTag getTag(String name) throws ParseXMLException {
        for (OldCardXmlTag tag : OldCardXmlTag.values()) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        throw new ParseXMLException("Tag with name " + name + " doesn't exist");
    }

}
