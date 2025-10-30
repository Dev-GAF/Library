package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
@ToString(exclude = "value")
@Table(name = "Rg", schema = "Lib")
public class RG
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value_rg", length = 12, nullable = false, unique = true)
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
}
