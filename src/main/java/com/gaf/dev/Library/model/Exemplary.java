package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Exemplary", schema = "Lib")
public class Exemplary implements Cloneable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "copy_number", nullable = false)
    private final int copyNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn", nullable = false)
    private final Book book;

    @Column(name = "available", nullable = false)
    private boolean available;

    public Exemplary(int copyNumber, Book book)
    {
        this.copyNumber = copyNumber;
        this.book = book;
        this.available = true;
    }

    public void markAsBorrowed()
    {
        this.available = false;
    }

    public void markAsReturned()
    {
        this.available = true;
    }

    public Exemplary(Exemplary model)
    {
        if (model==null) throw new IllegalArgumentException("missing model.");

        this.copyNumber = model.copyNumber;
        this.book = model.book != null ? new Book(model.book) : null;
        this.available = model.available;
    }

    @Override
    public Object clone()
    {
        Exemplary exemplary=null;
        try
        {
            exemplary = new Exemplary(this);
        } catch (RuntimeException e)
        {
            throw new AssertionError("Unexpected error during clone: " + e.getMessage(), e);
        }

        return book;
    }
}
