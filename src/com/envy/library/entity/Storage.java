package com.envy.library.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private static final int CAPACITY = 500;
    private static Storage instance;
    private int currentId = 0;

    private List<Book> books = new ArrayList<>();

    private Storage() {
        books = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public boolean addBook(Book book) {
        boolean operationSuccess = false;

        if (books.size() < CAPACITY) {
            operationSuccess = books.add(book);
        }
        currentId++;
        return operationSuccess;
    }

    public boolean removeBook(Book book) {
        boolean operationSuccess = false;

        operationSuccess = books.remove(book);
        return operationSuccess;
    }

    public int getCurrentId() {
        return currentId;
    }
}
