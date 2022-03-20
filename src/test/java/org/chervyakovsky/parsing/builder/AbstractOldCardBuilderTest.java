package org.chervyakovsky.parsing.builder;

import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public abstract class AbstractOldCardBuilderTest {

    public static final String CORRECT_FILE = "data/cardsTest.xml";
    public static final String INCORRECT_FILE = "data/incorrectCardsTest.xml";
    public static final String SCHEMA = "data/cardsTest.xsd";

    protected AbstractOldCardBuilder builder;
    protected Set<OldCardAbstract> oldCards;


    @BeforeClass
    public abstract void buildSetOldCardsTest() throws ParseXMLException;

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void buildSetOldCardsTest(Set<OldCardAbstract> expected){
        Assert.assertEqualsDeep(oldCards, expected,null);
    }

    @Test(expectedExceptions = ParseXMLException.class,
            expectedExceptionsMessageRegExp = "File and schema are not valid " + INCORRECT_FILE + " " + SCHEMA)
    public void buildSetOldCardsWithIncorrectXmlTest() throws ParseXMLException {
        builder.buildSetOldCards(INCORRECT_FILE, SCHEMA);
    }
}
