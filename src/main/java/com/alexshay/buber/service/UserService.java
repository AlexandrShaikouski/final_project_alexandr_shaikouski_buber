package com.alexshay.buber.service;

import com.alexshay.buber.dao.Identified;
import com.alexshay.buber.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class UserService<T extends Identified<Integer>> {
    abstract public T signUp(HttpServletRequest request) throws ServiceException;
    abstract public List<T> getAll() throws ServiceException;
    abstract public void deleteUser(int id) throws ServiceException;
    abstract public T signIn(HttpServletRequest request) throws ServiceException;
    abstract public String getResetPasswordKey(String email) throws ServiceException;
    abstract public boolean checkRepasswordKey(HttpServletRequest request) throws ServiceException;
    abstract public boolean resetPassword(HttpServletRequest request) throws ServiceException;
    abstract public T getByParameter(String parametr, String value) throws ServiceException;
    abstract public void updateUser(HttpServletRequest request) throws ServiceException;

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
        return IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());
    }
    public String generateRandomString(){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] chars = str.toCharArray();
        Random random = new SecureRandom();
        String key = "";
        for(int i = 0; i < 10; i++){
            key += chars[random.nextInt(chars.length)];
        }
        return key;
    }


}
