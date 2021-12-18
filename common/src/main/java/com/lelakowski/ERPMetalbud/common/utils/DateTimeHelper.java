package com.lelakowski.ERPMetalbud.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeHelper {

    public static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String fromGregorianCalendar(GregorianCalendar date) {
        return sdfDate.format(date.getTime());
    }

    public static String currentDate() {
        Date now = new Date();
        return sdfDate.format(now);
    }
}
