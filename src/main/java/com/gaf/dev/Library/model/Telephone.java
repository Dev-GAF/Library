package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
@Table(name = "Telephone", schema = "Lib")
public class Telephone
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "value_telephone", length = 15, nullable = false, unique = true)
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

    public void setValue(String value)
    {
        if (!Telephone.isPhone(value))
            throw new IllegalArgumentException("Invalid phone format.");

        this.value = value;
    }
}
