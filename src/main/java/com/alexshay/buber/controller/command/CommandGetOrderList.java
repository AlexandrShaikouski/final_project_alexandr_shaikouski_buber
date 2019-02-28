package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.TripOrder;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.TripOrderService;
import com.alexshay.buber.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandGetOrderList implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandGetOrderList.class);
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        TripOrderService tripOrderService = ServiceFactory.getInstance().getTripOrderService();
        List<TripOrder> tripOrders;
        try {
            tripOrders = tripOrderService.getAll();
            request.setAttribute("listOrders", tripOrders);
            responseContent.setRouter(new Router("/jsp/admin/list-orders.jsp", Router.Type.FORWARD));
            return responseContent;
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        return responseContent;
    }
}
