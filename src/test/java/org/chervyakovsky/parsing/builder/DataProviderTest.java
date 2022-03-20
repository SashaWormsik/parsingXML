package org.chervyakovsky.parsing.builder;

import org.chervyakovsky.parsing.entity.DigitalCard;
import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.entity.PhysicalCard;
import org.chervyakovsky.parsing.entity.enumeration.*;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.testng.annotations.DataProvider;

import java.util.HashSet;
import java.util.Set;

public class DataProviderTest {

    @DataProvider(name = "data-provider")
    public Object[][] createData() throws ParseXMLException {
        Set<OldCardAbstract> setCards = new HashSet<>();
        setCards.add(createFirstDigitalCard());
        setCards.add(createSecondDigitalCard());
        setCards.add(createFirstPhysicalCard());
        setCards.add(createSecondPhysicalCard());
        Object[][] data = new Object[1][1];
        data[0][0] = setCards;
        return data;
    }

    private static DigitalCard createFirstDigitalCard() throws ParseXMLException {
        DigitalCard card = DigitalCard.getBuilder().
                id("a0000002").
                typeCard(TypeCard.CONGRATULATORY).
                imageTheme(ImageTheme.SPORT).
                author("Jeff", "Koons").
                country(Country.DNK).
                year("1999").
                typeValuable(TypeValuable.COLLECTIBLE).
                wasSent(true).
                pictureFormat(PictureFormat.PNG).
                buildDigitalCard();
        return card;
    }

    private static DigitalCard createSecondDigitalCard() throws ParseXMLException {
        DigitalCard card = DigitalCard.getBuilder().
                id("a0000007").
                typeCard(TypeCard.USUAL).
                imageTheme(ImageTheme.CITY).
                author("Claude", "Monet").
                country(Country.BEL).
                year("1987").
                typeValuable(TypeValuable.THEMATIC).
                wasSent(false).
                pictureFormat(PictureFormat.JPEG).
                buildDigitalCard();
        return card;
    }

    private static PhysicalCard createFirstPhysicalCard() throws ParseXMLException {
        PhysicalCard card = PhysicalCard.getBuilder().
                id("a0000012").
                typeCard(TypeCard.USUAL).
                imageTheme(ImageTheme.SPORT).
                author("Pablo", "Picasso").
                country(Country.AUS).
                year("2001").
                typeValuable(TypeValuable.NO_VALUE).
                wasSent(true).
                material(Material.PAPER).
                size("200x200").
                buildPhysicalCard();
        return card;
    }

    private static PhysicalCard createSecondPhysicalCard() throws ParseXMLException {
        PhysicalCard card = PhysicalCard.getBuilder().
                id("a0000016").
                typeCard(TypeCard.USUAL).
                imageTheme(ImageTheme.PEOPLE).
                author("Frida", "Kahlo").
                country(Country.DNK).
                year("2021").
                typeValuable(TypeValuable.HISTORICAL).
                wasSent(false).
                material(Material.PAPER).
                size("190x165").
                buildPhysicalCard();
        return card;
    }
}
