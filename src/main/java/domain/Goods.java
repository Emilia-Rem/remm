package domain;


import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {

    private Integer id;
    private String name;
    private Date pubdate;
    private String picture;
    private Integer price;
    private Integer star;
    private String intro;
    private Integer typeid;

    public Goods() {
    }

    public Goods(Integer id, String name, Date pubdate, String piceure, Integer price, Integer star, String intro, Integer typeid) {
        this.id = id;
        this.name = name;
        this.pubdate = pubdate;
        this.picture = piceure;
        this.price = price;
        this.star = star;
        this.intro = intro;
        this.typeid = typeid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setPicture(String piceure) {
        this.picture = piceure;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pubdate=" + pubdate +
                ", piceure='" + picture + '\'' +
                ", price=" + price +
                ", star=" + star +
                ", intro='" + intro + '\'' +
                ", typeid=" + typeid +
                '}';
    }
}
