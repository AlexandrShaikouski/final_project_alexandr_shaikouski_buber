package com.alexshay.buber.dao.impl;

import com.alexshay.buber.dao.AbstractJdbcDao;
import com.alexshay.buber.dao.GenericDao;
import com.alexshay.buber.domain.OrderStatus;
import com.alexshay.buber.domain.TripOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripOrderDaoImpl extends AbstractJdbcDao<TripOrder, Integer> implements GenericDao<TripOrder, Integer> {
    private static final String DELETE_QUERY = "DELETE FROM trip_order WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE trip_order " +
            "SET from_x = ?, to_y = ?, status_order = ?, price = ?, " +
            "client_id = ?, driver_id = ?, bonus_id = ? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM trip_order";
    private static final String CREATE_QUERY = "INSERT INTO trip_order " +
            "(from_x, to_y, status_order, price, client_id, driver_id, bonus_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    @Override
    protected List<TripOrder> parseResultSet(ResultSet rs) throws SQLException {
        List<TripOrder> tripOrders = new ArrayList<>();
        while (rs.next()){
            TripOrder tripOrder = new TripOrder();
            tripOrder.setId(rs.getInt("id"));
            tripOrder.setFrom(rs.getString("from_x"));
            tripOrder.setTo(rs.getString("to_y"));
            tripOrder.setStatusOrder(OrderStatus.fromValue(rs.getString("status_order")));
            tripOrder.setPrice(rs.getFloat("price"));
            tripOrder.setClientId(rs.getInt("client_id"));
            tripOrder.setDriverId(rs.getInt("driver_id"));
            tripOrder.setBonusId(rs.getInt("bonus_id"));
            tripOrders.add(tripOrder);
        }
        return tripOrders;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TripOrder object) throws SQLException {
        int counter = 0;

        statement.setString(++counter, object.getFrom());
        statement.setString(++counter, object.getTo());
        statement.setString(++counter, object.getStatusOrder().value());
        statement.setFloat(++counter, object.getPrice());
        statement.setInt(++counter, object.getClientId());
        if(object.getDriverId() != 0) {
            statement.setInt(++counter, object.getDriverId());
        }else{
            statement.setNull(++counter, object.getDriverId());
        }
        if(object.getBonusId() != 0) {
            statement.setInt(++counter, object.getBonusId());
        }else{
            statement.setNull(++counter, object.getBonusId());
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TripOrder object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setInt(8, object.getId());
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
