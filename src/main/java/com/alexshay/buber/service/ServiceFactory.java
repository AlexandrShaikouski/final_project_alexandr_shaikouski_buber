package com.alexshay.buber.service;

import com.alexshay.buber.service.impl.BonusServiceImpl;
import com.alexshay.buber.service.impl.DriverServiceImpl;
import com.alexshay.buber.service.impl.TripOrderServiceImpl;
import com.alexshay.buber.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Service factory
 */
public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();
    private Map<String, UserService> userServiceMap = new HashMap<>();
    private BonusService bonusService = new BonusServiceImpl();
    private TripOrderService tripOrderService = new TripOrderServiceImpl();

    private ServiceFactory() {
        userServiceMap.put("user", new UserServiceImpl());
        userServiceMap.put("driver", new DriverServiceImpl());
    }

    public static ServiceFactory getInstance() {
        return instance;
    }



    public UserService getUserService(String nameEntity) {
        return userServiceMap.get(nameEntity);
    }

    public BonusService getBonusService(){return bonusService;}
    public TripOrderService getTripOrderService(){return tripOrderService;}
}
