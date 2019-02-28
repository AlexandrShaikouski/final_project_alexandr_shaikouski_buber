package com.alexshay.buber.controller.command;

import com.alexshay.buber.domain.Bonus;
import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.TripOrder;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.dto.ResponseContent;
import com.alexshay.buber.service.BonusService;
import com.alexshay.buber.service.ServiceFactory;
import com.alexshay.buber.service.TripOrderService;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandInfoUser implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandInfoUser.class);
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        BonusService bonusService = ServiceFactory.getInstance().getBonusService();
        TripOrderService tripOrderService = ServiceFactory.getInstance().getTripOrderService();
        ResponseContent responseContent = new ResponseContent();
        String id = request.getParameter("id");
        String role = request.getParameter("role");
        try {
            if (role.endsWith("user")) {
                UserService userService = ServiceFactory.getInstance().getUserService("user");
                User user = (User)userService.getByParameter("id", id);
                List<TripOrder> orders = tripOrderService.getByParameter("client_id", id);
                List<Bonus> bonuses = bonusService.getAll();
                request.setAttribute("listBonuses", bonuses);
                request.setAttribute("listOrders", orders);
                request.setAttribute("user", user);
                request.setAttribute("role","user");
                responseContent.setRouter(new Router("/jsp/admin/info-user.jsp",Router.Type.FORWARD));
            }else {
                UserService driverService = ServiceFactory.getInstance().getUserService("driver");
                Driver driver = (Driver)driverService.getByParameter("id", id);
                List<TripOrder> orders = tripOrderService.getByParameter("driver_id", id);
                request.setAttribute("listOrders", orders);
                request.setAttribute("role","driver");
                request.setAttribute("user",driver);
                responseContent.setRouter(new Router("/jsp/admin/info-user.jsp",Router.Type.FORWARD));
            }
        }catch (ServiceException e){
            LOGGER.error(e);
        }


        return responseContent;
    }
}
