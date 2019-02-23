package com.alexshay.buber.service;

import com.alexshay.buber.service.impl.DriverServiceImpl;
import com.alexshay.buber.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Service factory
 */
public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();
    private Map<String, UserService> userServiceMap = new HashMap<>();

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
}
