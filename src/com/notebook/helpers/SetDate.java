package com.notebook.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetDate {


    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy hh.mm").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDateCsv(String date) {
        try {
            return new SimpleDateFormat("E dd.MMM.yy hh':'mm a").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
