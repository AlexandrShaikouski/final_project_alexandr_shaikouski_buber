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
import com.alexshay.buber.validation.Validator;
import com.alexshay.buber.validation.impl.PasswordValidatorImpl;

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
            throw new ServiceException("Failed to use Algorithm for password", e);
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
    public void deleteUser(int id) throws ServiceException {
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

    @Override
    public Driver signIn(HttpServletRequest request) throws ServiceException {
        try {
            DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            String login = request.getParameter("login");
            String password = encryptPassword(request.getParameter("passwordUser"));
            Driver driver = driverDao.getByParameter("login", login);
            if (driver != null){
                if(driver.getPassword().equals(password)){
                    driver.setStatus(DriverStatus.ONLINE);
                    driverDao.update(driver);
                    return driver;
                }
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (PersistException e) {
            throw new ServiceException("Failed to save delete. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
    }

    @Override
    public String getResetPasswordKey(String email) throws ServiceException {
        String repassword = generateRandomString();
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<Driver, Integer> userDao = daoFactory.getDao(Driver.class);
            Driver driver = userDao.getByParameter("email", email);
            driver.setRepasswordKey(encryptPassword(repassword));
            userDao.update(driver);

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
        return repassword;
    }

    @Override
    public boolean checkRepasswordKey(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        String key = request.getParameter("key");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            Driver user = driverDao.getByParameter("email", email);
            if(user.getRepasswordKey().equals(encryptPassword(key))){
                user.setRepasswordKey(null);
                driverDao.update(user);
                return true;
            }

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
        return false;
    }

    @Override
    public boolean resetPassword(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Validator validator = new PasswordValidatorImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("passwordUser");
        String valid = validator.validate(request);

        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            if (valid == "") {
                Driver driver = driverDao.getByParameter("email",email);
                driver.setPassword(encryptPassword(password));
                driverDao.update(driver);
                return true;
            }
        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
        return false;
    }

}