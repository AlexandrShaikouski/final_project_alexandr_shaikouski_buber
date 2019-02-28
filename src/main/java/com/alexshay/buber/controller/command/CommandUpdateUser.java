package com.alexshay.buber.controller.command;

import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandUpdateUser implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandUpdateUser.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        String role = request.getParameter("role");
        String servletPath = request.getServletPath();
        String urlAdmin = request.getRequestURL().toString().replace(servletPath, "") + "/jsp/admin/success-create.jsp";
        try {
            if (role.equals("user")) {
                UserService userService = ServiceFactory.getInstance().getUserService("user");
                userService.updateUser(request);
            }else{
                UserService driverService = ServiceFactory.getInstance().getUserService("driver");
                driverService.updateUser(request);
            }
            responseContent.setRouter(new Router(urlAdmin, Router.Type.REDIRECT));
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        return responseContent;
    }
}
