package com.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {
    @SuppressWarnings("serial")
    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));

            add(new SimpleDateFormat("yyyy/MM/dd"));
            add(new SimpleDateFormat("yyyy.M.dd"));
            add(new SimpleDateFormat("yyyy/M/dd hh:mm:ss a"));
            add(new SimpleDateFormat("yyyy.M.dd hh:mm:ss a"));
            add(new SimpleDateFormat("yyyy.MMM.dd"));
            add(new SimpleDateFormat("yyyy-MMM-dd"));
            add(new SimpleDateFormat("yyyy-MM-dd"));
            add(new SimpleDateFormat("yyyyMMdd"));
        }
    };
    public static Date convertToDate(String input) {
        Date date = null;
        if(null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }

    public static java.sql.Date getFormatDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = dateFormat.parse(strDate);
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        return date;
    }

}
