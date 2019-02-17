package com.alexshay.buber.dao.impl;

import com.alexshay.buber.dao.AbstractJdbcDao;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.domain.Role;
import com.alexshay.buber.domain.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements GenericDao<User, Integer> {
    private static final String DELETE_QUERY = "DELETE FROM user_account WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user_account " +
            "SET login = ?, password = ?, first_name = ?, last_name = ?, " +
            "email = ?, phone = ?, registration_date = ?, location = ?, " +
            "status_ban = ?, role_id = ? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM user_account";
    private static final String CREATE_QUERY = "INSERT INTO user_account " +
            "(login, password, first_name, last_name, email, phone, registration_date, location, status_ban," +
            "role_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {

        List<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setRegistrationTime(new Date(rs.getLong("registration_date")));
            user.setLocation(rs.getString("location"));
            if(rs.getLong("status_ban") != 0) {
                user.setStatusBan(new Date(rs.getLong("status_ban")));
            }else{
                user.setStatusBan(null);
            }
            user.setRole(Role.fromValue(""+rs.getInt("role_id")));

            userList.add(user);
        }

        return userList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        int counter = 0;

        statement.setString(++counter, object.getLogin());
        statement.setString(++counter, object.getPassword());
        statement.setString(++counter, object.getFirstName());
        statement.setString(++counter, object.getLastName());
        statement.setString(++counter, object.getEmail());
        statement.setString(++counter, object.getPhone());
        statement.setLong(++counter, object.getRegistrationTime().getTime());
        statement.setString(++counter, object.getLocation());
        if(object.getStatusBan() != null) {
            statement.setLong(++counter, object.getStatusBan().getTime());
        }else{
            statement.setLong(++counter, 0);
        }
        statement.setInt(++counter, Integer.parseInt(object.getRole().value()));
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        prepareStatementForInsert(statement,object);
        statement.setInt(11, object.getId());
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
