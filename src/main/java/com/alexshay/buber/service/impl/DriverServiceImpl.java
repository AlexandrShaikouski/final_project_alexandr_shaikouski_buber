package com.alexshay.buber.service.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.*;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import org.apache.commons.lang3.time.DateUtils;

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
            List<Driver> drivers = driverDao.getByParameter("login", login);
            Driver driver = null;
            if(drivers != null){
                driver = drivers.get(0);
            }

            if (driver != null){
                if(driver.getPassword().equals(password)){
                    driver.setStatus(DriverStatus.ONLINE);
                    driverDao.update(driver);
                }
            }
            return driver;
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
            Driver driver = userDao.getByParameter("email", email).get(0);
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
            Driver user = driverDao.getByParameter("email", email).get(0);
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
        String email = request.getParameter("email");
        String password = request.getParameter("passwordUser");

        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            Driver driver = driverDao.getByParameter("email",email).get(0);
            driver.setPassword(encryptPassword(password));
            driverDao.update(driver);
            return true;
        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
    }

    @Override
    public Driver getByParameter(String parameter, String value) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            List<Driver> userList = driverDao.getByParameter(parameter, value);
            Driver driver = null;
            if (userList != null){
                driver = userList.get(0);
            }
            return driver;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public void updateUser(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        int id = Integer.parseInt(request.getParameter("id"));
        String login = request.getParameter("login");
        String location = request.getParameter("location");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String firstName = request.getParameter("name");
        try {
            GenericDao<Driver, Integer> driverDao = daoFactory.getDao(Driver.class);
            Driver driver = driverDao.getByPK(id);

            if (driver != null) {
                driver.setFirstName(firstName);
                driver.setEmail(email);
                driver.setLogin(login);
                driver.setLocation(location);
                driver.setPhone(phone);
                String banTime = request.getParameter("ban_time");
                if (!banTime.equals("none")) {
                    driver.setStatusBan(getStatusBan(driver, request));
                }

                driverDao.update(driver);
            }

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save entity. ", e);
        }

    }




    public Date getStatusBan(Driver driver, HttpServletRequest request) {
        Date date = driver.getStatusBan();

        String banTime = request.getParameter("ban_time");
        int countTimeBan = Integer.parseInt(request.getParameter("count_time_ban"));
        switch (banTime) {
            case "hour":
                return date == null ? DateUtils.addHours(new Date(), countTimeBan) : DateUtils.addMonths(date, countTimeBan);
            case "day":
                return date == null ? DateUtils.addHours(new Date(), countTimeBan) : DateUtils.addDays(date, countTimeBan);
            case "week":
                return date == null ? DateUtils.addHours(new Date(), countTimeBan) : DateUtils.addWeeks(date, countTimeBan);
            case "month":
                return date == null ? DateUtils.addHours(new Date(), countTimeBan) : DateUtils.addMonths(date, countTimeBan);
            case "year":
                return date == null ? DateUtils.addHours(new Date(), countTimeBan) : DateUtils.addYears(date, countTimeBan);
            default:
                throw new IllegalArgumentException();
        }
    }

}