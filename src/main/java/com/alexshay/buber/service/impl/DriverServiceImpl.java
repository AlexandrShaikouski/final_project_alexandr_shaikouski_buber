package com.alexshay.buber.service.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.DriverStatus;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class DriverServiceImpl extends UserService<Driver> {
    @Override
    public Driver signUp(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("passwordUser");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String firstName = request.getParameter("first_name");

            Driver driver = new Driver();
            driver.setLogin(login);
            driver.setEmail(email);
            driver.setPhone(phone);
            driver.setFirstName(firstName);
            driver.setPassword(encryptPassword(password));

            driver.setRegistrationTime(new Date());
            driver.setStatus(DriverStatus.OFF_LINE);
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            return driverDao.persist(driver);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get driver DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save driver. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password",e);
        }
    }

    @Override
    public List<Driver> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            return driverDao.getAll();

        } catch (DaoException e) {
            throw new ServiceException("Failed to get driver DAO. ", e);

        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException{
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            driverDao.delete(driverDao.getByPK(id));

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (PersistException e) {
            throw new ServiceException("Failed to save delete. ", e);
        }
    }

}