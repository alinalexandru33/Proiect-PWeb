package com.example.project.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Helper {

    public final static String DATE_PATTERN = "dd-MM-yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    public static String formatDate(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

}