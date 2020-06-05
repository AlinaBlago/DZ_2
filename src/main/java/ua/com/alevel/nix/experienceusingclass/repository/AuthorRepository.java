package ua.com.alevel.nix.experienceusingclass.repository;

import ua.com.alevel.nix.experienceusingclass.data.Author;

import java.util.List;

public interface AuthorRepository extends AbstractRepository<Author> {

    Author findByFullName(String fullName);
    List<Author> findByBook(String bookName);
    List<Author> findByBookId(Long bookId);
}
