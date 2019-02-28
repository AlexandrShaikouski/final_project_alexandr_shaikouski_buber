package com.alexshay.buber.service.impl;

import com.alexshay.buber.dao.DaoFactory;
import com.alexshay.buber.dao.DaoFactoryType;
import com.alexshay.buber.dao.FactoryProducer;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.dao.exception.DaoException;
import com.alexshay.buber.dao.exception.PersistException;
import com.alexshay.buber.domain.Bonus;
import com.alexshay.buber.service.BonusService;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BonusServiceImpl implements BonusService {
    private DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);




    @Override
    public List<Bonus> getAll() throws ServiceException {
        try {
            GenericDao<Bonus,Integer> bonusDao = daoFactory.getDao(Bonus.class);
            return bonusDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public Bonus getById(int id) throws ServiceException {
        try {
            GenericDao<Bonus,Integer> bonusDao = daoFactory.getDao(Bonus.class);
            return bonusDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public void creatBonus(HttpServletRequest request) throws ServiceException {
        try {
            GenericDao<Bonus,Integer> bonusDao = daoFactory.getDao(Bonus.class);
            String name = request.getParameter("name");
            float factor = Float.parseFloat(request.getParameter("factor"))/100;
            Bonus bonus = new Bonus();
            bonus.setName(name);
            bonus.setFactor(factor);

            bonusDao.persist(bonus);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }
    }

    @Override
    public void deleteBonusById(int id) throws ServiceException {
        try {
            GenericDao<Bonus,Integer> bonusDao = daoFactory.getDao(Bonus.class);
            Bonus bonus = bonusDao.getByPK(id);
            bonusDao.delete(bonus);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }
    }
}
