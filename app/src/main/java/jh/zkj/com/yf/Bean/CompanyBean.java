package jh.zkj.com.yf.Bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wdefer
 * 2018/11/13
 * use 企业（公司 bean）
 */
public class CompanyBean implements Serializable{


    /**
     * userUuid : 72019f34d9d341c39118f2cb2566fe3e
     * crmCompanys : [{"uuid":"92156253-0723-4b3a-b09e-de6f6d8f192d","code":"00001","mnemonicCode":null,"pinyin":null,"businessCode":"10001","name":"浙江公司","remark":null,"industryCode":10001,"regionCode":null,"address":null,"legalPerson":null,"contactPerson":null,"mobileNum":null,"zipCode":null,"businessLicense":null,"init_over_flag":null,"validDate":null,"productType":null,"regionFullName":null,"password":null,"phone":null,"applyNums":null,"version":13}]
     * stdUser : {"uuid":"72019f34d9d341c39118f2cb2566fe3e","mobilePhone":"18970313532","weixinOpenid":null,"alipayid":null,"identType":null,"identNo":null,"identAddress":null,"identValidDate":null,"regionCode":null,"identImgFront":null,"identImgBack":null,"name":null,"password":"$2a$10$yQBOMPDRAGNA0tYNvmLc3uTotvcAk2RAfSOLsLd0NrAlgEcQEZqEi","sex":0,"birthday":null,"remark":null,"validFlag":1,"createUserUuid":null,"createTime":null,"updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":1}
     */

    private String userUuid;
    private StdUserBean stdUser;
    private ArrayList<CrmCompanysBean> crmCompanys;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public StdUserBean getStdUser() {
        return stdUser;
    }

    public void setStdUser(StdUserBean stdUser) {
        this.stdUser = stdUser;
    }

    public ArrayList<CrmCompanysBean> getCrmCompanys() {
        return crmCompanys;
    }

    public void setCrmCompanys(ArrayList<CrmCompanysBean> crmCompanys) {
        this.crmCompanys = crmCompanys;
    }

    public static class StdUserBean implements Serializable{
        /**
         * uuid : 72019f34d9d341c39118f2cb2566fe3e
         * mobilePhone : 18970313532
         * weixinOpenid : null
         * alipayid : null
         * identType : null
         * identNo : null
         * identAddress : null
         * identValidDate : null
         * regionCode : null
         * identImgFront : null
         * identImgBack : null
         * name : null
         * password : $2a$10$yQBOMPDRAGNA0tYNvmLc3uTotvcAk2RAfSOLsLd0NrAlgEcQEZqEi
         * sex : 0
         * birthday : null
         * remark : null
         * validFlag : 1
         * createUserUuid : null
         * createTime : null
         * updateUserUuid : null
         * updateTime : null
         * version : 0
         * sysRemark : null
         * id : 1
         */

        private Object name;
        private String password;
        private int sex;
        private Object birthday;
        private Object remark;
        private int validFlag;
        private Object createUserUuid;
        private Object createTime;
        private Object updateUserUuid;
        private Object updateTime;
        private int version;
        private Object sysRemark;
        private int id;


        private String uuid;
        private String mobilePhone;
        private Object weixinOpenid;
        private Object alipayid;
        private Object identType;
        private Object identNo;
        private Object identAddress;
        private Object identValidDate;
        private Object regionCode;
        private String identImgFront;
        private String identImgBack;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public Object getCreateUserUuid() {
            return createUserUuid;
        }

        public void setCreateUserUuid(Object createUserUuid) {
            this.createUserUuid = createUserUuid;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public Object getIdentNo() {
            return identNo;
        }

        public void setIdentNo(Object identNo) {
            this.identNo = identNo;
        }

        public Object getIdentAddress() {
            return identAddress;
        }

        public void setIdentAddress(Object identAddress) {
            this.identAddress = identAddress;
        }

        public Object getIdentValidDate() {
            return identValidDate;
        }

        public void setIdentValidDate(Object identValidDate) {
            this.identValidDate = identValidDate;
        }

        public Object getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(Object regionCode) {
            this.regionCode = regionCode;
        }

        public String getIdentImgFront() {
            return identImgFront;
        }

        public void setIdentImgFront(String identImgFront) {
            this.identImgFront = identImgFront;
        }

        public String getIdentImgBack() {
            return identImgBack;
        }

        public void setIdentImgBack(String identImgBack) {
            this.identImgBack = identImgBack;
        }

    }

    public static class CrmCompanysBean implements Serializable{
        /**
         * uuid : 92156253-0723-4b3a-b09e-de6f6d8f192d
         * code : 00001
         * mnemonicCode : null
         * pinyin : null
         * businessCode : 10001
         * name : 浙江公司
         * remark : null
         * industryCode : 10001
         * regionCode : null
         * address : null
         * legalPerson : null
         * contactPerson : null
         * mobileNum : null
         * zipCode : null
         * businessLicense : null
         * init_over_flag : null
         * validDate : null
         * productType : null
         * regionFullName : null
         * password : null
         * phone : null
         * applyNums : null
         * version : 13
         */

        private String uuid;
        private String code;
        private Object mnemonicCode;
        private Object pinyin;
        private String businessCode;
        private String name;
        private Object remark;
        private int industryCode;
        private Object regionCode;
        private String address;
        private String legalPerson;
        private String contactPerson;
        private String mobileNum;
        private String zipCode;
        private String businessLicense;
        private String init_over_flag;
        private String validDate;
        private String productType;
        private String regionFullName;
        private Object password;
        private Object phone;
        private int applyNums;
        private int version;

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

        public Object getMnemonicCode() {
            return mnemonicCode;
        }

        public void setMnemonicCode(Object mnemonicCode) {
            this.mnemonicCode = mnemonicCode;
        }

        public Object getPinyin() {
            return pinyin;
        }

        public void setPinyin(Object pinyin) {
            this.pinyin = pinyin;
        }

        public String getBusinessCode() {
            return businessCode;
        }

        public void setBusinessCode(String businessCode) {
            this.businessCode = businessCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getMobileNum() {
            return mobileNum;
        }

        public void setMobileNum(String mobileNum) {
            this.mobileNum = mobileNum;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getInit_over_flag() {
            return init_over_flag;
        }

        public void setInit_over_flag(String init_over_flag) {
            this.init_over_flag = init_over_flag;
        }

        public String getValidDate() {
            return validDate;
        }

        public void setValidDate(String validDate) {
            this.validDate = validDate;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getRegionFullName() {
            return regionFullName;
        }

        public void setRegionFullName(String regionFullName) {
            this.regionFullName = regionFullName;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public int getApplyNums() {
            return applyNums;
        }

        public void setApplyNums(int applyNums) {
            this.applyNums = applyNums;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
