package domain;

import java.util.Date;

public class OrderWrapper {
    private String name;
    private Date pubdate;
    private String picture;
    private Integer price;
    private Integer star;
    private Integer num;
    private Integer money;

    public OrderWrapper() {
    }

    public OrderWrapper(String name, Date pubdate, String picture, Integer price, Integer star, Integer num, Integer money) {
        this.name = name;
        this.pubdate = pubdate;
        this.picture = picture;
        this.price = price;
        this.star = star;
        this.num = num;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "OrderWrapper{" +
                "name='" + name + '\'' +
                ", pubdate=" + pubdate +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", star=" + star +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
