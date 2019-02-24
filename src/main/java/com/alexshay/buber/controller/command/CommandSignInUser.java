package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.Role;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandSignInUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {

        ResponseContent responseContent = new ResponseContent();
        UserService userService = ServiceFactory.getInstance().getUserService("user");
        String servletPath = request.getServletPath();
        String urlAdmin = request.getRequestURL().toString().replace(servletPath,"") +  "/jsp/admin/admin.jsp";
        String urlClient = request.getRequestURL().toString().replace(servletPath,"") +  "/jsp/client/client.jsp";
        String urlDriver = request.getRequestURL().toString().replace(servletPath,"") +  "/jsp/driver/driver.jsp";
        try {
            User user = (User)userService.signIn(request);
            if(user != null){
                if(user.getRole().equals(Role.ADMIN)){
                    request.setAttribute("admin", user);
                    responseContent.setRouter(new Router(urlAdmin,Router.Type.REDIRECT));
                }else{
                    request.setAttribute("user", user);
                    responseContent.setRouter(new Router(urlClient,Router.Type.REDIRECT));
                }
            }else{
                UserService driverService = ServiceFactory.getInstance().getUserService("driver");
                Driver driver = (Driver)driverService.signIn(request);
                if (driver != null){
                    request.setAttribute("driver", driver);
                    responseContent.setRouter(new Router(urlDriver,Router.Type.REDIRECT));
                }else {
                    request.setAttribute("error_message", "Not such data!");
                    responseContent.setRouter(new Router("/",Router.Type.FORWARD));
                }
            }
        } catch (ServiceException e) {

        }


        return responseContent;
    }
}
