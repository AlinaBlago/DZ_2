package ua.com.alevel.nix.experienceusingclass.repository.impl;

import ua.com.alevel.nix.experienceusingclass.data.Author;
import ua.com.alevel.nix.experienceusingclass.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepositoryImpl implements AuthorRepository {

    private final List<Author> authorList = new ArrayList<>();

    public Author findByFullName(final String fullName) {
        return authorList
                .stream()
                .filter(author -> author.getFullName().equals(fullName))
                .findFirst()
                .orElse(null);
    }

    public List<Author> findByBook(String bookName) {
        return authorList
                .stream()
                .filter(author -> author.getBookList()
                        .stream()
                        .anyMatch(book -> book.getBookName().equals(bookName)))
                .collect(Collectors.toList());
    }

    public List<Author> findByBookId(Long bookId) {
        return authorList
                .stream()
                .filter(author -> author.getBookList()
                        .stream()
                        .anyMatch(book -> book.getId().equals(bookId)))
                .collect(Collectors.toList());
    }

    public void save(Author author) {
        authorList.add(author);
    }

    public Author findById(Long id) {
        return authorList
                .stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Author> findAll() {
        return authorList;
    }

    public void update(Author author) {
        authorList.forEach(currentAuthor -> {
            if (author.getId().equals(currentAuthor.getId())) {
                currentAuthor.setBookList(author.getBookList());
                currentAuthor.setFullName(author.getFullName());
            }
        });
    }

    public void remove(Long id) {
        authorList.removeIf(author -> author.getId().equals(id));
    }
}
