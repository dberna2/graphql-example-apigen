package com.graphql.springbootexample.service.mapper.util;


import com.google.common.base.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsMapper extends Converter<String, Date>{

    private static final String DATE_TIME_FORMAT_PATTERN = "dd/MM/yyyy";


    @Override
    protected Date doForward(String value) {

        final DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN);

        try {
            return format.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    protected String doBackward(Date value) {

        final DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN);

        return format.format(value);
    }

}
