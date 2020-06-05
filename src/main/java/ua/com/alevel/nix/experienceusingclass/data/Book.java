package ua.com.alevel.nix.experienceusingclass.data;

public class Book extends AbstractData<Book> {

    private String bookName;
    private Author author;
    private String uq;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getUq() {
        return uq;
    }

    public void setUq(String uq) {
        this.uq = uq;
    }
}
