package ru.kaiko.deone.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;
import ru.kaiko.deone.model.Book;
import ru.kaiko.deone.repo.BookRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = "application/json")

public class HomeControllerRest {
    private final BookRepository bookRepository;

    public HomeControllerRest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    //what response we'd like to return,
    //if we can't find an object with this id
    //so we are returning ResponseEntity
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping(consumes = "application/json")
    public Book postBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
//    @Transactional(rollbackOn = ArithmeticException.class)
//    @Transactional(Transactional.TxType.NEVER)
    @org.springframework.transaction.annotation
            .Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.NEVER    )
    public void deleteBookById(@PathVariable("id") Long id) {
        try {
            bookRepository.deleteById(id);
            doExpensiveWork();
        } catch (EmptyResultDataAccessException | InterruptedException e) {
          //  log.info(e.getMessage());
        }
    }

    private void doExpensiveWork() throws InterruptedException {
        Thread.sleep(100);
        throw new RuntimeException();
    }
    @PutMapping("/{id}")
    public Book putBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Book patchBook(@PathVariable("id") Long id, @RequestBody Book bookPath) {
        Book bookRefresh = bookRepository.findById(id).get();
        if (bookPath.getAuthor() != null) {
            bookRefresh.setAuthor(bookPath.getAuthor());
        }

        if (bookPath.getName() != null) {
            bookRefresh.setName(bookPath.getName());
        }

        return bookRepository.save(bookRefresh);
    }

}
