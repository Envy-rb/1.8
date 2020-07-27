package com.envy.library.creator;

import com.envy.library.dao.BookTableColumn;
import com.envy.library.entity.Book;
import com.envy.library.entity.Cover;
import com.envy.library.entity.Storage;
import com.envy.library.validator.BookValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookCreator {
    private static final String BLOCK_SEPARATOR = ",";

    public Book createBookFromSql (ResultSet resultSet) throws SQLException {
        int bookId = resultSet.getInt(BookTableColumn.BOOKID.getLabel());
        String name = resultSet.getString(BookTableColumn.NAME.getLabel());
        String author = resultSet.getString(BookTableColumn.AUTHOR.getLabel());
        Cover cover = Cover.valueOf(resultSet.getString(BookTableColumn.COVER.getLabel()));

        Book book = new Book(bookId, name, author, cover);

        return book;
    }

    public Optional<Book> createBook(String data) {
        BookValidator validator = new BookValidator();
        Optional<Book> result = Optional.empty();
        String[] bookData = data.split(BLOCK_SEPARATOR);

        Storage storage = Storage.getInstance();
        int bookId = storage.getCurrentId() + 1;
        String name = bookData[0].trim();
        String author = bookData[1].trim();
        Cover cover = Cover.valueOf(bookData[2].trim());

        Optional<Book> temp = Optional.of(new Book(bookId, name, author, cover));
        if (validator.validateBook(temp.get())) {
            result = temp;
        }
        return result;
    }
}
