package main.java.gaf.dev.biblioteca.repository.inmemory;

import main.java.gaf.dev.biblioteca.model.Librarian;
import main.java.gaf.dev.biblioteca.repository.LibrarianRepository;

import java.util.*;

public class InMemoryLibrarianRepository implements LibrarianRepository
{
    private final Map<String, Librarian> librarians = new HashMap<>();

    @Override
    public void add(Librarian librarian)
    {
        this.librarians.put(librarian.getRegistration(), librarian);
    }

    @Override
    public void removeByRegistration(String registration)
    {
        this.librarians.remove(registration);
    }

    @Override
    public Optional<Librarian> findByRegistration(String registration)
    {
        return Optional.ofNullable(this.librarians.get(registration));
    }

    @Override
    public List<Librarian> findByName(String name)
    {
        List<Librarian> result = new ArrayList<>();
        for (Librarian librarian :  this.librarians.values())
            if (librarian.getName().contains(name))
                result.add(librarian);

        return result;
    }

    @Override
    public List<Librarian> findAll()
    {
        return new ArrayList<>(this.librarians.values());
    }

    @Override
    public boolean existsByRegistration(String registration)
    {
        return this.librarians.containsKey(registration);
    }
}
