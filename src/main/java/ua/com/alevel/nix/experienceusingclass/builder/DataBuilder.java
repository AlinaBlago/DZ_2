package ua.com.alevel.nix.experienceusingclass.builder;

import ua.com.alevel.nix.experienceusingclass.config.ApplicationEnvironment;
import ua.com.alevel.nix.experienceusingclass.data.Author;
import ua.com.alevel.nix.experienceusingclass.data.Book;
import ua.com.alevel.nix.experienceusingclass.service.AuthorService;
import ua.com.alevel.nix.experienceusingclass.service.BookService;
import ua.com.alevel.nix.experienceusingclass.service.impl.AuthorServiceImpl;
import ua.com.alevel.nix.experienceusingclass.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataBuilder implements AbstractBuilder {

    private final AuthorService authorService = new AuthorServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    @Override
    public void buildDataList() {

        for (Map.Entry<String, String> entry : getKeyValueMapByBundleProperties(ApplicationEnvironment.getPropertyLang()).entrySet()) {

            Author author = new Author();
            String authorNameProperties = String.join(" ", entry.getKey().split("_"));
            author.setFullName(authorNameProperties);

            List<Book> bookList = new ArrayList<>();
            if (entry.getValue().contains(";")) {
                List<String> bookNamePropertiesList = Arrays.asList(entry.getValue().split(";"));
                bookNamePropertiesList.forEach(bookName -> {
                    Book book = new Book();
                    book.setBookName(bookName);
                    bookService.saveOrUpdate(book);
                    bookList.add(bookService.findByBookName(bookName));
                });
            } else {
                Book book = new Book();
                String bookName = entry.getValue();
                book.setBookName(bookName);
                bookService.saveOrUpdate(book);
                bookList.add(bookService.findByBookName(bookName));
            }

            author.setBookList(bookList);
            authorService.saveOrUpdate(author);
        }

        bookService.findAll().forEach(book -> {
            List<Author> authors = authorService.findByBook(book.getBookName());
            authors.forEach(author -> {
                book.setAuthor(author);
                bookService.saveOrUpdate(book);
            });
        });

        bookService.findAll().forEach(book -> {
            System.out.println("book = " + book.getBookName());
            System.out.println("book = " + book.getUq());
        });
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
