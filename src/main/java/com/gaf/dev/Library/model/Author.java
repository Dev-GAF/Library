package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "Author", schema = "Lib")
public class Author extends Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_origin", length = 20, nullable = false)
    private final String countryOrigin;

    @Column(name = "country_residence", length = 20, nullable = false)
    private String countryResidence;

    public Author(String name, LocalDate dateBirth, String countryOrigin, String countryResidence, char gender)
    {
        super(name, dateBirth, gender);
        this.countryOrigin = countryOrigin;
        this.countryResidence = countryResidence;
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
