package org.chervyakovsky.parsing.main;


import org.chervyakovsky.parsing.builder.AbstractOldCardBuilder;
import org.chervyakovsky.parsing.builder.impl.OldCardDomBuilder;
import org.chervyakovsky.parsing.builder.impl.OldCardSaxBuilder;
import org.chervyakovsky.parsing.builder.impl.OldCardStaxBuilder;
import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.exception.ParseXMLException;
import org.chervyakovsky.parsing.util.ResourcePathUtil;

import java.io.IOException;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws IOException, ParseXMLException {
        AbstractOldCardBuilder builder = new OldCardStaxBuilder();
        builder.buildSetOldCards("data/cards.xml", "data/cards.xsd");
        Set<OldCardAbstract> set = builder.getOldCards();
        for (OldCardAbstract card : set){
            System.out.println(card);
        }
    }
}
