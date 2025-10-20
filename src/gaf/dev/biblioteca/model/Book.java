package gaf.dev.biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements Cloneable
{
    // Arrume as datas, verificação

    private final String isbn; // Preliminary validation
    private final String title;
    private final Person author;
    private final String publisher;
    private final LocalDate publicationYear;
    private final int numberOfPages;
    private final String genre;
    private final String language;
    private final String edition;
    private final LocalDate registrationDate;
    private final String location;

    private String description;
    private double price;

    private boolean isValidISBN(String isbn)
    {
        if (isbn==null) return false;

        String cleaned = isbn.replaceAll("-", "").trim();

        return cleaned.matches("\\d{10}|\\d{13}");
    }

    public Book(String isbn, String title, Person author, String publisher, String genre, String language,
                String edition, LocalDate publicationYear, int numberOfPages, String description,
                double price, LocalDate registrationDate, String location)
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

        if (location == null)
            throw new IllegalArgumentException("Location cannot be null.");

        if (numberOfPages<=0)
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
        this.location = location;

        this.setDescription(description);
        this.setPrice(price);
    }

    public String getIsbn() { return this.isbn; }

    public Person getAuthor() { return this.author; }

    public String getTitle() { return this.title; }

    public String getPublisher() { return this.publisher; }

    public LocalDate getPublicationYear() { return this.publicationYear; }

    public int getNumberOfPages() { return this.numberOfPages; }

    public String getLanguage() { return this.language; }

    public String getGenre() { return this.genre; }

    public String getEdition() { return this.edition; }

    public String getLocation() { return this.location; }

    public LocalDate getRegistrationDate() { return this.registrationDate; }

    public String getDescription() {return this.description; }

    public void setDescription(String description)
    {
        if (description==null || description.trim().isEmpty())
            throw new IllegalArgumentException("Book must not be null and must contain a description");

        this.description = description;
    }

    public double getPrice() { return this.price; }

    public void setPrice(double price)
    {
        if (price<=0) throw new IllegalArgumentException("Price must be valid.");

        this.price = price;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return this.numberOfPages == book.numberOfPages && Double.compare(this.price, book.price) == 0 && Objects.equals(this.isbn, book.isbn) && Objects.equals(this.title, book.title) && Objects.equals(this.author, book.author) && Objects.equals(this.publisher, book.publisher) && Objects.equals(this.publicationYear, book.publicationYear) && Objects.equals(this.genre, book.genre) && Objects.equals(this.language, book.language) && Objects.equals(this.edition, book.edition) && Objects.equals(this.registrationDate, book.registrationDate) && Objects.equals(this.location, book.location) && Objects.equals(this.description, book.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.isbn, this.title, this.author, this.publisher, this.publicationYear, this.numberOfPages, this.genre, this.language, this.edition, this.registrationDate, this.location, this.description, this.price);
    }

    public Book(Book model)
    {
        if (model==null) throw new IllegalArgumentException("missing model.");

        this.isbn = model.isbn;
        this.title = model.title;
        this.author = model.author != null ? new Person(model.author) : null;
        this.publisher = model.publisher;
        this.publicationYear = model.publicationYear;
        this.numberOfPages = model.numberOfPages;
        this.genre = model.genre;
        this.language = model.language;
        this.edition = model.edition;
        this.registrationDate = model.registrationDate;
        this.location = model.location;
        this.description = model.description;
        this.price = model.price;
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
