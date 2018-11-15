package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/15.
 */

public class CRMCalibrateBean {

    /**
     * data : {"uuid":"5a558ec1400d4582ac00526248f591ef","mobilePhone":"18600000000","weixinOpenid":null,"alipayid":null,"identType":null,"identNo":"","name":"姐姐","password":"$2a$10$eu210v/qe0/79prVhUChSOirvGNGXMtnpA0Q1gJo74OTWXVnaJ1nS","sex":1,"birthday":null,"remark":null,"validFlag":1,"companyCode":null,"productType":null}
     * msg : success
     * code : 0
     */

    private DataBean data;
    private String msg;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * uuid : 5a558ec1400d4582ac00526248f591ef
         * mobilePhone : 18600000000
         * weixinOpenid : null
         * alipayid : null
         * identType : null
         * identNo :
         * name : 姐姐
         * password : $2a$10$eu210v/qe0/79prVhUChSOirvGNGXMtnpA0Q1gJo74OTWXVnaJ1nS
         * sex : 1
         * birthday : null
         * remark : null
         * validFlag : 1
         * companyCode : null
         * productType : null
         */

        private String uuid;
        private String mobilePhone;
        private Object weixinOpenid;
        private Object alipayid;
        private Object identType;
        private String identNo;
        private String name;
        private String password;
        private int sex;
        private Object birthday;
        private Object remark;
        private int validFlag;
        private Object companyCode;
        private Object productType;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public Object getWeixinOpenid() {
            return weixinOpenid;
        }

        public void setWeixinOpenid(Object weixinOpenid) {
            this.weixinOpenid = weixinOpenid;
        }

        public Object getAlipayid() {
            return alipayid;
        }

        public void setAlipayid(Object alipayid) {
            this.alipayid = alipayid;
        }

        public Object getIdentType() {
            return identType;
        }

        public void setIdentType(Object identType) {
            this.identType = identType;
        }

        public String getIdentNo() {
            return identNo;
        }

        public void setIdentNo(String identNo) {
            this.identNo = identNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getValidFlag() {
            return validFlag;
        }

        public void setValidFlag(int validFlag) {
            this.validFlag = validFlag;
        }

        public Object getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(Object companyCode) {
            this.companyCode = companyCode;
        }

        public Object getProductType() {
            return productType;
        }

        public void setProductType(Object productType) {
            this.productType = productType;
        }
    }
}
