package com.alexshay.buber.controller.command;

import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Example of the command implementation
 */
public class CommandExample implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        // Provide your code here

        UserService userService = ServiceFactory.getInstance().getUserService();

        // Provide your code here

        throw new UnsupportedOperationException();
    }
}
