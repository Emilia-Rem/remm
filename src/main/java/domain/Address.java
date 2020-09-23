package domain;

import java.io.Serializable;

public class Address implements Serializable {

    private Integer id;
    private String detail;
    private String name;
    private String phone;
    private Integer level;

    public Address() {
    }

    public Address(Integer id, String detail, String name, String phone, Integer level) {
        this.id = id;
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }
}
