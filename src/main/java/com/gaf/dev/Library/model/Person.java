package com.gaf.dev.Library.model;

import com.gaf.dev.Library.utils.DateValidator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Builder
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Person", schema = "Lib")
public class Person implements DateValidator, Cloneable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_id", referencedColumnName = "id", nullable = false)
    private final CPF cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rg_id", referencedColumnName = "id", nullable = false)
    private final RG rg;

    @Column(name = "date_birth", nullable = false)
    private final LocalDate dateBirth;

    @Setter(AccessLevel.NONE)
    @Column(name = "height", length = 10)
    private float height;

    @Setter(AccessLevel.NONE)
    @Column(name = "weight", length = 10)
    private float weight;

    @Column(name = "gender", length = 1, nullable = false)
    private final char gender;

    @Override
    public boolean isValid(byte day, byte month, short year)
    {
        int currentYear = LocalDate.now().getYear();
        if (year < currentYear-120) return true;
        if (year > currentYear) return true;

        if (day<1 || day>31 || month<1 || month>12) return true;

        if (day>30 && (month==4 || month==6 || month==9 || month==11)) return true;

        if (day>29 && month==2) return true;
        if (day>28 && month==2 && this.isLeapYear(year)) return true;

        return false;
    }

    protected Person()
    {
        this.rg=null;
        this.cpf=null;
        this.dateBirth=null;
        this.gender='X';
    }

    public Person(String name, CPF cpf, RG rg, LocalDate dateBirth, float height, float weight, char gender)
    {
        this.setName(name);
        this.cpf = cpf;
        this.rg = rg;

        if (dateBirth == null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (this.isValid((byte) dateBirth.getDayOfMonth(), (byte) dateBirth.getMonthValue(), (byte) dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date.");

        this.dateBirth = dateBirth;
        this.setHeight(height);
        this.setWeight(weight);

        if (Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new IllegalArgumentException("Invalid gender.");

        this.gender = gender;
    }

    public Person(String name, CPF cpf, RG rg, LocalDate dateBirth, char gender)
    {
        this.setName(name);
        this.cpf = cpf;
        this.rg = rg;

        if (dateBirth == null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (this.isValid((byte) dateBirth.getDayOfMonth(), (byte) dateBirth.getMonthValue(), (byte) dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date.");

        this.dateBirth = dateBirth;

        if (Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new IllegalArgumentException("Invalid gender.");

        this.gender = gender;
    }

    public Person(String name, LocalDate dateBirth, char gender)
    {
        this.setName(name);
        this.cpf = null;
        this.rg = null;

        if (dateBirth == null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (this.isValid((byte) dateBirth.getDayOfMonth(), (byte) dateBirth.getMonthValue(), (byte) dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date.");

        this.dateBirth = dateBirth;

        if (Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new IllegalArgumentException("Invalid gender.");

        this.gender = gender;
    }

    public Person(String name, CPF cpf, LocalDate dateBirth, char gender)
    {
        this.setName(name);
        this.cpf = cpf;
        this.rg = null;

        if (dateBirth == null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (this.isValid((byte) dateBirth.getDayOfMonth(), (byte) dateBirth.getMonthValue(), (byte) dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date.");

        this.dateBirth = dateBirth;

        if (Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F')
            throw new IllegalArgumentException("Invalid gender.");

        this.gender = gender;
    }

    public void setName(String name)
    {
        if (name == null || name.trim().length() < 2)
            throw new IllegalArgumentException("Non-existent name.");

        this.name = name.trim();
    }

    public byte getAge()
    {
        if (this.dateBirth==null) return 0;

        return (byte)Period.between(this.dateBirth, LocalDate.now()).getYears();
    }

    public void setHeight(float height)
    {
        if (height < 0.47F || height > 3F)
            throw new IllegalArgumentException("Invalid height.");

        this.height = height;
    }

    public void setWeight(float weight)
    {
        if (weight < 2.5F || weight > 635F)
            throw new IllegalArgumentException("Invalid weight.");

        this.weight = weight;
    }

    public Person(Person model)
    {
        if (model==null) throw new IllegalArgumentException("missing model.");

        this.name = model.name;

        this.cpf = model.cpf != null ? new CPF(model.cpf.getValue()) : null;
        this.rg  = model.rg  != null ? new RG(model.rg.getValue())  : null;

        this.dateBirth = model.dateBirth;
        this.height = model.height;
        this.weight = model.weight;
        this.gender = model.gender;
    }

    @Override
    public Object clone()
    {
        Person person=null;
        try
        {
            person = new Person(this);
        } catch (RuntimeException e)
        {
            throw new AssertionError("Unexpected error during clone: " + e.getMessage(), e);
        }

        return person;
    }
}
