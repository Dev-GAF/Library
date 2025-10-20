package gaf.dev.biblioteca.repository.inmemory;

import gaf.dev.biblioteca.model.Reader;
import gaf.dev.biblioteca.repository.ReaderRepository;

import java.time.LocalDate;
import java.util.*;

public class InMemoryReaderRepository implements ReaderRepository
{
    private final Map<String, Reader> readers = new HashMap<>();

    @Override
    public void add(Reader reader)
    {
        this.readers.put(reader.getRegistrationNumber(), reader);
    }

    @Override
    public void removeByRegistrationNumber(String registrationNumber)
    {
        this.readers.remove(registrationNumber);
    }

    @Override
    public Optional<Reader> findByRegistrationNumber(String registrationNumber)
    {
        return Optional.ofNullable(this.readers.get(registrationNumber));
    }

    @Override
    public Optional<Reader> findByDateBirth(LocalDate dateBirth)
    {
        return this.readers.values().stream()
                .filter(reader -> reader.getBirthDate().equals(dateBirth))
                .findFirst();
    }

    @Override
    public List<Reader> findByName(String name)
    {
        List<Reader> result = new ArrayList<>();
        for (Reader reader :  this.readers.values())
            if (reader.getName().contains(name))
                result.add(reader);

        return result;
    }

    @Override
    public List<Reader> findAll()
    {
        return new ArrayList<>(this.readers.values());
    }

    @Override
    public boolean existsByRegistrationNumber(String registrationNumber)
    {
        return this.readers.containsKey(registrationNumber);
    }
}
