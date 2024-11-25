package com.example.slstore.practice.model;

import java.util.ArrayList;
import java.util.List;

public class LoginPerson {
    private String name;
    private List<Book> books = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
