package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import com.alexshay.buber.validation.Validator;
import com.alexshay.buber.validation.impl.UserValidatorImpl;

import javax.servlet.http.HttpServletRequest;

public class CommandCreateUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        Validator validator = new UserValidatorImpl();
        ResponseContent responseContent = new ResponseContent();
        UserService userService = ServiceFactory.getInstance().getUserService("user");
        UserService driverService = ServiceFactory.getInstance().getUserService("driver");
        String servletPath = request.getServletPath();
        String urlAdmin = request.getRequestURL().toString().replace(servletPath,"") +  "/jsp/admin/success-create.jsp";
        String urlClient = request.getRequestURL().toString().replace(servletPath,"") +  "/jsp/client/client.jsp";


        String flag = request.getParameter("flag");
        String role = request.getParameter("role");
        request.setAttribute("role",role);
        try {
            String validate = validator.validate(request);
            if(validate == null) {
                switch (role) {
                    case "client":
                        User client = (User) userService.signUp(request);
                        request.setAttribute("user", client);
                        if (flag.equals("admin")) {
                            responseContent.setRouter(new Router(urlAdmin, Router.Type.REDIRECT));
                        } else {
                            responseContent.setRouter(new Router(urlClient, Router.Type.REDIRECT));
                        }
                        break;
                    case "admin":
                        User admin = (User) userService.signUp(request);
                        responseContent.setRouter(new Router(urlAdmin, Router.Type.REDIRECT));
                        break;
                    case "driver":
                        Driver driver = (Driver) driverService.signUp(request);
                        responseContent.setRouter(new Router(urlAdmin, Router.Type.REDIRECT));
                        break;
                }
            }else {
                request.setAttribute("message_user", validate);
                if(flag.equals("admin")){
                    responseContent.setRouter(new Router("/jsp/admin/create-user.jsp", Router.Type.FORWARD));
                }else{
                    responseContent.setRouter(new Router("/pages/register.jsp", Router.Type.FORWARD));
                }


            }
        } catch (ServiceException e) {

        }
        return responseContent;
    }

}
