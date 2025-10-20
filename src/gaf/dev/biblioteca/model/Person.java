package gaf.dev.biblioteca.model;

import gaf.dev.biblioteca.utils.DateValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person implements DateValidator, Cloneable
{
    private String name;
    private final CPF cpf;
    private final RG rg;
    private final LocalDate dateBirth;
    private float height;
    private float weight;
    private final char gender;

    @Override
    public boolean isValid(byte day, byte month, short year)
    {
        int currentYear = LocalDate.now().getYear();
        if (year < currentYear-120) return false;
        if (year > currentYear) return  false;

        if (day<1 || day>31 || month<1 || month>12) return false;

        if (day>30 && (month==4 || month==6 || month==9 || month==11)) return false;

        if (day>29 && month==2) return false;
        if (day>28 && month==2 && !this.isLeapYear(year)) return false;

        return true;
    }

    public Person(String name, CPF cpf, RG rg, LocalDate dateBirth, float height, float weight, char gender)
    {
        this.setName(name);
        this.cpf = cpf;
        this.rg = rg;

        if (dateBirth==null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (!this.isValid((byte)dateBirth.getDayOfMonth(), (byte)dateBirth.getMonthValue(), (byte)dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date. It must be a real date and no more than 120 years old.");

        this.dateBirth = dateBirth;
        this.setHeight(height);
        this.setWeight(weight);

        if (Character.toUpperCase(gender)!='M' && Character.toUpperCase(gender)!='F')
            throw new IllegalArgumentException("Gender entered invalidly.");

        this.gender = gender;
    }

    public Person(String name, CPF cpf, RG rg, LocalDate dateBirth, char gender)
    {
        this.setName(name);
        this.cpf = cpf;
        this.rg = rg;

        if (dateBirth==null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (!this.isValid((byte)dateBirth.getDayOfMonth(), (byte)dateBirth.getMonthValue(), (byte)dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date. It must be a real date and no more than 120 years old.");

        this.dateBirth = dateBirth;

        if (Character.toUpperCase(gender)!='M' && Character.toUpperCase(gender)!='F')
            throw new IllegalArgumentException("Gender entered invalidly.");

        this.gender = gender;
    }

    public Person(String name, CPF cpf, LocalDate dateBirth, char gender)
    {
        this.setName(name);
        this.cpf = cpf;
        this.rg = null;

        if (dateBirth==null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (!this.isValid((byte)dateBirth.getDayOfMonth(), (byte)dateBirth.getMonthValue(), (byte)dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date. It must be a real date and no more than 120 years old.");

        this.dateBirth = dateBirth;

        if (Character.toUpperCase(gender)!='M' && Character.toUpperCase(gender)!='F')
            throw new IllegalArgumentException("Gender entered invalidly.");

        this.gender = gender;
    }

    public Person(String name, LocalDate dateBirth, float height, float weight, char gender)
    {
        this.setName(name);

        if (dateBirth==null)
            throw new IllegalArgumentException("Birth date cannot be null.");

        if (!this.isValid((byte)dateBirth.getDayOfMonth(), (byte)dateBirth.getMonthValue(), (byte)dateBirth.getYear()))
            throw new IllegalArgumentException("Invalid birth date. It must be a real date and no more than 120 years old.");

        this.dateBirth = dateBirth;
        this.cpf = null;
        this.rg = null;
        this.setHeight(height);
        this.setWeight(weight);

        if (Character.toUpperCase(gender)!='M' && Character.toUpperCase(gender)!='F')
            throw new IllegalArgumentException("Gender entered invalidly.");

        this.gender = gender;
    }

    public String getName() { return name; }

    public void setName(String name)
    {
        if (name==null) throw new IllegalArgumentException("Missing name.");

        name = name.trim();
        if (name.length()<2) throw new IllegalArgumentException("Non-existent name.");

        this.name = name;
    }

    public LocalDate getBirthDate() { return this.dateBirth; }

    public byte getAge()
    {
        if (this.dateBirth==null) return 0;
        return (byte)Period.between(this.dateBirth, LocalDate.now()).getYears();
    }

    public float getHeight() { return height; }

    public void setHeight(float height)
    {
        if (height<0.47F || height>3F) throw new IllegalArgumentException("Invalid height.");

        this.height = height;
    }

    public float getWeight() { return weight; }

    public void setWeight(float weight)
    {
        if (weight<2.5F || weight>635F)  throw new IllegalArgumentException("Invalid weight.");

        this.weight = weight;
    }

    public char getGender() { return gender; }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + this.name + '\'' +
                ", cpf='" + this.cpf.getValue() + '\'' +
                ", rg='" + this.rg.getValue() + '\'' +
                ", age=" + this.getAge() +
                ", gender=" + this.gender +
                ", height=" + this.height +
                ", weight=" + this.weight +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person)obj;
        return Float.compare(this.height, person.height) == 0 &&
                Float.compare(this.weight, person.weight) == 0 &&
                this.gender == person.gender &&
                Objects.equals(this.name, person.name) &&
                Objects.equals(this.cpf.getValue(), person.cpf.getValue()) &&
                Objects.equals(this.rg.getValue(), person.rg.getValue()) &&
                Objects.equals(this.dateBirth, person.dateBirth);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.name, this.cpf.getValue(), this.rg.getValue(), this.dateBirth, this.height, this.weight, this.gender);
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
