package com.alexshay.buber.validation;

import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    String validate(HttpServletRequest httpServletRequest) throws ServiceException;
}
