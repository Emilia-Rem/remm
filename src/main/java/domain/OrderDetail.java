package domain;

public class OrderDetail {
    private Integer id;
    private String oid;
    private Integer pid;
    private Integer num;
    private Integer money;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, String oid, Integer pid, Integer num, Integer money) {
        this.id = id;
        this.oid = oid;
        this.pid = pid;
        this.num = num;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
        return "OrderDetail{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", pid=" + pid +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
