package com.alexshay.buber.domain;

import com.alexshay.buber.dao.Identified;

import java.util.Objects;

public class TripOrder implements Identified<Integer> {
    private int id;
    private String from;
    private String to;
    private OrderStatus statusOrder;
    private float price;
    private int clientId;
    private int driverId;
    private int bonusId;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getBonusId() {
        return bonusId;
    }

    public void setBonusId(int bonusId) {
        this.bonusId = bonusId;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public OrderStatus getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(OrderStatus statusOrder) {
        this.statusOrder = statusOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripOrder tripOrder = (TripOrder) o;
        return id == tripOrder.id &&
                Double.compare(tripOrder.price, price) == 0 &&
                clientId == tripOrder.clientId &&
                driverId == tripOrder.driverId &&
                bonusId == tripOrder.bonusId &&
                Objects.equals(from, tripOrder.from) &&
                Objects.equals(to, tripOrder.to) &&
                statusOrder == tripOrder.statusOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, statusOrder, price, clientId, driverId, bonusId);
    }

    @Override
    public String toString() {
        return "TripOrder{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", statusOrder=" + statusOrder +
                ", price=" + price +
                ", clientId=" + clientId +
                ", driverId=" + driverId +
                ", bonusId=" + bonusId +
                '}';
    }
}
