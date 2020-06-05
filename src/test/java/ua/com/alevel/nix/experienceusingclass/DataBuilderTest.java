package ua.com.alevel.nix.experienceusingclass;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.com.alevel.nix.experienceusingclass.builder.DataBuilder;
import ua.com.alevel.nix.experienceusingclass.config.ApplicationEnvironment;
import ua.com.alevel.nix.experienceusingclass.data.Author;
import ua.com.alevel.nix.experienceusingclass.data.Book;
import ua.com.alevel.nix.experienceusingclass.service.AuthorService;
import ua.com.alevel.nix.experienceusingclass.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataBuilderTest {

    @BeforeAll
    public static void setEnv() {
        ApplicationEnvironment.setPropertyLang("en");
    }

    @Test
    public void build() {

        DataBuilder dataBuilder = new DataBuilder();
        BookService bookService = dataBuilder.getBookService();
        AuthorService authorService = dataBuilder.getAuthorService();
        dataBuilder.buildDataList();

        assertTrue(bookService.findAll().size() == 9);
        assertEquals(authorService.findAll().size(), 5);

        Author author = new Author();
        String authorFullName = "Erich Gamma";
        author.setFullName(authorFullName);

        List<Book> bookList = new ArrayList<>();
        String bookName = "Design Patterns: Elements of Reusable Object-Oriented Software";

        Book book = new Book();
        book.setBookName(bookName);
        bookService.saveOrUpdate(book);
        bookList.add(bookService.findByBookName(bookName));

        author.setBookList(bookList);
        authorService.saveOrUpdate(author);

        bookService.findAll().forEach(currentBook -> {
            List<Author> authors = authorService.findByBook(currentBook.getBookName());
            authors.forEach(currentAuthor -> {
                book.setAuthor(currentAuthor);
                bookService.saveOrUpdate(currentBook);
            });
        });

        assertTrue(bookService.findAll().size() == 10);
        assertEquals(authorService.findAll().size(), 6);
    }
}
