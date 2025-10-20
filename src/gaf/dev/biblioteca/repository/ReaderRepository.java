package gaf.dev.biblioteca.repository;

import gaf.dev.biblioteca.model.Reader;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReaderRepository
{
    void add(Reader reader);
    void removeByRegistrationNumber(String registrationNumber);
    Optional<Reader> findByRegistrationNumber(String registrationNumber);
    Optional<Reader> findByDateBirth(LocalDate dateBirth);
    List<Reader> findByName(String name);
    List<Reader> findAll();
    boolean existsByRegistrationNumber(String registrationNumber);
}
