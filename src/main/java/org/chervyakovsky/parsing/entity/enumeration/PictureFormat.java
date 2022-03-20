package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;


public enum PictureFormat {

    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private final String name;

    PictureFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PictureFormat getPictureFormat(String name) throws ParseXMLException {
        for (PictureFormat pictureFormat : PictureFormat.values()) {
            if (pictureFormat.getName().equals(name)) {
                return pictureFormat;
            }
        }
        throw new ParseXMLException("PictureFormat with name '" + name + "' doesn't exist");
    }
}
