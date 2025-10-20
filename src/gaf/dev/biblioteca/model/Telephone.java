package gaf.dev.biblioteca.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telephone
{
    private String value;
    private static final String PHONE_REGEX= "^(\\(?\\d{2}\\)?\\s?)?(9\\d{4}|[2-8]\\d{3})[- ]?\\d{4}$";

    public static boolean isPhone(String phone)
    {
        if (phone==null) return false;

        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public Telephone(String value)
    {
        this.setValue(value);
    }

    public String getValue() { return this.value; }

    public void setValue(String value)
    {
        if (!Telephone.isPhone(value))
            throw new IllegalArgumentException("Invalid phone format.");

        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Telephone{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return Objects.equals(this.value, telephone.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.value);
    }
}
