package com.envy.library.dao.impl;

import com.envy.library.connection.ConnectionPool;
import com.envy.library.creator.BookCreator;
import com.envy.library.dao.ListDao;
import com.envy.library.entity.Book;
import com.envy.library.entity.Storage;
import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.ConnectionDatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookListDaoImpl implements ListDao {
    private static final String SQL_ADD_BOOK = "INSERT INTO book(name, author, cover)" +
            "VALUES (?, ?, ?)";
    private static final String SQL_REMOVE_BOOK = "DELETE FROM book " +
            "WHERE name = ? AND author = ? AND cover = ?";
    private static final String SQL_FIND_ALL_BOOKS = "SELECT bookid, name, author, cover" +
            " FROM book";
    private static final String SQL_FIND_BOOK_BY_ID = SQL_FIND_ALL_BOOKS + " WHERE bookid = ?";
    private static final String SQL_FIND_BOOKS_BY_AUTHOR = SQL_FIND_ALL_BOOKS + " WHERE author = ?";
    private static final String SQL_SORT_BOOKS_BY_ID = " ORDER BY bookid";

    @Override
    public boolean add(Book book) throws AppDaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isBookAdded;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_BOOK)) {
            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getAuthor().toString());
            statement.setString(3, book.getCover().getName());
            isBookAdded = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new AppDaoException("Error while adding book to storage", e);
        }

        return isBookAdded;
    }

    @Override
    public boolean remove(Book book) throws AppDaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isBookRemoved;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_BOOK)) {
            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getAuthor().toString());
            statement.setString(3, book.getCover().getName());
            isBookRemoved = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new AppDaoException("Error while adding book to storage", e);
        }

        return isBookRemoved;
    }

    @Override
    public Book findById(int bookId) throws AppDaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        BookCreator bookCreator = new BookCreator();
        Book targetBook = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BOOK_BY_ID)) {
            statement.setLong(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    targetBook = bookCreator.createBookFromSql(resultSet);
                }
            }
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new AppDaoException("Error while finding book by Id from storage", e);
        }

        return targetBook;
    }

    @Override
    public List<Book> findAll() throws AppDaoException {
        List<Book> targetBooks = new ArrayList<>();
        BookCreator bookCreator = new BookCreator();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        String sqlQuery = SQL_FIND_ALL_BOOKS;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Book book = bookCreator.createBookFromSql(resultSet);
                targetBooks.add(book);
            }
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new AppDaoException("Error while finding all books from storage", e);
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) throws AppDaoException {
        List<Book> targetBooks = new ArrayList<>();
        BookCreator bookCreator = new BookCreator();
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BOOKS_BY_AUTHOR)) {
            statement.setString(1, author);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = bookCreator.createBookFromSql(resultSet);
                    targetBooks.add(book);
                }
            }
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new AppDaoException("Error while finding books by author from storage", e);
        }

        return targetBooks;
    }

    @Override
    public List<Book> sortById() throws AppDaoException {
        List<Book> targetBooks = new ArrayList<>();
        BookCreator bookCreator = new BookCreator();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        String sqlQuery = SQL_FIND_ALL_BOOKS + SQL_SORT_BOOKS_BY_ID;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Book book = bookCreator.createBookFromSql(resultSet);
                targetBooks.add(book);
            }
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new AppDaoException("Error while finding all books from storage", e);
        }

        return targetBooks;
    }
}
