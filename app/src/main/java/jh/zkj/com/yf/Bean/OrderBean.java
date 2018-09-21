package jh.zkj.com.yf.Bean;

/**
 * Created by wdefer
 * on 2018/9/20
 * use 订单bean   用于零售下单、零售订单
 */
public class OrderBean {
    private String commodityNumber;
    private String name;
    private String price;
    private long num;
    private String totalPrice;
    private String ps;
    //记录listview的item位置
//    private int position;

    public String getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(String commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

//    public int getPosition() {
//        return position;
//    }
//
//    public void setPosition(int position) {
//        this.position = position;
//    }

    public void clearData(){
        commodityNumber = "";
        name = "";
        price = "";
        num = 1;
        totalPrice = "";
        ps = "";
//        position = 0;
    }
}
