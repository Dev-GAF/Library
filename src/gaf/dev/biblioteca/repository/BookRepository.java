package gaf.dev.biblioteca.repository;

import gaf.dev.biblioteca.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository
{
    void add(Book book);
    void removeByIsbn(String isbn);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findAll();
    boolean existsByIsbn(String isbn);
}
