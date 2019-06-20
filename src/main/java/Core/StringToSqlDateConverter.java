package main.java.Core;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSqlDateConverter {
    public Date convertStringToDate(String stringDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(stringDate);
        return new java.sql.Date(date.getTime());
    }
}
