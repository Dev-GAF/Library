package gaf.dev.biblioteca.model;

import gaf.dev.biblioteca.utils.DateValidator;
import gaf.dev.biblioteca.utils.ValidatePersonalInformation;

import java.time.LocalDate;
import java.util.Objects;

public class Reader extends Person implements Cloneable
{
    private String registrationNumber;
    private Telephone telephone;
    private Email email;

    public Reader(String registrationNumber, String name, CPF cpf, LocalDate dateBirth, Telephone telephone, Email email, char gender)
    {
        super(name, cpf, dateBirth, gender);
        this.registrationNumber = registrationNumber;
        this.telephone = telephone;
        this.email = email;
    }

    public String getRegistrationNumber() { return this.registrationNumber; }

    @Override
    public String toString() {
        return "Reader{" + super.toString() +
                "; registrationNumber='" + registrationNumber + '\'' +
                ", telephone=" + telephone +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reader reader = (Reader) o;
        return Objects.equals(this.registrationNumber, reader.registrationNumber) && Objects.equals(this.telephone.getValue(), reader.telephone.getValue()) && Objects.equals(this.email.getValue(), reader.email.getValue());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), this.registrationNumber, this.telephone.getValue(), this.email.getValue());
    }

    public Reader(Reader model)
    {
        super(model);
        if (model==null) throw new IllegalArgumentException("missing model.");

        this.registrationNumber = model.registrationNumber;
        this.telephone = model.telephone != null ? new Telephone(model.telephone.getValue()) : null;
        this.email  = model.email  != null ? new Email(model.email.getValue())  : null;
    }

    @Override
    public Object clone()
    {
        Reader reader=null;
        try
        {
            reader = new Reader(this);
        } catch (RuntimeException e)
        {
            throw new AssertionError("Unexpected error during clone: " + e.getMessage(), e);
        }

        return reader;
    }
}
