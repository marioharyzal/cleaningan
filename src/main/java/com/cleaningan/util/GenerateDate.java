package com.cleaningan.util;

import com.cleaningan.constant.MessageException;

import javax.management.BadAttributeValueExpException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateDate {

    public static Date generate(String stringDate) throws Exception {
        Date date =null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(stringDate);
        } catch (ParseException e) {
            throw new BadAttributeValueExpException(MessageException.BAD_REQUEST);
        }
        return date;
    }
}
