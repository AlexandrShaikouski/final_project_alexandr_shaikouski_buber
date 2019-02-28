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
import com.alexshay.buber.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

public class UserValidatorImpl implements Validator {

    @Override
    public String validate(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        String validString = "";
        String login = request.getParameter("login");
        String password = request.getParameter("passwordUser");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);


            if (driverDao.getByParameter("login", login) != null) {
                validString += validString.equals("")?
                        "Login":", login";
            }else if(userDao.getByParameter("login", login) != null){
                validString += validString.equals("")?
                        "Login":", login";
            }
            if (driverDao.getByParameter("email", email) != null) {
                validString += validString.equals("")?
                        "Email":", email";
            }else if(userDao.getByParameter("email", email) != null){
                validString += validString.equals("")?
                        "Email":", email";
            }
            if (driverDao.getByParameter("phone", phone) != null) {
                validString += validString.equals("")?
                        "Phone":", phone";
            }else if(userDao.getByParameter("phone", phone) != null){
                validString += validString.equals("")?
                        "Phone":", phone";
            }
        }catch (DaoException e){
            throw new ServiceException("Failed to get user DAO. ", e);
        }

        return validString.equals("")?null:validString + " already exist!";
    }
}