package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.User;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandGetUserList implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        UserService userService = ServiceFactory.getInstance().getUserService("user");
        List<User> users;
        try {
            users = userService.getAll();
            request.setAttribute("listUsers", users);
            responseContent.setRouter(new Router("/jsp/admin/list-users.jsp", Router.Type.FORWARD));
        } catch (ServiceException e) {
        }
        return responseContent;

    }
}
