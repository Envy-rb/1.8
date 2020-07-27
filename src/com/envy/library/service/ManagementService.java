package com.envy.library.service;

import com.envy.library.entity.Book;
import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.AppServiceException;

import java.util.List;

public interface ManagementService {
    boolean addBook(Book book) throws AppServiceException;

    boolean removeBook(Book book) throws AppServiceException;

    Book findById(int bookId) throws AppDaoException;

    List<Book> findByAuthor(String author) throws AppServiceException, AppDaoException;

    List<Book> sortById() throws AppServiceException, AppDaoException;
}
