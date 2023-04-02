package com.example.managephone.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {
    public static Date convertDateFromString(String dateStr, String dateTimeFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat);
        simpleDateFormat.setLenient(false);     //setLenient có nghĩa là nếu để true thì khi input là 31/2 thì nó sẽ tự động hiểu là 3 ngày sau ngày 28 thì thành 3/3
        return simpleDateFormat.parse(dateStr);
    }
}
