package com.envy.library.entity;

import java.util.Objects;

public class Book {
    private int bookId;
    private String name;
    private String author;
    private Cover cover;

    public Book(int bookId, String name, String author, Cover cover) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.cover = cover;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Cover getCover() {
        return cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                name.equals(book.name) &&
                author.equals(book.author) &&
                cover == book.cover;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookId=").append(bookId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", cover=").append(cover);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, name, author, cover);
    }
}
