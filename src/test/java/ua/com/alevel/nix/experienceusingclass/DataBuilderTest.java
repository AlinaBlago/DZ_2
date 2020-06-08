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

    static BookService bookService = null;
    static AuthorService authorService = null;

    @BeforeAll
    public static void setEnv() {
        ApplicationEnvironment.setPropertyLang("en");
        DataBuilder dataBuilder = new DataBuilder();
        bookService = dataBuilder.getBookService();
        authorService = dataBuilder.getAuthorService();
        dataBuilder.buildDataList();
    }

    @Test
    public void build() {

        assertTrue(bookService.findAll().size() == 8);
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

        assertTrue(bookService.findAll().size() == 9);
        assertEquals(authorService.findAll().size(), 6);
        authorService.remove(author.getId());
        bookService.remove(book.getId());

    }

    @Test
    public void authorTestFindByFullName(){
        Author returned_author = authorService.findByFullName("George Orwell");
        assertTrue(returned_author.getFullName().equals("George Orwell"));
    }

    @Test
    public void authorTestFindByFullNameIncorrectName(){
        Author returned_author = authorService.findByFullName("George_Orwell");
        assertTrue(returned_author == null);
    }

    @Test
    public void authorTestFindByBook(){
        Author author = authorService.findByFullName("Mikhail Bulgakov");
        assertTrue(authorService.findByBook("Master and Margarita").contains(author));
    }

    @Test
    public void authorTestFindByBookIncorrectName(){
        Author author = authorService.findByFullName("Mikhail Bulgakov");
        assertTrue(!authorService.findByBook("Master_and_Margarita").contains(author));
    }

    @Test
    public void authorTestFindByBookId(){
        Author author = authorService.findByFullName("Mikhail Bulgakov");
        assertTrue(authorService.findByBookId(1L).contains(author));
    }

    //saveOrUpdate

    @Test
    public void authorTestSaveOrUpdate(){
        Author author = new Author();
        author.setFullName("Alina Blagodarnaya");

        authorService.saveOrUpdate(author);

        assertTrue(authorService.findByFullName("Alina Blagodarnaya").equals(author));

        authorService.remove(author.getId());
    }

    //findById

    @Test
    public void authorTestFindById(){
        Author author = authorService.findAll().get(0);

        assertTrue(authorService.findById(author.getId()).equals(author));
    }

    //findAll

    @Test
    public void authorTestFindall(){
        assertTrue(authorService.findAll().size() == 5);
    }

    //remove

    @Test
    public void authorTestRemove(){
        Author author = authorService.findAll().get(0);
        authorService.remove(author.getId());
        assertTrue(authorService.findAll().size() == 4);
        authorService.saveOrUpdate(author);
    }

    // findByBookName

    @Test
    public void bookTestFindByBookName(){
        Book returned_book = bookService.findByBookName("Animal Farm");
        assertTrue(returned_book.getBookName().equals("Animal Farm"));
    }

    // findByAuthor
    @Test
    public void bookTestFindByAuthor(){
        Book book = bookService.findByBookName("1984");
        assertTrue(authorService.findByFullName("George Orwell").getBookList().contains(book));
    }

    // findByAuthorId
    @Test
    public void bookTestFindByAuthorId(){
        Author author = authorService.findByFullName("Mikhail Bulgakov");
        Book book = bookService.findByAuthorId(author.getId());
        assertTrue(author.getBookList().contains(book));
    }

    //saveOrUpdate
    @Test
    public void bookTestSaveOrUpdate(){
        Book book = new Book();
        book.setBookName("Cat and Dog");

        bookService.saveOrUpdate(book);

        assertTrue(bookService.findByBookName("Cat and Dog").equals(book));

        bookService.remove(book.getId());
    }

    // findById
    @Test
    public void bookTestFindById(){
        Book book = bookService.findAll().get(0);

        assertTrue(bookService.findById(book.getId()).equals(book));
    }

    // findAll
    @Test
    public void bookTestFindAll(){
        assertTrue(bookService.findAll().size() == 8);
    }

    // remove
    @Test
    public void bookTestRemove(){
        Book book = bookService.findAll().get(0);
        bookService.remove(book.getId());
        assertTrue(bookService.findAll().size() == 7);
        bookService.saveOrUpdate(book);
    }


}
