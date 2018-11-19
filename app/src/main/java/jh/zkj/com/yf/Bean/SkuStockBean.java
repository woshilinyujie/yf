package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/11/17
 * use
 */
public class SkuStockBean implements Serializable{


    /**
     * totalQty : 642
     * totalPrice : 407.5757541656494
     * list : [{"skuFullNameList":[{"sku_full_name":"23412","price":0,"qty":19},{"sku_full_name":"666test","price":0,"qty":128},{"sku_full_name":"gter","price":0,"qty":10}],"price":116.66666603088379,"qty":580,"name":"仓库01"}]
     */

    private int totalQty;
    private double totalPrice;
    private ArrayList<ListBean> list;

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<ListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * skuFullNameList : [{"sku_full_name":"23412","price":0,"qty":19},{"sku_full_name":"666test","price":0,"qty":128},{"sku_full_name":"gter","price":0,"qty":10}]
         * price : 116.66666603088379
         * qty : 580
         * name : 仓库01
         */

        private double price;
        private int qty;
        private String name;
        private ArrayList<SkuFullNameListBean> skuFullNameList;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<SkuFullNameListBean> getSkuFullNameList() {
            return skuFullNameList;
        }

        public void setSkuFullNameList(ArrayList<SkuFullNameListBean> skuFullNameList) {
            this.skuFullNameList = skuFullNameList;
        }

        public static class SkuFullNameListBean implements Serializable{
            /**
             * sku_full_name : 23412
             * price : 0
             * qty : 19
             */

            private String sku_full_name;
            private int price;
            private int qty;

            public String getSku_full_name() {
                return sku_full_name;
            }

            public void setSku_full_name(String sku_full_name) {
                this.sku_full_name = sku_full_name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }
        }
    }
}
