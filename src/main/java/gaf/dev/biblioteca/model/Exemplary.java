package main.java.gaf.dev.biblioteca.model;

import java.util.Objects;

public class Exemplary
{
    private final int copyNumber;
    private final Book book;
    private boolean available;

    public Exemplary(int copyNumber, Book book)
    {
        this.copyNumber = copyNumber;
        this.book = book;
        this.available = true;
    }

    public int getCopyNumber() { return this.copyNumber; }

    public Book getBook() { return this.book; }

    public void markAsBorrowed() { this.available = false; }
    public void markAsReturned() { this.available = true; }

    public boolean isAvailable() { return this.available; }

    @Override
    public String toString()
    {
        return "Exemplar{" +
                "exemplarNumber=" + this.copyNumber +
                ", book=" + this.book +
                ", available=" + this.available +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Exemplary exemplary = (Exemplary) o;
        return this.copyNumber == exemplary.copyNumber && this.available == exemplary.available && Objects.equals(this.book, exemplary.book);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.copyNumber, this.book, this.available);
    }
}
