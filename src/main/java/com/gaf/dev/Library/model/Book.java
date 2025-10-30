package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "isbn")
@Table(name = "Book", schema = "Lib")
public class Book implements Cloneable
{
    // Arrume as datas, verificação

    @Id
    @Column(name = "isbn", length = 13, nullable = false, unique = true)
    private final String isbn;

    @Column(name = "title_book", length = 50, nullable = false)
    private final String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private final Author author;

    @Column(name = "publisher", length = 60, nullable = false)
    private final String publisher;

    @Column(name = "genre", length = 20, nullable = false)
    private final String genre;

    @Column(name = "language_book", length = 20, nullable = false)
    private final String language;

    @Column(name = "edition", length = 10, nullable = false)
    private final String edition;

    @Column(name = "publication_year", nullable = false)
    private final LocalDate publicationYear;

    @Column(name = "number_of_pages", nullable = false)
    private final int numberOfPages;

    @Column(name = "registration_date", nullable = false)
    private final LocalDate registrationDate;

    @Column(name = "location_book", length = 10, nullable = false)
    private String locationBook;

    @Column(name = "description_book", length = 200, nullable = false)
    private final String description;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private double price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Exemplary> exemplaries = new ArrayList<>();

    private boolean isValidISBN(String isbn)
    {
        if (isbn==null) return false;

        String cleaned = isbn.replaceAll("-", "").trim();

        return cleaned.matches("\\d{10}|\\d{13}");
    }

    public Book(String isbn, String title, Author author, String publisher, String genre, String language,
                String edition, LocalDate publicationYear, int numberOfPages, String description,
                double price, LocalDate registrationDate, String locationBook)
    {
        if (!this.isValidISBN(isbn))
            throw new IllegalArgumentException("Invalid ISBN.");

        if (title == null)
            throw new IllegalArgumentException("Title cannot be null.");

        if (author == null)
            throw new IllegalArgumentException("Author cannot be null.");

        if (publisher == null)
            throw new IllegalArgumentException("Publisher cannot be null.");

        if (genre == null)
            throw new IllegalArgumentException("Genre cannot be null.");

        if (language == null)
            throw new IllegalArgumentException("Language cannot be null.");

        if (edition == null)
            throw new IllegalArgumentException("Edition cannot be null.");

        if (locationBook == null)
            throw new IllegalArgumentException("Location cannot be null.");

        if (numberOfPages <= 0)
            throw new IllegalArgumentException("Number of pages must be valid.");

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.language = language;
        this.edition = edition;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.registrationDate = registrationDate;
        this.locationBook = locationBook;

        if (description==null || description.trim().isEmpty())
            throw new IllegalArgumentException("Book must not be null and must contain a description");

        this.description = description;

        if (price<=0)
            throw new IllegalArgumentException("Price must be valid.");

        this.price = price;
    }

    public Book(Book model)
    {
        if (model==null) throw new IllegalArgumentException("missing model.");

        this.isbn = model.isbn;
        this.title = model.title;
        this.author = model.author != null ? new Author(model.author) : null;
        this.publisher = model.publisher;
        this.publicationYear = model.publicationYear;
        this.numberOfPages = model.numberOfPages;
        this.genre = model.genre;
        this.language = model.language;
        this.edition = model.edition;
        this.registrationDate = model.registrationDate;
        this.locationBook = model.locationBook;
        this.description = model.description;
        this.price = model.price;

        for (Exemplary exemplary : model.exemplaries)
            this.exemplaries.add(new Exemplary(exemplary));

    }

    @Override
    public Object clone()
    {
        Book book=null;
        try
        {
            book = new Book(this);
        } catch (RuntimeException e)
        {
            throw new AssertionError("Unexpected error during clone: " + e.getMessage(), e);
        }

        return book;
    }
}
