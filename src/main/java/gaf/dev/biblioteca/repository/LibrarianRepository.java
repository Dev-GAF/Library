package main.java.gaf.dev.biblioteca.repository;

import main.java.gaf.dev.biblioteca.model.Librarian;

import java.util.List;
import java.util.Optional;

public interface LibrarianRepository
{
    void add(Librarian librarian);
    void removeByRegistration(String registration);
    Optional<Librarian> findByRegistration(String registration);
    List<Librarian> findByName(String name);
    List<Librarian> findAll();
    boolean existsByRegistration(String registration);
}
