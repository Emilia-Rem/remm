package domain;

import java.io.Serializable;

public class Cart implements Serializable {

   private Integer id;
   private Integer pid;
   private Goods goods;
   private Integer num;
   private Integer money;

    public Cart() {
    }

    public Cart(Integer id, Integer pid, Goods goods, Integer num, Integer money) {
        this.id = id;
        this.pid = pid;
        this.goods = goods;
        this.num = num;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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
        return "Cart{" +
                "id=" + id +
                ", pid=" + pid +
                ", goods=" + goods +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
