package com.alexshay.buber.service.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.Role;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import com.alexshay.buber.validation.Validator;
import com.alexshay.buber.validation.impl.PasswordValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Example of user service implementation
 */
public class UserServiceImpl extends UserService<User> {
    @Override
    public User signUp(HttpServletRequest request) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("passwordUser");
            String role = request.getParameter("role");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String firstName = request.getParameter("first_name");
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPhone(phone);
            user.setFirstName(firstName);
            user.setRole(role.equals("admin") ? Role.ADMIN : Role.USER);
            user.setPassword(encryptPassword(password));
            user.setRegistrationTime(new Date());

            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            return userDao.persist(user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            return userDao.getAll();

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            userDao.delete(userDao.getByPK(id));

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (PersistException e) {
            throw new ServiceException("Failed to delete. ", e);
        }
    }

    @Override
    public User signIn(HttpServletRequest request) throws ServiceException {
        try {
            DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            String login = request.getParameter("login");
            String password = encryptPassword(request.getParameter("passwordUser"));
            User user = userDao.getByParameter("login", login);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
    }

    @Override
    public String getResetPasswordKey(String email) throws ServiceException {
        String repassword = generateRandomString();
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            User user = userDao.getByParameter("email", email);
            user.setRepasswordKey(encryptPassword(repassword));
            userDao.update(user);
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
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            User user = userDao.getByParameter("email", email);
            if (user.getRepasswordKey().equals(encryptPassword(key))) {
                user.setRepasswordKey(null);
                userDao.update(user);
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
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            if (valid == "") {
                User user = userDao.getByParameter("email",email);
                user.setPassword(encryptPassword(password));
                userDao.update(user);
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
