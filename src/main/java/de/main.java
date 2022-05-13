package de;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class main {
    public static void main(String[] args) throws ParseException {
        //Date date = new SimpleDateFormat("dd-MM-yyyy").parse("16-07-1991");
        //java.sql.Date jDate = new java.sql.Date(date.getTime());
        //System.out.println(jDate);
        //System.out.println(date);
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.stream().forEach(a -> System.out.println(a));
    }
}
