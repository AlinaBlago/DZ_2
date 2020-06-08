package ua.com.alevel.nix.experienceusingclass.repository.impl;

import ua.com.alevel.nix.experienceusingclass.data.Author;
import ua.com.alevel.nix.experienceusingclass.data.Book;
import ua.com.alevel.nix.experienceusingclass.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepositoryImpl implements AuthorRepository {

    private final List<Author> authorList = new ArrayList<>();

    public Author findByFullName(final String fullName) {
        /*
        for (int i = 0; i < authorList.size(); i++){
             if (authorList.get(i).getFullName().equals(fullName)){
                 return authorList.get(i);
             }
         }
        return null;
         */


        for(Author author : authorList){
           if (author.getFullName().equals(fullName)){
               return author;
           }
       }
       return null;



    }

    public List<Author> findByBook(String bookName) {
        ArrayList<Author> authors_array = new ArrayList<Author>();

        for (Author author : authorList){
            for (Book book : author.getBookList()){
                if (book.getBookName().equals(bookName)){
                    authors_array.add(author);
                    break;
                }
            }
        }
        return authors_array;
    }

    public List<Author> findByBookId(Long bookId) {
        ArrayList<Author> authors_array = new ArrayList<Author>();

        for (Author author : authorList){
            for (Book book : author.getBookList()){
                if (book.getId().equals(bookId)){
                    authors_array.add(author);
                    break;
                }
            }
        }
        return authors_array;
    }

    public void save(Author author) {
        authorList.add(author);
    }

    public Author findById(Long id) {
        for (Author author : authorList){
            if (author.getId().equals(id)){
                return author;
            }
        }
        return null;
    }

    public List<Author> findAll() {
        return authorList;
    }

    public void update(Author author) {

        for (Author current_author : authorList){
            if (author.getId().equals(current_author.getId())) {
                current_author.setBookList(author.getBookList());
                current_author.setFullName(author.getFullName());
            }
        }

    }

    public void remove(Long id) {

        for (Author author : authorList){
            if (author.getId().equals(id)){
                authorList.remove(author);
                break;
            }
        }
    }
}
