package com.alexshay.buber.validation.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.service.exception.ServiceException;
import com.alexshay.buber.validation.Validator;

import javax.servlet.http.HttpServletRequest;

public class EmailValidatorImpl implements Validator {
    @Override
    public String validate(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        String validString = "";
        String email = request.getParameter("email");
        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            if (driverDao.getByParameter("email", email) != null) {
                validString = "driver";
            } else if(userDao.getByParameter("email", email) != null){
                validString = "user";
            }else{
                validString = "Email do not exist";
            }
        }catch (DaoException e){
            throw new ServiceException("Failed to get user DAO. ", e);
        }
        return validString;
    }
}
