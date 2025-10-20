package gaf.dev.biblioteca.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email
{
    private String value;
    private static final String EMAIL_REGEX = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

    public static boolean isEmail(String email)
    {
    /*
        Explicação para mim:

        -pattern compila a regex que valida o formato do email.
        -matcher vai tentar casar essa regex com a string email que você quer validar.

        Depois, você chama matcher.matches() para saber se o email inteiro corresponde ao padrão.
    */

        if (email==null) return false;

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public Email(String value)
    {
        this.setValue(value);
    }

    public String getValue() { return value; }

    public void setValue(String value)
    {
        if (!Email.isEmail(value))
            throw new IllegalArgumentException("Invalid email.");

        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Email{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(this.value, email.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.value);
    }
}
