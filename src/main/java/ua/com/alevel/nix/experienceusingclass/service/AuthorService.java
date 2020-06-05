package ua.com.alevel.nix.experienceusingclass.service;

import ua.com.alevel.nix.experienceusingclass.data.Author;

import java.util.List;

public interface AuthorService extends AbstractService<Author> {

    Author findByFullName(String fullName);
    List<Author> findByBook(String bookName);
    List<Author> findByBookId(Long bookId);
}
