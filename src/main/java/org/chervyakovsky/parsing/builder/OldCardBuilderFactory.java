package org.chervyakovsky.parsing.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.parsing.builder.impl.OldCardDomBuilder;
import org.chervyakovsky.parsing.builder.impl.OldCardSaxBuilder;
import org.chervyakovsky.parsing.builder.impl.OldCardStaxBuilder;
import org.chervyakovsky.parsing.exception.ParseXMLException;

public class OldCardBuilderFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private enum ParserType {
        DOM, SAX, STAX
    }

    private OldCardBuilderFactory() {
    }

    public static AbstractOldCardBuilder createOldCardBuilder(String parserType) throws ParseXMLException {
        ParserType parser = ParserType.valueOf(parserType.toUpperCase());
        switch (parser) {
            case DOM:
                LOGGER.info("Create new OldCardDomBuilder");
                return new OldCardDomBuilder();
            case SAX:
                LOGGER.info("Create new OldCardSaxBuilder");
                return new OldCardSaxBuilder();
            case STAX:
                LOGGER.info("Create new OldCardStaxBuilder");
                return new OldCardStaxBuilder();
            default:
                LOGGER.error("There is no enumeration constant. " + parser.getDeclaringClass() + " " + parser.name());
                throw new ParseXMLException("There is no enumeration constant. " + parser.getDeclaringClass() + " " + parser.name());
        }
    }
}
