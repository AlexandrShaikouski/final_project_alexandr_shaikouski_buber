package com.alexshay.buber.domain;

import com.alexshay.buber.dao.Identified;

import java.util.Objects;

public class Bonus implements Identified<Integer> {
    private int id;
    private String name;
    private float factor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return id == bonus.id &&
                Double.compare(bonus.factor, factor) == 0 &&
                Objects.equals(name, bonus.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, factor);
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", factor=" + factor +
                '}';
    }
}
