package gaf.dev.biblioteca.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RG
{
    private final String value;
    private static final String RG_REGEX = "^\\d{2}\\.\\d{3}\\.\\d{3}-?\\d{1}$|^\\d{8,9}$";

    private static boolean isRG(String rg)
    {
        if (rg==null) return false;

        Pattern pattern = Pattern.compile(RG_REGEX);
        Matcher matcher = pattern.matcher(rg.trim());

        return matcher.matches();
    }

    public RG(String value)
    {
        if (!RG.isRG(value))
            throw new IllegalArgumentException("Invalid RG.");

        this.value = this.format(value);
    }

    private String format(String value)
    {
        return value.replaceAll("[^0-9A-Za-z]", "");
    }

    protected String getValue() { return value; }

    @Override
    public String toString()
    {
        return "RG{" +
                "value='" + this.getValue() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        RG rg = (RG) o;
        return Objects.equals(this.value, rg.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.value);
    }
}
