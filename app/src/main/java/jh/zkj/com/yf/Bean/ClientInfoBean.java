package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/10/24
 * use
 */
public class ClientInfoBean implements Serializable{


    /**
     * records : [{"uuid":"18768533bc324eae8275cb636ec6a9ba","mobilePhone":"15168698798","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"339005199009156844","name":"coco","tel":"82779863","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"},{"uuid":"23e56a2ea6ae4b6ea7dcec59ce787085","mobilePhone":"15168698258","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"339005199009156844","name":"cao","tel":"82779863","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"},{"uuid":"3832b5c26fc8482c81b28f2817e3a5ba","mobilePhone":"15168698278","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"339005199009156844","name":"coco","tel":"82779863","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"},{"uuid":"6abfd4fb82fc4253af685253aeb6a403","mobilePhone":"15168725696","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"338660199501286988","name":"cgp","tel":"0571-82996358","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"},{"uuid":"77b688af6c30454ca1d0831583a39698","mobilePhone":"15168277777","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"339005199001126877","name":"cgp","tel":"0571-82779863","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"},{"uuid":"7c3996d2ff744c4aaf3307d68c0034d6","mobilePhone":"151686874125","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"339005199009156844","name":"coco","tel":"15168698798","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"},{"uuid":"a01021a193e24011ab13211f57c74da0","mobilePhone":"15168698745","constIdentTypeUuid":"zkj-erp-properties-credit_type-idcard","identNo":"339005199009156844","name":"coco","tel":"15168698798","sex":1,"birthday":null,"cardNo":null,"deviceUuid":null,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22"}]
     * total : 7
     * size : 10
     * current : 1
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private int pages;
    private ArrayList<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * uuid : 18768533bc324eae8275cb636ec6a9ba
         * mobilePhone : 15168698798
         * constIdentTypeUuid : zkj-erp-properties-credit_type-idcard
         * identNo : 339005199009156844
         * name : coco
         * tel : 82779863
         * sex : 1
         * birthday : null
         * cardNo : null
         * deviceUuid : null
         * remark : null
         * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
         */

        private String uuid;
        private String mobilePhone;
        private String constIdentTypeUuid;
        private String identNo;
        private String name;
        private String tel;
        private int sex;
        private String birthday;
        private String cardNo;
        private String deviceUuid;
        private String remark;
        private String companyUuid;
        private boolean isSelect;

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

        public String getConstIdentTypeUuid() {
            return constIdentTypeUuid;
        }

        public void setConstIdentTypeUuid(String constIdentTypeUuid) {
            this.constIdentTypeUuid = constIdentTypeUuid;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getDeviceUuid() {
            return deviceUuid;
        }

        public void setDeviceUuid(String deviceUuid) {
            this.deviceUuid = deviceUuid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public void setCompanyUuid(String companyUuid) {
            this.companyUuid = companyUuid;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
