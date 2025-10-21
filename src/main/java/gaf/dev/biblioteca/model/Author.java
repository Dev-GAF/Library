package main.java.gaf.dev.biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Author extends Person
{
    private final String countryOrigin;
    private String countryResidence;

    public Author(String name, LocalDate dateBirth, String countryOrigin, String countryResidence, char gender)
    {
        super(name, dateBirth, gender);
        this.countryOrigin = countryOrigin;
        this.countryResidence = countryResidence;
    }

    public String getCountryResidence() { return this.countryResidence; }

    public void setCountryResidence(String countryResidence) { this.countryResidence = countryResidence; }

    public String getCountryOrigin() { return this.countryOrigin; }

    @Override
    public String toString()
    {
        return "Author{" + super.toString() +
                "; countryOrigin='" + this.countryOrigin + '\'' +
                ", countryResidence='" + this.countryResidence + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(this.countryOrigin, author.countryOrigin) && Objects.equals(this.countryResidence, author.countryResidence);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), this.countryOrigin, this.countryResidence);
    }

    public Author(Author model)
    {
        super(model);

        this.countryOrigin = model.countryOrigin;
        this.countryResidence = model.countryResidence;
    }

    @Override
    public Object clone()
    {
        Author author=null;
        try
        {
            author = new Author(this);
        } catch (RuntimeException e)
        {
            throw new AssertionError("Unexpected error during clone: " + e.getMessage(), e);
        }

        return author;
    }
}
