package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/5.
 */

public class LineDataBean {

    /**
     * data : [{"biz_date":"2018-11-04","qty":0},{"biz_date":"2018-11-05","qty":-13}]
     * msg : success
     * code : 0
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * biz_date : 2018-11-04
         * qty : 0
         */

        private String biz_date;
        private int qty;

        public String getBiz_date() {
            return biz_date;
        }

        public void setBiz_date(String biz_date) {
            this.biz_date = biz_date;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }
}
