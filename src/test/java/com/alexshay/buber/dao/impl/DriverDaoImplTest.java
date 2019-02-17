package com.alexshay.buber.dao.impl;

import com.alexshay.buber.dao.AbstractJdbcDao;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.DriverStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DriverDaoImplTest {
    private AbstractJdbcDao<Driver, Integer> daoDriver;
    private GenericDao<Driver, Integer> genericDao;
    private Driver driver;
    private static final String DELETE_QUERY = "DELETE FROM driver_account WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE driver_account " +
            "SET login = ?, password = ?, first_name = ?, last_name = ?, " +
            "email = ?, phone = ?, registration_date = ?, status = ?,  location = ?, " +
            "status_ban = ? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM driver_account";
    private static final String CREATE_QUERY = "INSERT INTO driver_account " +
            "(login, password, first_name, last_name, email, phone, registration_date, status, location, " +
            "status_ban) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Before
    public void init() throws DaoException {
        driver = new Driver();
        driver.setId(1);
        driver.setLogin("Alex");
        driver.setPassword("871FF76E24362EFA16E7F39D65EE380ADE9129D969E895CE34E5DB54252604FB");
        driver.setFirstName("Alexandr");
        driver.setLastName("Shaikouski");
        driver.setEmail("sash_shay@mail.ru");
        driver.setPhone("+375256182421");
        driver.setLocation("53.8853376,27.5546112,12");
        driver.setRegistrationTime(new Date());
        driver.setStatus(DriverStatus.ONLINE);

        daoDriver = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(Driver.class);
        genericDao = JdbcDaoFactory.getInstance().getDao(Driver.class);
    }

    @Test
    public void AbstractJdbcDaoForDriverTest() throws PersistException, DaoException {
        Driver driver1 = null;
        try {
            driver1 = genericDao.persist(driver);
            driver = genericDao.getByPK(driver1.getId());
            assertEquals(driver1, driver);
            driver.setLogin("Max");
            genericDao.update(driver);
            driver = genericDao.getByPK(driver.getId());
            assertEquals("Max", driver.getLogin());
        } finally {
            if (driver1 != null) {
                genericDao.delete(driver1);
            }
        }
    }

    @Test
    public void getSelectQuery() {
        assertEquals(SELECT_QUERY, daoDriver.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        assertEquals(CREATE_QUERY, daoDriver.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        assertEquals(UPDATE_QUERY, daoDriver.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        assertEquals(DELETE_QUERY, daoDriver.getDeleteQuery());
    }

    @After
    public void destroy() throws PersistException {
        daoDriver = null;
        genericDao = null;
        driver = null;
    }
}
