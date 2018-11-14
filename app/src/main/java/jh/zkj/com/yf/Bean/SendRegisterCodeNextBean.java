package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/13.
 */

public class SendRegisterCodeNextBean {

    /**
     * data : {"flag":1,"stdUserApplys":[{"uuid":"204568f173f24cba9926cb365f54afe5","mobilePhone":"18606889455","username":null,"ascriptionCompanyUuid":"92156253-0723-4b3a-b09e-de6f6d8f192d","auditFlag":"0","code":null,"companyName":"浙江公司","createTime":"2018-11-13 19:46:42","name":null,"identNo":null,"sex":null,"identAddress":null,"identValidDate":null,"regionRode":null,"identImgFront":null,"identImgBack":null,"password":null,"confirmPassword":null,"newUser":false,"regionFullName":null}]}
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
         * flag : 1
         * stdUserApplys : [{"uuid":"204568f173f24cba9926cb365f54afe5","mobilePhone":"18606889455","username":null,"ascriptionCompanyUuid":"92156253-0723-4b3a-b09e-de6f6d8f192d","auditFlag":"0","code":null,"companyName":"浙江公司","createTime":"2018-11-13 19:46:42","name":null,"identNo":null,"sex":null,"identAddress":null,"identValidDate":null,"regionRode":null,"identImgFront":null,"identImgBack":null,"password":null,"confirmPassword":null,"newUser":false,"regionFullName":null}]
         */

        private int flag;
        private List<StdUserApplysBean> stdUserApplys;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public List<StdUserApplysBean> getStdUserApplys() {
            return stdUserApplys;
        }

        public void setStdUserApplys(List<StdUserApplysBean> stdUserApplys) {
            this.stdUserApplys = stdUserApplys;
        }

        public static class StdUserApplysBean {
            /**
             * uuid : 204568f173f24cba9926cb365f54afe5
             * mobilePhone : 18606889455
             * username : null
             * ascriptionCompanyUuid : 92156253-0723-4b3a-b09e-de6f6d8f192d
             * auditFlag : 0
             * code : null
             * companyName : 浙江公司
             * createTime : 2018-11-13 19:46:42
             * name : null
             * identNo : null
             * sex : null
             * identAddress : null
             * identValidDate : null
             * regionRode : null
             * identImgFront : null
             * identImgBack : null
             * password : null
             * confirmPassword : null
             * newUser : false
             * regionFullName : null
             */

            private String uuid;
            private String mobilePhone;
            private Object username;
            private String ascriptionCompanyUuid;
            private String auditFlag;
            private Object code;
            private String companyName;
            private String createTime;
            private Object name;
            private Object identNo;
            private Object sex;
            private Object identAddress;
            private Object identValidDate;
            private Object regionRode;
            private Object identImgFront;
            private Object identImgBack;
            private Object password;
            private Object confirmPassword;
            private boolean newUser;
            private Object regionFullName;

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

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public String getAscriptionCompanyUuid() {
                return ascriptionCompanyUuid;
            }

            public void setAscriptionCompanyUuid(String ascriptionCompanyUuid) {
                this.ascriptionCompanyUuid = ascriptionCompanyUuid;
            }

            public String getAuditFlag() {
                return auditFlag;
            }

            public void setAuditFlag(String auditFlag) {
                this.auditFlag = auditFlag;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getIdentNo() {
                return identNo;
            }

            public void setIdentNo(Object identNo) {
                this.identNo = identNo;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
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

            public Object getRegionRode() {
                return regionRode;
            }

            public void setRegionRode(Object regionRode) {
                this.regionRode = regionRode;
            }

            public Object getIdentImgFront() {
                return identImgFront;
            }

            public void setIdentImgFront(Object identImgFront) {
                this.identImgFront = identImgFront;
            }

            public Object getIdentImgBack() {
                return identImgBack;
            }

            public void setIdentImgBack(Object identImgBack) {
                this.identImgBack = identImgBack;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public Object getConfirmPassword() {
                return confirmPassword;
            }

            public void setConfirmPassword(Object confirmPassword) {
                this.confirmPassword = confirmPassword;
            }

            public boolean isNewUser() {
                return newUser;
            }

            public void setNewUser(boolean newUser) {
                this.newUser = newUser;
            }

            public Object getRegionFullName() {
                return regionFullName;
            }

            public void setRegionFullName(Object regionFullName) {
                this.regionFullName = regionFullName;
            }
        }
    }
}
