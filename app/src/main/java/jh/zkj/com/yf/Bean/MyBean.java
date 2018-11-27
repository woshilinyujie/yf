package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/23.
 */

public class MyBean {

    /**
     * data : {"sysUser":{"topCompanyCode":"510674","createTime":[2018,11,24,19,13,2],"createUserUuid":null,"updateTime":[2018,11,26,15,51,24],"updateUserUuid":"6eaceaf37899448f938d5a8e7a251d12","sysRemark":"系统初始化","validFlag":1,"version":12,"uuid":"6eaceaf37899448f938d5a8e7a251d12","ascriptionCompanyUuid":"8abdd01264a141f4bd7ec72456fd360d","username":"jh-erp-3c_code_android_18900000000","password":"$2a$10$ej7Mkr.4H5204AdtFl.hA.j8bZy4U1ZFGrzM5hekGfWK5QkP4/Cki","name":"刘小龙","mobileNum":"18900000000","idCard":"","headImg":null,"searchDays":null,"soClerkFlag":1,"poClerkFlag":1,"serviceEngFlag":1,"updateOtherBillFlag":1,"viewSupplierFlag":1,"viewCostFlag":1,"viewWsPriceFlag":1,"viewSoPriceFlag":1,"basicUserUuid":"defca0f97e70400f9d7f3ad1902f4308","enableFlag":1,"remark":null,"id":20181124191302256,"companyUuid":"8abdd01264a141f4bd7ec72456fd360d"},"permissions":["erp_app_operationAnalysis","erp_app_stockSelect","erp_app_stockManage","erp_app_statisticalAnalysis"],"roleUuid":["1234702388f44458878abbe250f1e12e"],"roleName":["超级管理员"],"topCompanyUuid":"8abdd01264a141f4bd7ec72456fd360d","topCompanyCode":"510674","companyUuid":"8abdd01264a141f4bd7ec72456fd360d","companyCode":"510674","dbName":"dev_01","from":"APP","topCompnayName":"我我有限公司","companyName":"我我有限公司"}
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
         * sysUser : {"topCompanyCode":"510674","createTime":[2018,11,24,19,13,2],"createUserUuid":null,"updateTime":[2018,11,26,15,51,24],"updateUserUuid":"6eaceaf37899448f938d5a8e7a251d12","sysRemark":"系统初始化","validFlag":1,"version":12,"uuid":"6eaceaf37899448f938d5a8e7a251d12","ascriptionCompanyUuid":"8abdd01264a141f4bd7ec72456fd360d","username":"jh-erp-3c_code_android_18900000000","password":"$2a$10$ej7Mkr.4H5204AdtFl.hA.j8bZy4U1ZFGrzM5hekGfWK5QkP4/Cki","name":"刘小龙","mobileNum":"18900000000","idCard":"","headImg":null,"searchDays":null,"soClerkFlag":1,"poClerkFlag":1,"serviceEngFlag":1,"updateOtherBillFlag":1,"viewSupplierFlag":1,"viewCostFlag":1,"viewWsPriceFlag":1,"viewSoPriceFlag":1,"basicUserUuid":"defca0f97e70400f9d7f3ad1902f4308","enableFlag":1,"remark":null,"id":20181124191302256,"companyUuid":"8abdd01264a141f4bd7ec72456fd360d"}
         * permissions : ["erp_app_operationAnalysis","erp_app_stockSelect","erp_app_stockManage","erp_app_statisticalAnalysis"]
         * roleUuid : ["1234702388f44458878abbe250f1e12e"]
         * roleName : ["超级管理员"]
         * topCompanyUuid : 8abdd01264a141f4bd7ec72456fd360d
         * topCompanyCode : 510674
         * companyUuid : 8abdd01264a141f4bd7ec72456fd360d
         * companyCode : 510674
         * dbName : dev_01
         * from : APP
         * topCompnayName : 我我有限公司
         * companyName : 我我有限公司
         */

        private SysUserBean sysUser;
        private String topCompanyUuid;
        private String topCompanyCode;
        private String companyUuid;
        private String companyCode;
        private String dbName;
        private String from;
        private String topCompnayName;
        private String companyName;
        private List<String> permissions;
        private List<String> roleUuid;
        private List<String> roleName;

        public SysUserBean getSysUser() {
            return sysUser;
        }

        public void setSysUser(SysUserBean sysUser) {
            this.sysUser = sysUser;
        }

        public String getTopCompanyUuid() {
            return topCompanyUuid;
        }

        public void setTopCompanyUuid(String topCompanyUuid) {
            this.topCompanyUuid = topCompanyUuid;
        }

        public String getTopCompanyCode() {
            return topCompanyCode;
        }

