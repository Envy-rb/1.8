package com.envy.library.controller.command;

import com.envy.library.exception.AppDaoException;
import com.envy.library.exception.AppServiceException;

public interface Command {
    Object execute(Object parameter) throws AppServiceException, AppDaoException;
}
