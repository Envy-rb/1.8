package com.envy.library.validator;

import com.envy.library.entity.Book;

public class BookValidator {
    public boolean validateBook(Book book) {
        return validateName(book.getName()) && validateAuthor(book.getAuthor());
    }

    private boolean validateName(String name) {
        boolean isValid = false;

        if (name != null && !name.isBlank()) {
            isValid = true;
        }

        return isValid;
    }

    private boolean validateAuthor(String author) {
        boolean isValid = false;

        if (author != null && !author.isBlank()) {
            isValid = true;
        }

        return isValid;
    }
}