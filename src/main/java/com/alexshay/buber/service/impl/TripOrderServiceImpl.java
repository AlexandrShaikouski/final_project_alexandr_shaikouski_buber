package com.alexshay.buber.service.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.Bonus;
import com.alexshay.buber.domain.OrderStatus;
import com.alexshay.buber.domain.TripOrder;
import com.alexshay.buber.service.TripOrderService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TripOrderServiceImpl implements TripOrderService {
    DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);

    @Override
    public List<TripOrder> getAll() throws ServiceException {
        try {
            GenericDao<TripOrder, Integer> tripOrderDao = daoFactory.getDao(TripOrder.class);
            return tripOrderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        }
    }

    @Override
    public List<TripOrder> getByParameter(String parameter, String value) throws ServiceException {
        try {
            GenericDao<TripOrder, Integer> tripOrderDao = daoFactory.getDao(TripOrder.class);
            return tripOrderDao.getByParameter(parameter,value);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public TripOrder creatTripOrder(HttpServletRequest request) throws ServiceException {
        try {
            GenericDao<TripOrder, Integer> tripOrderDao = daoFactory.getDao(TripOrder.class);
            GenericDao<Bonus, Integer> bonusDao = daoFactory.getDao(Bonus.class);
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            int bonusId = Integer.parseInt(request.getParameter("bonus_id"));
            int clientId = Integer.parseInt(request.getParameter("client_id"));
            float factor = bonusDao.getByPK(bonusId).getFactor();
            float price = getPrice(request)*(1.0f-factor);
            TripOrder tripOrder = new TripOrder();
            tripOrder.setFrom(from);
            tripOrder.setTo(to);
            tripOrder.setClientId(clientId);
            tripOrder.setStatusOrder(OrderStatus.WAITING);
            tripOrder.setPrice(price);
            tripOrder.setBonusId(bonusId);


            tripOrder = tripOrderDao.persist(tripOrder);
            return tripOrder;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }
    }

    @Override
    public void deleteTripOrder(int id) throws ServiceException {
        try {
            GenericDao<TripOrder, Integer> tripOrderDao = daoFactory.getDao(TripOrder.class);

            TripOrder tripOrder = tripOrderDao.getByPK(id);

            tripOrderDao.delete(tripOrder);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }
    }

    @Override
    public void updateTripOrder(HttpServletRequest request) throws ServiceException {
        try {
            GenericDao<TripOrder, Integer> tripOrderDao = daoFactory.getDao(TripOrder.class);
            int id = Integer.parseInt(request.getParameter("id"));
            int driverId = Integer.parseInt(request.getParameter("driver_id"));
            String statusOrder = request.getParameter("status_order");

            TripOrder tripOrder = tripOrderDao.getByPK(id);
            tripOrder.setDriverId(driverId);
            tripOrder.setStatusOrder(OrderStatus.valueOf(statusOrder));
            tripOrderDao.update(tripOrder);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }
    }

    private float getPrice(HttpServletRequest request) {
        return 1.2f;
    }
}
