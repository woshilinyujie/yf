package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/5.
 */

public class ShopNameBean {


    /**
     * data : [{"uuid":"03f1d065adbd45809f05fe2914bf20dc","code":"00001003","name":"舟山营销中心"},{"uuid":"05ae915f66e343c69dc2774231641bfc","code":"00001002001","name":"乐清店"},{"uuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","code":"00001","name":"浙江公司test"},{"uuid":"1544fe2349fb4406892b10f4846c03ca","code":"00001006","name":"杭州营销中心"},{"uuid":"1a09e57a4eb24927b22eed2a8de1f263","code":"00001002002","name":"门店2"},{"uuid":"418fbedeeff148858e7a03811a8f2743","code":"00001006001001","name":"西湖店-1"},{"uuid":"538114f712384f4b8ede565b0417da5f","code":"00001009","name":"test2"},{"uuid":"5d381b5618234a23866569bc40dc35e0","code":"00001003001","name":"门店3.1"},{"uuid":"60d71120c9534d83b47b84688ee485ca","code":"00001004","name":"金华营销中心"},{"uuid":"8973281bea5e46e3b9dec0888c1dbd14","code":"00001008","name":"test"},{"uuid":"911e17305ace45afa846b064bffba374","code":"00001006002","name":"武林店"},{"uuid":"aeb7cb68ecd2455c8e1bfa56050c5a73","code":"00001005","name":"金华营销中心2"},{"uuid":"bdd703b5a0074f66b30f37a1fca24198","code":"00001002","name":"温州营销中心"},{"uuid":"c1c587708dae46a69155cff8b167873f","code":"00001006001","name":"西湖店"},{"uuid":"c284a4f479ef4f819a7547da1b158a9e","code":"00001007","name":"丽水营销中心"}]
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
         * uuid : 03f1d065adbd45809f05fe2914bf20dc
         * code : 00001003
         * name : 舟山营销中心
         */

        private String uuid;
        private String code;
        private String name;
        public boolean isSelect=false;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
