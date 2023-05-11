package de;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class main {
    public static void main(String[] args) throws ParseException {
        String test = "2023-06-08T11:07:33.441068Z";
        String test2 = "2023-06-08T11:07:33.441068";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        System.out.println(format.parse(test));
        //
        LocalDateTime test1 = LocalDateTime.parse(test2);
        System.out.println(Date.from(test1.atZone(ZoneId.systemDefault()).toInstant()));



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z' z");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(test + " Europe/Berlin", formatter);
        System.out.println(zonedDateTime);
        System.out.println(Date.from(zonedDateTime.toInstant()));

        //DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        //ZonedDateTime zonedDateTime1 = ZonedDateTime.parse(test, formatter1);
        //System.out.println(zonedDateTime1);
    }
}
