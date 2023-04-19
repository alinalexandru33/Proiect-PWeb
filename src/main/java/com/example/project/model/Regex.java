package com.example.project.model;

public class Regex {

    public static final String CNP_REGEX = "^[1-9][0-9]{12}$";
    public static final String STREET_REGEX = "^([a-zA-Z',.-]+( [a-zA-Z',.-]+)*)$";
    public static final String CITY_REGEX = "^([a-zA-Z',.-]+( [a-zA-Z',.-]+)*)$";
    public static final String NAME_REGEX = "^([a-zA-Z',.-]+( [a-zA-Z',.-]+)*)$";
    public static final String LENGTH = "^(?:\\d+[hms]?|\\d+h(?:[ ]*[0-5]?\\dm)?(?:[ ]*[0-5]?\\ds)?|\\d+m[ ]*[0-5]?\\ds)$";

}
