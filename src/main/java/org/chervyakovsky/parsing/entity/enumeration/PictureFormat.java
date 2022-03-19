package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "picture-format-type")
@XmlEnum
public enum PictureFormat {

    @XmlEnumValue("jpeg")
    JPEG("jpeg"),
    @XmlEnumValue("png")
    PNG("png"),
    @XmlEnumValue("gif")
    GIF("gif");

    private final String name;

    PictureFormat(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static PictureFormat getPictureFormat(String name) throws ParseXMLException {
        for(PictureFormat pictureFormat : PictureFormat.values()){
            if(pictureFormat.getName().equals(name)){
                return pictureFormat;
            }
        }
        throw new ParseXMLException("PictureFormat with name '" + name + "' doesn't exist");
    }
}
