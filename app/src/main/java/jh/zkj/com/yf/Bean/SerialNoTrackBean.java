package jh.zkj.com.yf.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/11/17
 * use
 */
public class SerialNoTrackBean {


    /**
     * sku_full_name : OPPO R 128G 网络1 玫瑰红,VIVO X20 128G 玫瑰红
     * serials : 5555586/0000043
     * details : [{"biz_date":"2018-11-17","biz_type":"PI","bill_no":"PI00039","source":"供应商A","source_type":"supplier","target":"总仓","target_type":"warehouse","qty":1,"price":1800,"amount":1800,"remark":"试试等等21224","serial_info_uuid":"58fe50170be949418c7f6daa62c0ab3f","first_in_time":"2018-11-19 09:56:13","sku_full_name":"OPPO R 128G 网络1 玫瑰红","sku_name":"R 128G","create_time":"2018-11-19 09:55:06","create_user_name":"超级管理员","clerk_name":"超级管理员","serial01":"5555586/0000043","serial02":"","serial03":"","serial04":"","serial05":"","serial06":"","serial07":"","serial08":"","serial09":"","serial10":"","sort_field":"2018-11-19 09:56:132018-11-172018-11-19 09:55:06"},{"biz_date":"2018-11-19","biz_type":"SS","bill_no":"SS00181","source":"总仓","source_type":"warehouse","target":"零售客户","target_type":"customer","qty":-1,"price":2000,"amount":-2000,"remark":"","serial_info_uuid":"58fe50170be949418c7f6daa62c0ab3f","first_in_time":"2018-11-19 09:56:13","sku_full_name":"OPPO R 128G 网络1 玫瑰红","sku_name":"R 128G","create_time":"2018-11-19 22:27:11","create_user_name":"超级管理员","clerk_name":"","serial01":"5555586/0000043","serial02":"","serial03":"","serial04":"","serial05":"","serial06":"","serial07":"","serial08":"","serial09":"","serial10":"","sort_field":"2018-11-19 09:56:132018-11-192018-11-19 22:27:11"},{"biz_date":"2018-11-20","biz_type":"PI","bill_no":"PI00339","source":"供应商A","source_type":"supplier","target":"总仓","target_type":"warehouse","qty":1,"price":2000,"amount":2000,"remark":"试试等等21224","serial_info_uuid":"fed8e323572447569604f0cff87757dc","first_in_time":"2018-11-22 11:39:49","sku_full_name":"VIVO X20 128G 玫瑰红","sku_name":"X20 128G","create_time":"2018-11-20 14:08:20","create_user_name":"超级管理员","clerk_name":"超级管理员","serial01":"999978000043","serial02":"5555586/0000043","serial03":"","serial04":"","serial05":"","serial06":"","serial07":"","serial08":"","serial09":"","serial10":"","sort_field":"2018-11-22 11:39:492018-11-202018-11-20 14:08:20"}]
     */

    private String sku_full_name;
    private String serials;
    private ArrayList<DetailsBean> details;

    public String getSku_full_name() {
        return sku_full_name;
    }

    public void setSku_full_name(String sku_full_name) {
        this.sku_full_name = sku_full_name;
    }

    public String getSerials() {
        return serials;
    }

    public void setSerials(String serials) {
        this.serials = serials;
    }

    public ArrayList<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * biz_date : 2018-11-17
         * biz_type : PI
         * bill_no : PI00039
         * source : 供应商A
         * source_type : supplier
         * target : 总仓
         * target_type : warehouse
         * qty : 1
         * price : 1800
         * amount : 1800
         * remark : 试试等等21224
         * serial_info_uuid : 58fe50170be949418c7f6daa62c0ab3f
         * first_in_time : 2018-11-19 09:56:13
         * sku_full_name : OPPO R 128G 网络1 玫瑰红
         * sku_name : R 128G
         * create_time : 2018-11-19 09:55:06
         * create_user_name : 超级管理员
         * clerk_name : 超级管理员
         * serial01 : 5555586/0000043
         * serial02 :
         * serial03 :
         * serial04 :
         * serial05 :
         * serial06 :
         * serial07 :
         * serial08 :
         * serial09 :
         * serial10 :
         * sort_field : 2018-11-19 09:56:132018-11-172018-11-19 09:55:06
         */

        private String biz_date;
        private String biz_type;
        private String bill_no;
        private String source;
        private String source_type;
        private String target;
        private String target_type;
        private double qty;
        private double price;
        private double amount;
        private String remark;
        private String serial_info_uuid;
        private String first_in_time;
        private String sku_full_name;
        private String sku_name;
        private String create_time;
        private String create_user_name;
        private String clerk_name;
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
        private String sort_field;
        private int colorType;

        public String getBiz_date() {
            return biz_date;
        }

        public void setBiz_date(String biz_date) {
            this.biz_date = biz_date;
        }

        public String getBiz_type() {
            return biz_type;
        }

        public void setBiz_type(String biz_type) {
            this.biz_type = biz_type;
        }

        public String getBill_no() {
            return bill_no;
        }

        public void setBill_no(String bill_no) {
            this.bill_no = bill_no;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getTarget_type() {
            return target_type;
        }

        public void setTarget_type(String target_type) {
            this.target_type = target_type;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSerial_info_uuid() {
            return serial_info_uuid;
        }

        public void setSerial_info_uuid(String serial_info_uuid) {
            this.serial_info_uuid = serial_info_uuid;
        }

        public String getFirst_in_time() {
            return first_in_time;
        }

        public void setFirst_in_time(String first_in_time) {
            this.first_in_time = first_in_time;
        }

        public String getSku_full_name() {
            return sku_full_name;
        }

        public void setSku_full_name(String sku_full_name) {
            this.sku_full_name = sku_full_name;
        }

        public String getSku_name() {
            return sku_name;
        }

        public void setSku_name(String sku_name) {
            this.sku_name = sku_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCreate_user_name() {
            return create_user_name;
        }

        public void setCreate_user_name(String create_user_name) {
            this.create_user_name = create_user_name;
        }

        public String getClerk_name() {
            return clerk_name;
        }

        public void setClerk_name(String clerk_name) {
            this.clerk_name = clerk_name;
        }

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

        public String getSort_field() {
            return sort_field;
        }

        public void setSort_field(String sort_field) {
            this.sort_field = sort_field;
        }

        public int getColorType() {
            return colorType;
        }

        public void setColorType(int colorType) {
            this.colorType = colorType;
        }
    }
}
