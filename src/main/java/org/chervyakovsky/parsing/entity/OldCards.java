package org.chervyakovsky.parsing.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "old-cards")
public class OldCards {


    @XmlElements({
            @XmlElement(name = "digital-card", type = DigitalCard.class),
            @XmlElement(name = "physical-card", type = PhysicalCard.class)
    })
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
