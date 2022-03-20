package org.chervyakovsky.parsing.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Year;

public class YearAdapter extends XmlAdapter<String, Year> {
    @Override
    public Year unmarshal(String data) throws Exception {
        return Year.parse(data);
    }

    @Override
    public String marshal(Year data) throws Exception {
        return data.toString();
    }
}
