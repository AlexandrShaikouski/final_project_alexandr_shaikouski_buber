package com.alexshay.buber.service;

import com.alexshay.buber.domain.Bonus;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BonusService {
    List<Bonus> getAll() throws ServiceException;
    Bonus getById(int id) throws ServiceException;
    void creatBonus(HttpServletRequest request) throws ServiceException;
    void deleteBonusById(int id) throws  ServiceException;
}
