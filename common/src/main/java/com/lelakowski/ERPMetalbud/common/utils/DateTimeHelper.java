package com.lelakowski.ERPMetalbud.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {

    public static String currentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdfDate.format(now);
    }
}
