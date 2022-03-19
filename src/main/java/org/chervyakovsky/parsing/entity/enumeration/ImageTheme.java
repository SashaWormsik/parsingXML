package org.chervyakovsky.parsing.entity.enumeration;

import org.chervyakovsky.parsing.exception.ParseXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "image-theme-type")
@XmlEnum
public enum ImageTheme {

     @XmlEnumValue("nature")
     NATURE("nature"),
     @XmlEnumValue("people")
     PEOPLE("people"),
     @XmlEnumValue("sport")
     SPORT("sport"),
     @XmlEnumValue("architecture")
     ARCHITECTURE("architecture"),
     @XmlEnumValue("city")
     CITY("city"),
     @XmlEnumValue("animal")
     ANIMAL("animal"),
     @XmlEnumValue("car")
     CAR("car"),
     @XmlEnumValue("another-theme")
     ANOTHER_THEME("another-theme");

     private final String name;

     ImageTheme(String name) {
          this.name = name;
     }

     public String getName(){
          return name;
     }

     public static ImageTheme getImageTheme(String name) throws ParseXMLException {
          for(ImageTheme imageTheme : ImageTheme.values()){
               if(imageTheme.getName().equals(name)){
                    return imageTheme;
               }
          }
          throw new ParseXMLException("ImageTheme with name " + name + " doesn't exist");
     }
}
