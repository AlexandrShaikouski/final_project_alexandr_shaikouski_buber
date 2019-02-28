package com.alexshay.buber.service;

import com.alexshay.buber.domain.TripOrder;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TripOrderService {
    List<TripOrder> getAll() throws ServiceException;
    List<TripOrder> getByParameter(String parameter, String value) throws ServiceException;
    TripOrder creatTripOrder(HttpServletRequest request) throws ServiceException;
    void deleteTripOrder(int id) throws  ServiceException;
    void updateTripOrder(HttpServletRequest request) throws  ServiceException;
}
