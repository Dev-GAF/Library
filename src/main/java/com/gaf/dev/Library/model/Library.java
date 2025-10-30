package com.gaf.dev.Library.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@
public class Library
{
    private final int id;
    private String name;
    private String address;
    private Telephone telephone;
    private final List<Exemplary> exemplars;
    private final List<Reader> readers;
    private final List<Librarian> librarians;
    private final List<Loan> loans;

    public Library(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.exemplars = new ArrayList<>();
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

    public List<Exemplary> getExemplars() { return new ArrayList<>(this.exemplars); }

    public List<Reader> getReaders() { return new ArrayList<>(this.readers); }

    public List<Librarian> getLibrarians() { return new ArrayList<>(librarians); }



}
