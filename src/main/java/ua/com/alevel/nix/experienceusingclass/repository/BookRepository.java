package ua.com.alevel.nix.experienceusingclass.repository;

import ua.com.alevel.nix.experienceusingclass.data.Book;

public interface BookRepository extends AbstractRepository<Book> {

    Book findByBookName(String bookName);
    Book findByAuthor(String authorName);
    Book findByAuthorId(Long id);
    Book findByUq(String uq);
}
