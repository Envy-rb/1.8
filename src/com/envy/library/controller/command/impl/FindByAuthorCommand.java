package com.envy.library.controller.command.impl;

import com.envy.library.controller.command.Command;
import com.envy.library.entity.Book;
import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.AppServiceException;
import com.envy.library.service.impl.BookManagementServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FindByAuthorCommand implements Command {
    BookManagementServiceImpl service = new BookManagementServiceImpl();

    @Override
    public Object execute(Object parameter) throws AppServiceException, AppDaoException {
        List<Book> result = new ArrayList<>();

        result = service.findByAuthor((String)parameter);
        return result;
    }
}
