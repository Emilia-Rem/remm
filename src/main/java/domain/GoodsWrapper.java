package domain;

import java.util.Date;

public class GoodsWrapper {
    private Integer id;
    private String name;
    private Date pubdate;
    private Integer price;
    private String intro;
    private Integer typeid;
    private String typeName;

    public GoodsWrapper() {
    }

    public GoodsWrapper(Integer id, String name, Date pubdate, Integer price, String intro, Integer typeid, String typeName) {
        this.id = id;
        this.name = name;
        this.pubdate = pubdate;
        this.price = price;
        this.intro = intro;
        this.typeid = typeid;
        this.typeName = typeName;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "GoodsWrapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pubdate=" + pubdate +
                ", price=" + price +
                ", intro='" + intro + '\'' +
                ", typeid=" + typeid +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
