package com.envy.library.dao;

import com.envy.library.entity.Book;
import com.envy.library.exception.AppDaoException;

import java.util.List;

public interface ListDao {
    boolean add(Book book) throws AppDaoException;

    boolean remove(Book book) throws AppDaoException;

    Book findById(int bookId) throws AppDaoException;

    List<Book> findAll() throws AppDaoException;

    List<Book> findByAuthor(String author) throws AppDaoException;

    List<Book> sortById() throws AppDaoException;

}
