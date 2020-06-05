package ua.com.alevel.nix.experienceusingclass.data;

import java.util.List;

public class Author extends AbstractData<Author> {

    private String fullName;
    private List<Book> bookList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "fullName='" + fullName + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
