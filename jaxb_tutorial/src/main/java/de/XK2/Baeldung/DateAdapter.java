package de.XK2.Baeldung;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    @Override
    public Date unmarshal(String s) throws Exception {
        return dateFormat.get().parse(s);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return dateFormat.get().format(date);
    }
}
