package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    //订单id
    private String id;
    //用户ID
    private int uid;
    private int money;
    private String status;
    private Date time;
    //地址id
    private int aid;
    private Address address;

    private List<Goods> goods;
    private String username;

    public Order() {
    }

    public Order(String id, int uid, int money, String status, Date time, int aid, Address address, List<Goods> goods, String username) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.aid = aid;
        this.address = address;
        this.goods = goods;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", uid=" + uid +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", aid=" + aid +
                ", address=" + address +
                ", goods=" + goods +
                ", username='" + username + '\'' +
                '}';
    }
}
