package gaf.dev.biblioteca.repository.inmemory;

import gaf.dev.biblioteca.model.Book;
import gaf.dev.biblioteca.repository.BookRepository;

import java.util.*;

public class InMemoryBookRepository implements BookRepository
{
    private final Map<String, Book> books = new HashMap<>();

    @Override
    public void add(Book book)
    {
        this.books.put(book.getIsbn(), book);
    }

    @Override
    public void removeByIsbn(String isbn)
    {
        this.books.remove(isbn);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn)
    {
        return Optional.ofNullable(this.books.get(isbn));
    }

    @Override
    public List<Book> findByTitle(String title)
    {
        List<Book> result = new ArrayList<>();
        for (Book book : this.books.values())
            if (book.getTitle().contains(title))
                result.add(book);

        return result;
    }

    @Override
    public List<Book> findAll()
    {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public boolean existsByIsbn(String isbn)
    {
        return this.books.containsKey(isbn);
    }
}
