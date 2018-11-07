package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/2.
 */

public class CRMInfoBean {

    /**
     * data : {"stdUser":{"uuid":"b82d95b552cb476989d8c145c7e95831","mobilePhone":"18654444555","weixinOpenid":null,"alipayid":null,"identType":null,"identNo":null,"identAddress":null,"identValidDate":null,"name":null,"password":"$2a$10$lmS2U3pk1XQ0bITFCXXjtOxmPAFuvScGoHc/rVnKy9erY6IDzdiG.","sex":0,"birthday":null,"remark":null,"validFlag":1,"activeFlag":true,"createUserUuid":null,"createTime":"2018-11-06 10:02:26","updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":20181106100226542},"groupCompanyDTOS":[{"crmCompany":{"uuid":"faf2404f58794c559d3b2a8d1a4ef63a","code":"35852","businessCode":"","description":"尴尬不","remark":null,"industryCode":10001,"regionCode":null,"address":"VB不急","legalPerson":"","contactPerson":"","contactPhone":"","zipCode":"","businessLicense":null,"init_over_flag":null,"validFlag":"1","createUserUuid":"b82d95b552cb476989d8c145c7e95831","createTime":"2018-11-06 10:02:26","updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":20181106100226561},"stdGroupCompanies":[]}]}
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
         * stdUser : {"uuid":"b82d95b552cb476989d8c145c7e95831","mobilePhone":"18654444555","weixinOpenid":null,"alipayid":null,"identType":null,"identNo":null,"identAddress":null,"identValidDate":null,"name":null,"password":"$2a$10$lmS2U3pk1XQ0bITFCXXjtOxmPAFuvScGoHc/rVnKy9erY6IDzdiG.","sex":0,"birthday":null,"remark":null,"validFlag":1,"activeFlag":true,"createUserUuid":null,"createTime":"2018-11-06 10:02:26","updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":20181106100226542}
         * groupCompanyDTOS : [{"crmCompany":{"uuid":"faf2404f58794c559d3b2a8d1a4ef63a","code":"35852","businessCode":"","description":"尴尬不","remark":null,"industryCode":10001,"regionCode":null,"address":"VB不急","legalPerson":"","contactPerson":"","contactPhone":"","zipCode":"","businessLicense":null,"init_over_flag":null,"validFlag":"1","createUserUuid":"b82d95b552cb476989d8c145c7e95831","createTime":"2018-11-06 10:02:26","updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":20181106100226561},"stdGroupCompanies":[]}]
         */

        private StdUserBean stdUser;
        private List<GroupCompanyDTOSBean> groupCompanyDTOS;

        public StdUserBean getStdUser() {
            return stdUser;
        }

        public void setStdUser(StdUserBean stdUser) {
            this.stdUser = stdUser;
        }

        public List<GroupCompanyDTOSBean> getGroupCompanyDTOS() {
            return groupCompanyDTOS;
        }

        public void setGroupCompanyDTOS(List<GroupCompanyDTOSBean> groupCompanyDTOS) {
            this.groupCompanyDTOS = groupCompanyDTOS;
        }

        public static class StdUserBean {
            /**
             * uuid : b82d95b552cb476989d8c145c7e95831
             * mobilePhone : 18654444555
             * weixinOpenid : null
             * alipayid : null
             * identType : null
             * identNo : null
             * identAddress : null
             * identValidDate : null
             * name : null
             * password : $2a$10$lmS2U3pk1XQ0bITFCXXjtOxmPAFuvScGoHc/rVnKy9erY6IDzdiG.
             * sex : 0
             * birthday : null
             * remark : null
             * validFlag : 1
             * activeFlag : true
             * createUserUuid : null
             * createTime : 2018-11-06 10:02:26
             * updateUserUuid : null
             * updateTime : null
             * version : 0
             * sysRemark : null
             * id : 20181106100226542
             */

            private String uuid;
            private String mobilePhone;
            private Object weixinOpenid;
            private Object alipayid;
            private Object identType;
            private Object identNo;
            private Object identAddress;
            private Object identValidDate;
            private Object name;
            private String password;
            private int sex;
            private Object birthday;
            private Object remark;
            private int validFlag;
            private boolean activeFlag;
            private Object createUserUuid;
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

            public boolean isActiveFlag() {
                return activeFlag;
            }

            public void setActiveFlag(boolean activeFlag) {
                this.activeFlag = activeFlag;
            }

            public Object getCreateUserUuid() {
                return createUserUuid;
            }

            public void setCreateUserUuid(Object createUserUuid) {
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

        public static class GroupCompanyDTOSBean {
            /**
             * crmCompany : {"uuid":"faf2404f58794c559d3b2a8d1a4ef63a","code":"35852","businessCode":"","description":"尴尬不","remark":null,"industryCode":10001,"regionCode":null,"address":"VB不急","legalPerson":"","contactPerson":"","contactPhone":"","zipCode":"","businessLicense":null,"init_over_flag":null,"validFlag":"1","createUserUuid":"b82d95b552cb476989d8c145c7e95831","createTime":"2018-11-06 10:02:26","updateUserUuid":null,"updateTime":null,"version":0,"sysRemark":null,"id":20181106100226561}
             * stdGroupCompanies : []
             */

            private CrmCompanyBean crmCompany;
            private List<?> stdGroupCompanies;

            public CrmCompanyBean getCrmCompany() {
                return crmCompany;
            }

            public void setCrmCompany(CrmCompanyBean crmCompany) {
                this.crmCompany = crmCompany;
            }

            public List<?> getStdGroupCompanies() {
                return stdGroupCompanies;
            }

            public void setStdGroupCompanies(List<?> stdGroupCompanies) {
                this.stdGroupCompanies = stdGroupCompanies;
            }

            public static class CrmCompanyBean {
                /**
                 * uuid : faf2404f58794c559d3b2a8d1a4ef63a
                 * code : 35852
                 * businessCode :
                 * description : 尴尬不
                 * remark : null
                 * industryCode : 10001
                 * regionCode : null
                 * address : VB不急
                 * legalPerson :
                 * contactPerson :
                 * contactPhone :
                 * zipCode :
                 * businessLicense : null
                 * init_over_flag : null
                 * validFlag : 1
                 * createUserUuid : b82d95b552cb476989d8c145c7e95831
                 * createTime : 2018-11-06 10:02:26
                 * updateUserUuid : null
                 * updateTime : null
                 * version : 0
                 * sysRemark : null
                 * id : 20181106100226561
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
    }
}
