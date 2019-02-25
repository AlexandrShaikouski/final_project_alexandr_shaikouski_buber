package com.alexshay.buber.controller.command;

import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandResetPassword implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        ResetPassword resetPassword = new ResetPassword();
        String reset = request.getParameter("reset");

        try {
            switch (reset){
                case "send_key":
                    return resetPassword.getPageEnterKey(request);
                case "reset_password":
                    return resetPassword.getPageEnterPassword(request);
                case "finish":
                    return resetPassword.getNewPassword(request);
            }

        } catch (ServiceException e) {

        }
        responseContent.setRouter(new Router("/",Router.Type.FORWARD));
        return responseContent;
    }
}
