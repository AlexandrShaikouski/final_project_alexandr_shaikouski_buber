package com.alexshay.buber.service;

import com.alexshay.buber.domain.User;
import com.alexshay.buber.service.exception.ServiceException;

/**
 * Example of user service
 */
public interface UserService {

    /**
     * Sign up user
     * @param user - User
     * @return - saved user
     * @throws ServiceException should be clarify
     */
    User signUp(User user) throws ServiceException;

    // Provide your code here

}