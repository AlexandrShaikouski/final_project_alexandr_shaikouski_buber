package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandGetDriverList implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        UserService driverService = ServiceFactory.getInstance().getUserService("driver");
        List<Driver> drivers;
        try {
            drivers = driverService.getAll();
            request.setAttribute("listDrivers", drivers);
            responseContent.setRouter(new Router("/jsp/admin/list-drivers.jsp", Router.Type.FORWARD));
            return responseContent;
        } catch (ServiceException e) {

        }
        return responseContent;
    }
}
