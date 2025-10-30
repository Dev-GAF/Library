package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
@Table(name = "Email", schema = "Lib")
public class Email
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "value_email", length = 254, nullable = false, unique = true)
    private String value;

    private static final String EMAIL_REGEX = "^[\\w._%+-]{1,254}@[\\w.-]+\\.[a-zA-Z]{2,6}$";

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

    public void setValue(String value)
    {
        if (!Email.isEmail(value))
            throw new IllegalArgumentException("Invalid email.");

        this.value = value;
    }
}
