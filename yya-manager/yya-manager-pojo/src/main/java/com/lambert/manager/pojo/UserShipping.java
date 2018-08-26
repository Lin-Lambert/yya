package com.lambert.manager.pojo;

import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.persistence.*;

@Table(name = "usershipping")
public class UserShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receiverName")
    private String name;

    @Column(name = "receiverPhone")
    private String phone;

    @Column(name = "receiverMobile")
    private String mobile;

    @Column(name = "receiverState")
    private String state;

    @Column(name = "receiverCity")
    private String city;

    @Column(name = "receiverDistrict")
    private String district;

    @Column(name = "receiverAddress")
    private String address;

    @Column(name = "receiverZip")
    private String zip;

    @Column(name = "userId")
    private Long userId;

    @Override
    public String toString() {
        return "UserShipping{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
