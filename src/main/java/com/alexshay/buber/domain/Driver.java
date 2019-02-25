package com.alexshay.buber.domain;

import com.alexshay.buber.dao.Identified;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class Driver implements Identified<Integer> {
    private int id;
    private String login;
    private String password;
    private String repasswordKey;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String location;
    private Date registrationTime;
    private Date statusBan;
    private DriverStatus status;

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getStatusBan() {
        return statusBan;
    }

    public void setStatusBan(Date statusBan) {
        this.statusBan = statusBan;
    }


    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public String getRepasswordKey() {
        return repasswordKey;
    }

    public void setRepasswordKey(String repasswordKey) {
        this.repasswordKey = repasswordKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id &&
                Objects.equals(login, driver.login) &&
                Objects.equals(password, driver.password) &&
                Objects.equals(repasswordKey, driver.repasswordKey) &&
                Objects.equals(firstName, driver.firstName) &&
                Objects.equals(lastName, driver.lastName) &&
                Objects.equals(email, driver.email) &&
                Objects.equals(phone, driver.phone) &&
                Objects.equals(location, driver.location) &&
                Objects.equals(registrationTime, driver.registrationTime) &&
                Objects.equals(statusBan, driver.statusBan) &&
                status == driver.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, repasswordKey, firstName, lastName, email, phone, location, registrationTime, statusBan, status);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", repasswordKey='" + repasswordKey + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", registrationTime=" + registrationTime +
                ", statusBan=" + statusBan +
                ", status=" + status +
                '}';
    }
}
