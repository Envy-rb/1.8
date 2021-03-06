package com.envy.library.controller.command.impl;

import com.envy.library.controller.command.Command;
import com.envy.library.entity.Book;
import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.AppServiceException;
import com.envy.library.service.impl.BookManagementServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FindByIdCommand implements Command {
    BookManagementServiceImpl service = new BookManagementServiceImpl();

    @Override
    public Object execute(Object parameter) throws AppDaoException {
        List<Book> result = new ArrayList<>();

        result.add(service.findById((int) parameter));
        return result;
    }
}
