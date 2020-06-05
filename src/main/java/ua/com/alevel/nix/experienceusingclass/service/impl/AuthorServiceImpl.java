package ua.com.alevel.nix.experienceusingclass.service.impl;

import ua.com.alevel.nix.experienceusingclass.data.Author;
import ua.com.alevel.nix.experienceusingclass.repository.AuthorRepository;
import ua.com.alevel.nix.experienceusingclass.repository.impl.AuthorRepositoryImpl;
import ua.com.alevel.nix.experienceusingclass.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository = new AuthorRepositoryImpl();

    public Author findByFullName(String fullName) {
        return authorRepository.findByFullName(fullName);
    }

    public List<Author> findByBook(String bookName) {
        return authorRepository.findByBook(bookName);
    }

    public List<Author> findByBookId(Long bookId) {
        return authorRepository.findByBookId(bookId);
    }

    public void saveOrUpdate(Author author) {
        if (author.getId() == null) {
            long id = findAll().size();
            author.setId(++id);
            authorRepository.save(author);
        } else {
            authorRepository.update(author);
        }
    }

    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void remove(Long id) {
        authorRepository.remove(id);
    }
}
