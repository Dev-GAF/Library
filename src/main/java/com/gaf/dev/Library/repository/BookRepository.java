package com.gaf.dev.Library.repository;

import com.gaf.dev.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>
{
//    void add(Book book);
//    void removeByIsbn(String isbn);
//    Optional<Book> findByIsbn(String isbn);
//    List<Book> findByTitle(String title);
//    List<Book> findAll();
//    boolean existsByIsbn(String isbn);
}
