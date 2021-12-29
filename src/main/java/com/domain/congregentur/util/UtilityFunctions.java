package com.domain.congregentur.util;

import com.domain.congregentur.book.Book;

public class UtilityFunctions {

    public static boolean bookIsValid(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();
        String isbn = book.getIsbn();
        return !title.isEmpty() && !author.isEmpty() && (isbn.length() == 13 && isNumeric(isbn));
    }

    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Long.parseLong(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
