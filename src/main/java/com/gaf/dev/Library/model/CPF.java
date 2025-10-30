package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "value")
@Table(name = "Cpf", schema = "Lib")
public class CPF
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value_cpf", length = 14, nullable = false, unique = true)
    private final String value;

    private static short sumMultipliers(List<Integer> multipliers, String cpfValidated)
    {
        short sum=0;
        for (int i=0; i<multipliers.size(); i++)
        {
            int digit = cpfValidated.charAt(i) - '0'; // Converte char para int;
            sum = (short)(sum + (digit * multipliers.get(i)) );
        }

        return sum;
    }

    private static boolean isCPF(String cpf)
    {
        if (cpf==null) throw new IllegalArgumentException("CPF cannot be null.");

        //String cpfValidated = ValidateDocument.formatCPF(cpf);

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<cpf.length(); i++)
        {
            char c = cpf.charAt(i);
            if (Character.isDigit(c)) sb.append(c);
        }

        String cpfValidated = sb.toString();

        if (cpfValidated.length()!=11) return false;

        List<Integer> multipliers = new ArrayList<>(List.of(10, 9, 8, 7, 6, 5, 4, 3, 2));

        short sum = CPF.sumMultipliers(multipliers, cpfValidated);

        byte remainder = (byte)(sum % 11);
        byte firstDigit = (byte)(remainder<2 ? 0 : 11-remainder);

        multipliers.add(0, 11);

        sum = CPF.sumMultipliers(multipliers, cpfValidated);

        remainder = (byte)(sum % 11);
        byte secondDigit = (byte) (remainder<2 ? 0 : 11-remainder);

        return (firstDigit == cpfValidated.charAt(9) - '0') && (secondDigit == cpfValidated.charAt(10) - '0');
    }

    public CPF(String value)
    {
        if (!CPF.isCPF(value))
            throw new IllegalArgumentException("Invalid CPF.");

        this.value = format(value);
    }

    private String format(String value)
    {
        return value.replaceAll("[^0-9]", "");
    }
}
