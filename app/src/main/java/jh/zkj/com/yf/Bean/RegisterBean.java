package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by linyujie on 18/11/5.
 */

public class RegisterBean implements Serializable{

    /**
     * data : {"uuid":"bb2ed9655c90410cb2f4f202e162f2fa","code":"04422","businessCode":"","description":"刚回家就","remark":null,"industryCode":10001,"regionCode":null,"address":"超级经纪人","legalPerson":"","contactPerson":"","contactPhone":"","zipCode":"","businessLicense":null,"init_over_flag":null,"validFlag":"1","createUserUuid":"c28b9008037d4d9680975a0e823c48e9","createTime":"2018-11-05 15:24:51","updateUserUuid":null,"updateTime":"2018-11-05 15:24:51","version":0,"sysRemark":null,"id":20181105152451048}
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

    public static class DataBean implements Serializable{
        /**
         * uuid : bb2ed9655c90410cb2f4f202e162f2fa
         * code : 04422
         * businessCode :
         * description : 刚回家就
         * remark : null
         * industryCode : 10001
         * regionCode : null
         * address : 超级经纪人
         * legalPerson :
         * contactPerson :
         * contactPhone :
         * zipCode :
         * businessLicense : null
         * init_over_flag : null
         * validFlag : 1
         * createUserUuid : c28b9008037d4d9680975a0e823c48e9
         * createTime : 2018-11-05 15:24:51
         * updateUserUuid : null
         * updateTime : 2018-11-05 15:24:51
         * version : 0
         * sysRemark : null
         * id : 20181105152451048
         */

        private String uuid;
        private String code;
        private String businessCode;
        private String description;
        private Object remark;
        private int industryCode;
        private Object regionCode;
        private String address;
        private String legalPerson;
        private String contactPerson;
        private String contactPhone;
        private String zipCode;
        private Object businessLicense;
        private Object init_over_flag;
        private String validFlag;
        private String createUserUuid;
        private String createTime;
        private Object updateUserUuid;
        private String updateTime;
        private int version;
        private Object sysRemark;
        private long id;

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

        public String getBusinessCode() {
            return businessCode;
        }

        public void setBusinessCode(String businessCode) {
            this.businessCode = businessCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getIndustryCode() {
            return industryCode;
        }

        public void setIndustryCode(int industryCode) {
            this.industryCode = industryCode;
        }

        public Object getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(Object regionCode) {
            this.regionCode = regionCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public Object getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(Object businessLicense) {
            this.businessLicense = businessLicense;
        }

        public Object getInit_over_flag() {
            return init_over_flag;
        }

        public void setInit_over_flag(Object init_over_flag) {
            this.init_over_flag = init_over_flag;
        }

        public String getValidFlag() {
            return validFlag;
        }

        public void setValidFlag(String validFlag) {
            this.validFlag = validFlag;
        }

        public String getCreateUserUuid() {
            return createUserUuid;
        }

        public void setCreateUserUuid(String createUserUuid) {
            this.createUserUuid = createUserUuid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateUserUuid() {
            return updateUserUuid;
        }

        public void setUpdateUserUuid(Object updateUserUuid) {
            this.updateUserUuid = updateUserUuid;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getSysRemark() {
            return sysRemark;
        }

        public void setSysRemark(Object sysRemark) {
            this.sysRemark = sysRemark;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
