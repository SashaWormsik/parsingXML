package org.chervyakovsky.parsing.entity;

import java.util.HashSet;
import java.util.Set;


public class OldCards {

    private Set<OldCardAbstract> oldCards;


    public OldCards() {
        this.oldCards = new HashSet<>();
    }

    public void setOldCards(Set<OldCardAbstract> oldCards) {
        this.oldCards = oldCards;
    }

    public void add(OldCardAbstract oldCard) {
        this.oldCards.add(oldCard);
    }

    public Set<OldCardAbstract> getOldCards() {
        return oldCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        OldCards that = (OldCards) o;
        return oldCards.equals(that);
    }

    @Override
    public int hashCode() {
        return oldCards.hashCode();
    }
}
