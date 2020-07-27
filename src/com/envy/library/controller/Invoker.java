package com.envy.library.controller;

import com.envy.library.controller.command.Command;
import com.envy.library.controller.command.impl.*;
import com.envy.library.entity.Book;
import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.AppServiceException;

import java.util.List;

public class Invoker {
    private static Invoker instance;

    private Command addBookCommand = new AddBookCommand();
    private Command findByAuthorCommand = new FindByAuthorCommand();
    private Command findByIdCommand = new FindByIdCommand();
    private Command removeBookCommand = new RemoveBookCommand();
    private Command sortByIdCommand = new SortByIdCommand();

    private Invoker() {

    }

    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }

        return instance;
    }

    public boolean addBook(Book book) throws AppServiceException, AppDaoException {
        return (boolean) addBookCommand.execute(book);
    }

    public List<Book> findByAuthor(String author) throws AppServiceException, AppDaoException {
        return (List<Book>) findByAuthorCommand.execute(author);
    }
    public List<Book> findById(int id) throws AppServiceException, AppDaoException {
        return (List<Book>) findByIdCommand.execute(id);
    }
    public boolean removeBook(Book book) throws AppServiceException, AppDaoException {
        return (boolean) removeBookCommand.execute(book);
    }
    public List<Book> sortById() throws AppServiceException, AppDaoException {
        return (List<Book>) sortByIdCommand.execute(null);
    }
}
