package org.chervyakovsky.parsing.builder;

import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.exception.ParseXMLException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractOldCardBuilder {

    protected Set<OldCardAbstract> oldCards;

    public AbstractOldCardBuilder() {
        this.oldCards = new HashSet<>();
    }

    public AbstractOldCardBuilder(Set<OldCardAbstract> oldCards) {
        this.oldCards = oldCards;
    }

    public Set<OldCardAbstract> getOldCards() {
        return oldCards;
    }

    public abstract void buildSetOldCards(String xmlName, String xsdName) throws ParseXMLException;
}
