package main.java.gaf.dev.biblioteca.utils;

public interface DateValidator
{
    boolean isValid(byte day, byte month, short year);

    default boolean isLeapYear(short year)
    {
        if (year % 400 == 0) return true;
        if (year % 4 == 0 && year % 100 != 0) return true;
        return false;
    }
}