        public void setTopCompanyCode(String topCompanyCode) {
            this.topCompanyCode = topCompanyCode;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public void setCompanyUuid(String companyUuid) {
            this.companyUuid = companyUuid;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTopCompnayName() {
            return topCompnayName;
        }

        public void setTopCompnayName(String topCompnayName) {
            this.topCompnayName = topCompnayName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        public List<String> getRoleUuid() {
            return roleUuid;
        }

        public void setRoleUuid(List<String> roleUuid) {
            this.roleUuid = roleUuid;
        }

        public List<String> getRoleName() {
            return roleName;
        }

        public void setRoleName(List<String> roleName) {
            this.roleName = roleName;
        }

        public static class SysUserBean {
            /**
             * topCompanyCode : 510674
             * createTime : [2018,11,24,19,13,2]
             * createUserUuid : null
             * updateTime : [2018,11,26,15,51,24]
             * updateUserUuid : 6eaceaf37899448f938d5a8e7a251d12
             * sysRemark : 系统初始化
             * validFlag : 1
             * version : 12
             * uuid : 6eaceaf37899448f938d5a8e7a251d12
             * ascriptionCompanyUuid : 8abdd01264a141f4bd7ec72456fd360d
             * username : jh-erp-3c_code_android_18900000000
             * password : $2a$10$ej7Mkr.4H5204AdtFl.hA.j8bZy4U1ZFGrzM5hekGfWK5QkP4/Cki
             * name : 刘小龙
             * mobileNum : 18900000000
             * idCard :
             * headImg : null
             * searchDays : null
             * soClerkFlag : 1
             * poClerkFlag : 1
             * serviceEngFlag : 1
             * updateOtherBillFlag : 1
             * viewSupplierFlag : 1
             * viewCostFlag : 1
             * viewWsPriceFlag : 1
             * viewSoPriceFlag : 1
             * basicUserUuid : defca0f97e70400f9d7f3ad1902f4308
             * enableFlag : 1
             * remark : null
             * id : 20181124191302256
             * companyUuid : 8abdd01264a141f4bd7ec72456fd360d
             */

            private String topCompanyCode;
            private Object createUserUuid;
            private String updateUserUuid;
            private String sysRemark;
            private int validFlag;
            private int version;
            private String uuid;
            private String ascriptionCompanyUuid;
            private String username;
            private String password;
            private String name;
            private String mobileNum;
            private String idCard;
            private Object headImg;
            private Object searchDays;
            private int soClerkFlag;
            private int poClerkFlag;
            private int serviceEngFlag;
            private int updateOtherBillFlag;
            private int viewSupplierFlag;
            private int viewCostFlag;
            private int viewWsPriceFlag;
            private int viewSoPriceFlag;
            private String basicUserUuid;
            private int enableFlag;
            private Object remark;
            private long id;
            private String companyUuid;
            private List<Integer> createTime;
            private List<Integer> updateTime;

            public String getTopCompanyCode() {
                return topCompanyCode;
            }

            public void setTopCompanyCode(String topCompanyCode) {
                this.topCompanyCode = topCompanyCode;
            }

            public Object getCreateUserUuid() {
                return createUserUuid;
            }

            public void setCreateUserUuid(Object createUserUuid) {
                this.createUserUuid = createUserUuid;
            }

            public String getUpdateUserUuid() {
                return updateUserUuid;
            }

            public void setUpdateUserUuid(String updateUserUuid) {
                this.updateUserUuid = updateUserUuid;
            }

            public String getSysRemark() {
                return sysRemark;
            }

            public void setSysRemark(String sysRemark) {
                this.sysRemark = sysRemark;
            }

            public int getValidFlag() {
                return validFlag;
            }

            public void setValidFlag(int validFlag) {
                this.validFlag = validFlag;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getAscriptionCompanyUuid() {
                return ascriptionCompanyUuid;
            }

            public void setAscriptionCompanyUuid(String ascriptionCompanyUuid) {
                this.ascriptionCompanyUuid = ascriptionCompanyUuid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobileNum() {
                return mobileNum;
            }

            public void setMobileNum(String mobileNum) {
                this.mobileNum = mobileNum;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public Object getHeadImg() {
                return headImg;
            }

            public void setHeadImg(Object headImg) {
                this.headImg = headImg;
            }

            public Object getSearchDays() {
                return searchDays;
            }

            public void setSearchDays(Object searchDays) {
                this.searchDays = searchDays;
            }

            public int getSoClerkFlag() {
                return soClerkFlag;
            }

            public void setSoClerkFlag(int soClerkFlag) {
                this.soClerkFlag = soClerkFlag;
            }

            public int getPoClerkFlag() {
                return poClerkFlag;
            }

            public void setPoClerkFlag(int poClerkFlag) {
                this.poClerkFlag = poClerkFlag;
            }

            public int getServiceEngFlag() {
                return serviceEngFlag;
            }

            public void setServiceEngFlag(int serviceEngFlag) {
                this.serviceEngFlag = serviceEngFlag;
            }

            public int getUpdateOtherBillFlag() {
                return updateOtherBillFlag;
            }

            public void setUpdateOtherBillFlag(int updateOtherBillFlag) {
                this.updateOtherBillFlag = updateOtherBillFlag;
            }

            public int getViewSupplierFlag() {
                return viewSupplierFlag;
            }

            public void setViewSupplierFlag(int viewSupplierFlag) {
                this.viewSupplierFlag = viewSupplierFlag;
            }

            public int getViewCostFlag() {
                return viewCostFlag;
            }

            public void setViewCostFlag(int viewCostFlag) {
                this.viewCostFlag = viewCostFlag;
            }

            public int getViewWsPriceFlag() {
                return viewWsPriceFlag;
            }

            public void setViewWsPriceFlag(int viewWsPriceFlag) {
                this.viewWsPriceFlag = viewWsPriceFlag;
            }

            public int getViewSoPriceFlag() {
                return viewSoPriceFlag;
            }

            public void setViewSoPriceFlag(int viewSoPriceFlag) {
                this.viewSoPriceFlag = viewSoPriceFlag;
            }

            public String getBasicUserUuid() {
                return basicUserUuid;
            }

            public void setBasicUserUuid(String basicUserUuid) {
                this.basicUserUuid = basicUserUuid;
            }

            public int getEnableFlag() {
                return enableFlag;
            }

            public void setEnableFlag(int enableFlag) {
                this.enableFlag = enableFlag;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getCompanyUuid() {
                return companyUuid;
            }

            public void setCompanyUuid(String companyUuid) {
                this.companyUuid = companyUuid;
            }

            public List<Integer> getCreateTime() {
                return createTime;
            }

            public void setCreateTime(List<Integer> createTime) {
                this.createTime = createTime;
            }

            public List<Integer> getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(List<Integer> updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
