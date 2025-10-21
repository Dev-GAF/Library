package main.java.gaf.dev.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Library
{
    private final int id;
    private String name;
    private final List<Exemplary> copies;
    private final List<Reader> readers;
    private final List<Librarian> librarians;

    public Library(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.copies = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public void setName(String name)
    {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name invalid.");

        this.name = name.trim();
    }

    public List<Exemplary> getCopies() { return new ArrayList<>(this.copies); }

    public List<Reader> getReaders() { return new ArrayList<>(this.readers); }

    public List<Librarian> getLibrarians() { return new ArrayList<>(librarians); }



}
