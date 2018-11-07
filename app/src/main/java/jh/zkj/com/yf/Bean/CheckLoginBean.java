package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/5.
 */

public class CheckLoginBean {

    /**
     * data : {"uuid":"a9d5f6f0191d47a8a881a8ef8c343495","code":"69515","businessCode":"","description":"古古怪怪","remark":null,"industryCode":10001,"regionCode":null,"address":"vv北郊农场下","legalPerson":"","contactPerson":"","contactPhone":"","zipCode":"","businessLicense":null,"init_over_flag":null,"validFlag":"1","createUserUuid":"f8b1ddd2cc4442d99d792f08d354f764","createTime":"2018-11-05 20:02:24","updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":20181105200224603}
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
         * uuid : a9d5f6f0191d47a8a881a8ef8c343495
         * code : 69515
         * businessCode :
         * description : 古古怪怪
         * remark : null
         * industryCode : 10001
         * regionCode : null
         * address : vv北郊农场下
         * legalPerson :
         * contactPerson :
         * contactPhone :
         * zipCode :
         * businessLicense : null
         * init_over_flag : null
         * validFlag : 1
         * createUserUuid : f8b1ddd2cc4442d99d792f08d354f764
         * createTime : 2018-11-05 20:02:24
         * updateUserUuid : null
         * updateTime : null
         * version : 0
         * sysRemark : null
         * id : 20181105200224603
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
        private Object updateTime;
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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
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
