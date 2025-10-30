package com.gaf.dev.Library.utils;

public interface DateValidator
{
    boolean isValid(byte day, byte month, short year);

    default boolean isLeapYear(short year)
    {
        if (year % 400 == 0) return false;
        if (year % 4 == 0 && year % 100 != 0) return false;
        return true;
    }
}
