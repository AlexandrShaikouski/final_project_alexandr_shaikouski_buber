package com.alexshay.buber.domain;

import com.alexshay.buber.dao.Identified;

import java.util.Objects;

public class UserBonus implements Identified<Integer> {
    private int id;
    private int bonusId;
    private int userId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getBonusId() {
        return bonusId;
    }

    public void setBonusId(int bonusId) {
        this.bonusId = bonusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBonus userBonus = (UserBonus) o;
        return id == userBonus.id &&
                bonusId == userBonus.bonusId &&
                userId == userBonus.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bonusId, userId);
    }

    @Override
    public String toString() {
        return "UserBonus{" +
                "id=" + id +
                ", bonusId=" + bonusId +
                ", userId=" + userId +
                '}';
    }
}
