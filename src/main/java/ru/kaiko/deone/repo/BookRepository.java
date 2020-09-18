package ru.kaiko.deone.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaiko.deone.model.Book;

public interface BookRepository extends CrudRepository<Book,Long> {
}
