package com.envy.library.dao;

public enum BookTableColumn {
    BOOKID("bookid"),
    NAME("title"),
    AUTHOR("authors"),
    COVER("cover");

    private final String label;

    BookTableColumn(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
