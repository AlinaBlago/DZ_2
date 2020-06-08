package ua.com.alevel.nix.experienceusingclass.repository.impl;

import ua.com.alevel.nix.experienceusingclass.data.Book;
import ua.com.alevel.nix.experienceusingclass.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private final List<Book> bookList = new ArrayList<>();

    public Book findByBookName(String bookName) {

        for (Book book : bookList){
            if (book.getBookName().equals(bookName))
                return book;
        }
        return null;

    }

    public Book findByAuthor(String authorName) {

        for (Book book : bookList){
            if (book.getAuthor().getFullName().equals(authorName)){
                return book;
            }
        }
        return null;
    }

    public Book findByAuthorId(Long authorId) {

        for (Book book : bookList){
            if (book.getAuthor().getId().equals(authorId)){
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findByUq(String uq) {

        for (Book book : bookList){
            if (book.getUq().equals(uq)){
                return book;
            }
        }
        return null;

    }

    public void save(Book book) {
        bookList.add(book);
    }

    public Book findById(Long id) {

        for (Book book : bookList){
            if (book.getId().equals(id)){
                return book;
            }
        }
        return null;

    }

    public List<Book> findAll() {
        return bookList;
    }

    public void update(Book book) {

        for (Book current_book : bookList){
            if (book.getId().equals(current_book.getId())){
                current_book.setAuthor(book.getAuthor());
                current_book.setBookName(book.getBookName());
            }
        }
    }

    public void remove(Long id) {

        for (Book book : bookList){
            if (book.getId().equals(id)){
                bookList.remove(book);
                break;
            }
        }

    }
}
