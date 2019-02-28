package com.alexshay.buber.service.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.Bonus;
import com.alexshay.buber.domain.Role;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.domain.UserBonus;
import com.alexshay.buber.service.UserService;
import com.alexshay.buber.service.exception.ServiceException;
import com.alexshay.buber.validation.Validator;
import com.alexshay.buber.validation.impl.UserValidatorImpl;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Example of user service implementation
 */
public class UserServiceImpl extends UserService<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private Validator validator = new UserValidatorImpl();

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
            List<User> userList = userDao.getByParameter("login", login);

            if (userList != null) {
                User user = userList.get(0);
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
            List<User> userList = userDao.getByParameter("email", email);
            User user = null;
            if (userList != null) {
                user = userList.get(0);
            }
            if (user != null) {
                user.setRepasswordKey(encryptPassword(repassword));
                userDao.update(user);
            }

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
            List<User> userList;
            userList = userDao.getByParameter("email", email);
            User user = null;
            if (userList != null) {
                user = userList.get(0);
            }

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
        String email = request.getParameter("email");
        String password = request.getParameter("passwordUser");
        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            List<User> userList = userDao.getByParameter("email", email);
            User user = null;
            if (userList != null) {
                user = userList.get(0);
            }
            if (user != null) {
                user.setPassword(encryptPassword(password));
                userDao.update(user);
                return true;
            }
            return false;
        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to use Algorithm for password", e);
        }
    }

    @Override
    public User getByParameter(String parameter, String value) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            GenericDao<Bonus, Integer> bonusDao = daoFactory.getDao(Bonus.class);
            GenericDao<UserBonus, Integer> userBonusDao = daoFactory.getDao(UserBonus.class);

            List<User> userList = userDao.getByParameter(parameter, value);
            User user = null;
            if (userList != null) {
                user = userList.get(0);
                List<UserBonus> userBonuses = userBonusDao.getByParameter("user_id", user.getId().toString());
                if (userBonuses != null) {
                    List<Bonus> bonuses = userBonuses.stream().map(s -> {
                        try {
                            return bonusDao.getByPK(s.getBonusId());
                        } catch (DaoException e) {
                            LOGGER.error(e);
                        }
                        return null;
                    }).collect(Collectors.toList());
                    user.setBonuses(bonuses);
                }

            }

            return user;
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
        int bonusId = Integer.parseInt(request.getParameter("bonus_id"));
            try {
                GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
                User user = userDao.getByPK(id);

                if (user != null) {
                    user.setFirstName(firstName);
                    user.setEmail(email);
                    user.setLogin(login);
                    user.setLocation(location);
                    user.setPhone(phone);
                    String banTime = request.getParameter("ban_time");
                    if (!banTime.equals("none")) {
                        user.setStatusBan(getStatusBan(user, request));
                    }

                    if (bonusId != -1) {
                        GenericDao<UserBonus, Integer> userBonusDao = daoFactory.getDao(UserBonus.class);
                        UserBonus userBonus = new UserBonus();
                        userBonus.setUserId(id);
                        userBonus.setBonusId(bonusId);
                        userBonusDao.persist(userBonus);
                    }
                    userDao.update(user);
                }

            } catch (DaoException e) {
                throw new ServiceException("Failed to get user DAO. ", e);

            } catch (PersistException e) {
                throw new ServiceException("Failed to save entity. ", e);
            }

        }




    public Date getStatusBan(User user, HttpServletRequest request) {
        Date date = user.getStatusBan();

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


