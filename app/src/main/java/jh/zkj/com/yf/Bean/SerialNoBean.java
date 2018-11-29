package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/11/17
 * use
 */
public class SerialNoBean implements Serializable{


    /**
     * content : [{"warehouse_name":"总仓","sku_full_name":"VIVO X20 128G  玫瑰红","current_stock_age":"0","exceed_stock_age":"0","stock_age":0,"stock_price":"0","stock_cost":"0","warehouse_uuid":"33ffa4038ab4493c8b6146f95a33ac31","serial01":"111000111112","serial02":"111000111112222","serial03":"","serial04":"","serial05":"","serial06":"","serial07":"","serial08":"","serial09":"","serial10":""}]
     * totalElements : 155
     * totalPages : 16
     * number : 0
     * size : 10
     * summary : {}
     */

    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private SummaryBean summary;
    private ArrayList<ContentBean> content;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SummaryBean getSummary() {
        return summary;
    }

    public void setSummary(SummaryBean summary) {
        this.summary = summary;
    }

    public ArrayList<ContentBean> getContent() {
        return content;
    }

    public void setContent(ArrayList<ContentBean> content) {
        this.content = content;
    }

    public static class SummaryBean implements Serializable{
    }

    public static class ContentBean implements Serializable{
        /**
         * warehouse_name : 总仓
         * sku_full_name : VIVO X20 128G  玫瑰红
         * current_stock_age : 0
         * exceed_stock_age : 0
         * stock_age : 0
         * stock_price : 0
         * stock_cost : 0
         * warehouse_uuid : 33ffa4038ab4493c8b6146f95a33ac31
         * serial01 : 111000111112
         * serial02 : 111000111112222
         * serial03 :
         * serial04 :
         * serial05 :
         * serial06 :
         * serial07 :
         * serial08 :
         * serial09 :
         * serial10 :
         */

        private String warehouse_name;
        private String sku_full_name;
        private String current_stock_age;
        private String exceed_stock_age;
//        private int stock_age;
        private String stock_price;
        private String stock_cost;
        private String warehouse_uuid;
//        private String stock_qty;
        private String serial01;
        private String serial02;
        private String serial03;
        private String serial04;
        private String serial05;
        private String serial06;
        private String serial07;
        private String serial08;
        private String serial09;
        private String serial10;

        public String getWarehouse_name() {
            return warehouse_name;
        }

        public void setWarehouse_name(String warehouse_name) {
            this.warehouse_name = warehouse_name;
        }

        public String getSku_full_name() {
            return sku_full_name;
        }

        public void setSku_full_name(String sku_full_name) {
            this.sku_full_name = sku_full_name;
        }

        public String getCurrent_stock_age() {
            return current_stock_age;
        }

        public void setCurrent_stock_age(String current_stock_age) {
            this.current_stock_age = current_stock_age;
        }

        public String getExceed_stock_age() {
            return exceed_stock_age;
        }

        public void setExceed_stock_age(String exceed_stock_age) {
            this.exceed_stock_age = exceed_stock_age;
        }

//        public int getStock_age() {
//            return stock_age;
//        }
//
//        public void setStock_age(int stock_age) {
//            this.stock_age = stock_age;
//        }

        public String getStock_price() {
            return stock_price;
        }

        public void setStock_price(String stock_price) {
            this.stock_price = stock_price;
        }

        public String getStock_cost() {
            return stock_cost;
        }

        public void setStock_cost(String stock_cost) {
            this.stock_cost = stock_cost;
        }

        public String getWarehouse_uuid() {
            return warehouse_uuid;
        }

        public void setWarehouse_uuid(String warehouse_uuid) {
            this.warehouse_uuid = warehouse_uuid;
        }

//        public String getStock_qty() {
//            return stock_qty;
//        }
//
//        public void setStock_qty(String stock_qty) {
//            this.stock_qty = stock_qty;
//        }

        public String getSerial01() {
            return serial01;
        }

        public void setSerial01(String serial01) {
            this.serial01 = serial01;
        }

        public String getSerial02() {
            return serial02;
        }

        public void setSerial02(String serial02) {
            this.serial02 = serial02;
        }

        public String getSerial03() {
            return serial03;
        }

        public void setSerial03(String serial03) {
            this.serial03 = serial03;
        }

        public String getSerial04() {
            return serial04;
        }

        public void setSerial04(String serial04) {
            this.serial04 = serial04;
        }

        public String getSerial05() {
            return serial05;
        }

        public void setSerial05(String serial05) {
            this.serial05 = serial05;
        }

        public String getSerial06() {
            return serial06;
        }

        public void setSerial06(String serial06) {
            this.serial06 = serial06;
        }

        public String getSerial07() {
            return serial07;
        }

        public void setSerial07(String serial07) {
            this.serial07 = serial07;
        }

        public String getSerial08() {
            return serial08;
        }

        public void setSerial08(String serial08) {
            this.serial08 = serial08;
        }

        public String getSerial09() {
            return serial09;
        }

        public void setSerial09(String serial09) {
            this.serial09 = serial09;
        }

        public String getSerial10() {
            return serial10;
        }

        public void setSerial10(String serial10) {
            this.serial10 = serial10;
        }
    }
}
