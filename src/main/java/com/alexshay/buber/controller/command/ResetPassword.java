package com.alexshay.buber.controller.command;

import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.email.MailGenerator;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import com.alexshay.buber.validation.Validator;
import com.alexshay.buber.validation.impl.EmailValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class ResetPassword {
    private Validator validator;
    private MailGenerator mailGenerator;
    private static final String SUBJECT = "Reset password";
    private static final String MESSAGE = "This is your enter key \n";
    private ResponseContent responseContent;

    public ResetPassword(){
        validator = new EmailValidatorImpl();
        mailGenerator = new MailGenerator();
        responseContent = new ResponseContent();
    }
    public ResponseContent getPageEnterKey(HttpServletRequest request) throws ServiceException {
        String email = request.getParameter("email");
        String reset = request.getParameter("reset");
        String valid = validator.validate(request);
        if(valid.equals("driver")){
            request.setAttribute("reset", reset);
            request.setAttribute("email",email);
            request.setAttribute("role","driver");
            UserService driverService = ServiceFactory.getInstance().getUserService("driver");
            setResponseContent(email, driverService);
        }else if(valid.equals("user")){
            request.setAttribute("reset", reset);
            request.setAttribute("email",email);
            request.setAttribute("role","user");
            UserService userService = ServiceFactory.getInstance().getUserService("user");
            setResponseContent(email, userService);
        }else {
            request.setAttribute("message", valid);
            responseContent.setRouter(new Router("/pages/reset-password.jsp", Router.Type.FORWARD));
        }

        return responseContent;
    }


    public ResponseContent getPageEnterPassword(HttpServletRequest request) throws ServiceException {
        String role = request.getParameter("role");
        String emaile = request.getParameter("email");
        String reset = request.getParameter("reset");
        request.setAttribute("role",role);
        request.setAttribute("email",emaile);
        boolean checkKey = false;
        responseContent.setRouter(new Router("/pages/reset-password.jsp", Router.Type.FORWARD));
        if(role.equals("user")){
            UserService userService = ServiceFactory.getInstance().getUserService("user");
            checkKey = userService.checkRepasswordKey(request);
        }else{
            UserService driverService = ServiceFactory.getInstance().getUserService("driver");
            checkKey = driverService.checkRepasswordKey(request);
        }

        if (checkKey){
            request.setAttribute("reset",reset);
        }else{
            request.setAttribute("reset","send_key");
            request.setAttribute("message", "Wrong key");
        }
        return responseContent;
    }

    public ResponseContent getNewPassword(HttpServletRequest request) throws ServiceException {
        String servletPath = request.getServletPath();
        String urlMainPage = request.getRequestURL().toString().replace(servletPath,"");
        String role = request.getParameter("role");
        String email = request.getParameter("email");

        if(role.equals("user")){
            UserService userService = ServiceFactory.getInstance().getUserService("user");
            if(userService.resetPassword(request)){
                responseContent.setRouter(new Router(urlMainPage, Router.Type.REDIRECT));
                return responseContent;
            }
        }else {
            UserService driverService = ServiceFactory.getInstance().getUserService("driver");
            if(driverService.resetPassword(request)){
                responseContent.setRouter(new Router(urlMainPage, Router.Type.REDIRECT));
                return responseContent;
            }
        }
        responseContent.setRouter(new Router("/pages/reset-password.jsp", Router.Type.FORWARD));
        request.setAttribute("reset", "reset_password");
        request.setAttribute("role", role);
        request.setAttribute("email", email);
        return responseContent;
    }


    private void setResponseContent(String email, UserService userService) throws ServiceException {
        String repassword = userService.getResetPasswordKey(email);
        mailGenerator.sendMessage(SUBJECT, MESSAGE + repassword, Arrays.asList(email));
        responseContent.setRouter(new Router("/pages/reset-password.jsp",Router.Type.FORWARD));
    }
}
