package com.alexshay.buber.dao.impl;

import com.alexshay.buber.dao.AbstractJdbcDao;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.domain.Driver;
import com.alexshay.buber.domain.DriverStatus;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class DriverDaoImpl extends AbstractJdbcDao<Driver, Integer> implements GenericDao<Driver, Integer> {
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

    @Override
    protected List<Driver> parseResultSet(ResultSet rs) throws SQLException {

        List<Driver> driverList = new ArrayList<>();

        while (rs.next()) {
            Driver driver = new Driver();

            driver.setId(rs.getInt("id"));
            driver.setLogin(rs.getString("login"));
            driver.setPassword(rs.getString("password"));
            driver.setFirstName(rs.getString("first_name"));
            driver.setLastName(rs.getString("last_name"));
            driver.setEmail(rs.getString("email"));
            driver.setPhone(rs.getString("phone"));
            driver.setRegistrationTime(new Date(rs.getLong("registration_date")));
            driver.setLocation(rs.getString("location"));
            if(rs.getLong("status_ban") != 0) {
                driver.setStatusBan(new Date(rs.getLong("status_ban")));
            }else{
                driver.setStatusBan(null);
            }
            driver.setStatus(DriverStatus.fromValue(rs.getString("status")));

            driverList.add(driver);
        }

        return driverList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Driver object) throws SQLException {
        int counter = 0;

        statement.setString(++counter, object.getLogin());
        statement.setString(++counter, object.getPassword());
        statement.setString(++counter, object.getFirstName());
        statement.setString(++counter, object.getLastName());
        statement.setString(++counter, object.getEmail());
        statement.setString(++counter, object.getPhone());
        statement.setLong(++counter, object.getRegistrationTime().getTime());
        statement.setString(++counter, object.getStatus().value());
        statement.setString(++counter, object.getLocation());
        if(object.getStatusBan() != null) {
            statement.setLong(++counter, object.getStatusBan().getTime());
        }else{
            statement.setLong(++counter, 0);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Driver object) throws SQLException {
        prepareStatementForInsert(statement,object);
        statement.setInt(11,object.getId());
    }

    @Override
    public String getSelectQuery() {

        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {

        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {

        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {

        return DELETE_QUERY;
    }
}
