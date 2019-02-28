package com.alexshay.buber.dao.impl;

import com.alexshay.buber.dao.AbstractJdbcDao;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.domain.User;
import com.alexshay.buber.domain.UserBonus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBonusDaoImpl extends AbstractJdbcDao<UserBonus, Integer> implements GenericDao<UserBonus, Integer> {
    private static final String DELETE_QUERY = "DELETE FROM user_bonus WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user_bonus " +
            "SET bonus_id = ?, user_id = ?" +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM user_bonus";
    private static final String CREATE_QUERY = "INSERT INTO user_bonus " +
            "(bonus_id, user_id) " +
            "VALUES (?, ?)";
    @Override
    protected List<UserBonus> parseResultSet(ResultSet rs) throws SQLException {
        List<UserBonus> userBonuses = new ArrayList<>();

        while (rs.next()) {
            UserBonus userBonus = new UserBonus();
            userBonus.setId(rs.getInt("id"));
            userBonus.setBonusId(rs.getInt("bonus_id"));
            userBonus.setUserId(rs.getInt("user_id"));

            userBonuses.add(userBonus);
        }

        return userBonuses;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserBonus object) throws SQLException {
        int counter = 0;

        statement.setInt(++counter, object.getBonusId());
        statement.setInt(++counter, object.getUserId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserBonus object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setInt(3,object.getId());
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
