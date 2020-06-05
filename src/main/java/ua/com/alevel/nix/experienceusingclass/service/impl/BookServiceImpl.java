package ua.com.alevel.nix.experienceusingclass.service.impl;

import ua.com.alevel.nix.experienceusingclass.data.Book;
import ua.com.alevel.nix.experienceusingclass.repository.BookRepository;
import ua.com.alevel.nix.experienceusingclass.repository.impl.BookRepositoryImpl;
import ua.com.alevel.nix.experienceusingclass.service.BookService;

import java.util.List;
import java.util.UUID;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository = new BookRepositoryImpl();

    public Book findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    public Book findByAuthor(String authorName) {
        return bookRepository.findByAuthor(authorName);
    }

    public Book findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public void saveOrUpdate(Book book) {
        if (book.getId() == null) {
            long id = findAll().size();
            book.setId(++id);
            book.setUq(generateUq(UUID.randomUUID().toString()));
            bookRepository.save(book);
        } else {
            bookRepository.update(book);
        }
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void remove(Long id) {
        bookRepository.remove(id);
    }

    private String generateUq(String token) {
        Book b = bookRepository.findByUq(token);
        if (b == null) {
            return token;
        } else {
            return generateUq(UUID.randomUUID().toString());
        }
    }
}
