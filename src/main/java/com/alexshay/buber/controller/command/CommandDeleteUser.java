package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        String urlUser = request.getRequestURL().toString();

        String role = request.getParameter("role");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            if (role.equals("user")) {
                UserService userService = ServiceFactory.getInstance().getUserService("user");
                userService.deleteUser(id);
                responseContent.setRouter(new Router(urlUser + "?command=list_users", Router.Type.REDIRECT));
            } else {
                UserService userService = ServiceFactory.getInstance().getUserService("driver");
                userService.deleteUser(id);
                responseContent.setRouter(new Router(urlUser + "?command=list_drivers", Router.Type.REDIRECT));
            }
        }catch (ServiceException e){

        }
        return responseContent;
    }
}
