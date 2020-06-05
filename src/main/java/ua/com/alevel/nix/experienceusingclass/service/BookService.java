package ua.com.alevel.nix.experienceusingclass.service;

import ua.com.alevel.nix.experienceusingclass.data.Book;

public interface BookService extends AbstractService<Book> {

    Book findByBookName(String bookName);
    Book findByAuthor(String authorName);
    Book findByAuthorId(Long authorId);
}
