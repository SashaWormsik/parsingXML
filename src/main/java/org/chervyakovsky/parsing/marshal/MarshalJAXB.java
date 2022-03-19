package org.chervyakovsky.parsing.marshal;

import org.chervyakovsky.parsing.entity.OldCardAbstract;
import org.chervyakovsky.parsing.entity.OldCards;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;

public class MarshalJAXB {

    private static final String PATH_TO_FILE_TO_WRITE = "./src/main/resources/data/cards_marsh.xml";

    public boolean writeXmlFile(Set<OldCardAbstract> oldCardsSet) {
        try {
            JAXBContext context = JAXBContext.newInstance(OldCards.class);
            Marshaller marshaller = context.createMarshaller();
            OldCards oldCards = new OldCards();
           for (OldCardAbstract card : oldCardsSet){
               oldCards.add(card);
           }
           marshaller.marshal(oldCards, new FileOutputStream(PATH_TO_FILE_TO_WRITE));
        }catch (JAXBException | FileNotFoundException exception){
            System.out.println(exception);
            return false;
        }
        return true;
    }

}
