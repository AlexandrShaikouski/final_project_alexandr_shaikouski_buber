package com.alexshay.buber.dao.impl;

import com.alexshay.buber.dao.AbstractJdbcDao;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.ConnectionPoolException;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.OrderStatus;
import com.alexshay.buber.domain.Role;
import com.alexshay.buber.domain.TripOrder;
import com.alexshay.buber.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TripOrderDaoImplTest {
    private AbstractJdbcDao<TripOrder, Integer> daoTripOrder;
    private TripOrder tripOrder;
    private User user;
    private GenericDao<TripOrder, Integer> genericDao;
    private GenericDao<User, Integer> daoUser;
    private static final String DELETE_QUERY = "DELETE FROM trip_order WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE trip_order " +
            "SET from_x = ?, to_y = ?, status_order = ?, price = ?, " +
            "client_id = ?, driver_id = ?, bonus_id = ? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM trip_order";
    private static final String CREATE_QUERY = "INSERT INTO trip_order " +
            "(from_x, to_y, status_order, price, client_id, driver_id, bonus_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Before
    public void init() throws DaoException, ConnectionPoolException, PersistException {

        user = new User();
        user.setId(1);
        user.setLogin("Max");
        user.setPassword("871FF76E24362EFA16E7F39D65EE380ADE9129D969E895CE34E5DB54252604FB");
        user.setFirstName("Alexandr");
        user.setLastName("Shaikouski");
        user.setEmail("alex_shay@mail.ru");
        user.setPhone("+375256182422");
        user.setLocation("53.8853376,27.5546112,12");
        user.setRegistrationTime(new Date());
        user.setRole(Role.ADMIN);
        daoUser = JdbcDaoFactory.getInstance().getDao(User.class);
        user = daoUser.persist(user);


        tripOrder = new TripOrder();
        tripOrder.setFrom("53.8853376,27.5546112,12");
        tripOrder.setTo("53.8853376,27.5546112,13");
        tripOrder.setStatusOrder(OrderStatus.WAITING);
        tripOrder.setPrice(3.4f);
        tripOrder.setClientId(user.getId());


        daoTripOrder = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(TripOrder.class);
        genericDao = JdbcDaoFactory.getInstance().getDao(TripOrder.class);
    }

    @Test
    public void AbstractJdbcDaoForTripOrderTest() throws DaoException, PersistException {
        TripOrder tripOrder1 = null;
        try {
            tripOrder1 = genericDao.persist(tripOrder);
            tripOrder = genericDao.getByPK(tripOrder1.getId());
            assertEquals(tripOrder1, tripOrder);
            tripOrder.setStatusOrder(OrderStatus.IN_PROGRESS);
            genericDao.update(tripOrder);
            tripOrder = genericDao.getByPK(tripOrder.getId());
            assertEquals(OrderStatus.IN_PROGRESS, tripOrder.getStatusOrder());
        } finally {
            if (tripOrder1 != null) {
                genericDao.delete(tripOrder);
            }
        }


    }
    @Test
    public void getSelectQuery() {
        assertEquals(SELECT_QUERY,daoTripOrder.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        assertEquals(CREATE_QUERY,daoTripOrder.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        assertEquals(UPDATE_QUERY,daoTripOrder.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        assertEquals(DELETE_QUERY,daoTripOrder.getDeleteQuery());
    }

    @After
    public void destroy() throws PersistException {
        daoUser.delete(user);
        daoTripOrder = null;
        tripOrder = null;
        user = null;
        genericDao = null;
        daoUser = null;
    }
}
