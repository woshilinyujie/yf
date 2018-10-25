package jh.zkj.com.yf.Bean;

import java.util.ArrayList;

/**
 * Created by wdefer
 * 2018/10/23
 * use  下单bean  用于存放数据 不对接接口
 */
public class RetailOrderBean {
    //业务员
    private ArrayList<SalesmanBean> salesmanList = new ArrayList<>();

    //客户
    private ClientInfoBean client;

    //商品数据
    private ArrayList<CommodityInfoBean> comList = new ArrayList<>();

    public ArrayList<SalesmanBean> getSalesmanList() {
        return salesmanList;
    }

    public void clearSalesmanList(){
        salesmanList.clear();
    }

    public void addAllSalesmanList(ArrayList<SalesmanBean> salesmanList) {
        this.salesmanList.addAll(salesmanList);
    }

    public ClientInfoBean getClient() {
        return client;
    }

    public void setClient(ClientInfoBean client) {
        this.client = client;
    }

    public ArrayList<CommodityInfoBean> getComList() {
        return comList;
    }

    public void addComList(ArrayList<CommodityInfoBean> beanList) {
        comList.clear();
        comList.addAll(beanList);
    }
}
