package com.envy.library.service.impl;

import com.envy.library.dao.impl.BookListDaoImpl;
import com.envy.library.entity.Book;
import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.AppServiceException;
import com.envy.library.service.ManagementService;

import java.util.ArrayList;
import java.util.List;

public class BookManagementServiceImpl implements ManagementService {
    @Override
    public boolean addBook(Book book) throws AppServiceException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        boolean result;

        try {
            result = bookListDao.add(book);
        } catch (AppDaoException e) {
            throw new AppServiceException("Error while adding book to storage", e);
        }

        return result;
    }

    @Override
    public boolean removeBook(Book book) throws AppServiceException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        boolean result;

        try {
            result = bookListDao.remove(book);
        } catch (AppDaoException e) {
            throw new AppServiceException("Error while removing book from storage", e);
        }

        return result;
    }

    @Override
    public Book findById(int bookId) throws AppDaoException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        Book targetBook = bookListDao.findById(bookId);

        return targetBook;
    }

    @Override
    public List<Book> findByAuthor(String author) throws AppDaoException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> targetBooks = new ArrayList<>();

        targetBooks = bookListDao.findByAuthor(author);

        return targetBooks;
    }

    @Override
    public List<Book> sortById() throws AppServiceException, AppDaoException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortById();

        if (sortedList.size() != bookListDao.findAll().size()) {
            throw new AppServiceException("Error in sorting by Id method");
        }

        return sortedList;
    }
}
