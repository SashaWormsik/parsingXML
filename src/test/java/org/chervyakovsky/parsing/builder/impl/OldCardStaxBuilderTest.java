package org.chervyakovsky.parsing.builder.impl;

import org.chervyakovsky.parsing.builder.AbstractOldCardBuilderTest;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OldCardStaxBuilderTest extends AbstractOldCardBuilderTest {

    @Override
    @BeforeClass
    public void buildSetOldCardsTest() throws ParseXMLException {
        builder = new OldCardStaxBuilder();
        builder.buildSetOldCards(CORRECT_FILE, SCHEMA);
        oldCards = builder.getOldCards();
    }
}
