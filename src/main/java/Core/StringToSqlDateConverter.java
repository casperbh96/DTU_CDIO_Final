package main.java.Core;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSqlDateConverter {
    public Date convertStringToDate(String stringDate) throws ParseException {
        Date date = Date.valueOf(stringDate);
        date.setTime(date.getTime()+90000000); // plus 25 hours, fix

        return date;
    }
}
