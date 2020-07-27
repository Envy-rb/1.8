package com.envy.library.controller.command.impl;

import com.envy.library.controller.command.Command;
import com.envy.library.entity.Book;
import com.envy.library.exception.AppServiceException;
import com.envy.library.service.impl.BookManagementServiceImpl;

public class RemoveBookCommand implements Command {
    BookManagementServiceImpl service = new BookManagementServiceImpl();

    @Override
    public Object execute(Object parameter) throws AppServiceException {
        boolean result;

        result = service.removeBook((Book)parameter);
        return result;
    }
}
