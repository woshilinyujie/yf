package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/23.
 */

public class MyBean {

    /**
     * data : {"sysUser":{"topCompanyCode":"90372","createTime":[2018,11,17,13,22,22],"createUserUuid":null,"updateTime":[2018,11,20,19,9,38],"updateUserUuid":"317367f28f4842fc8039a7bd917d0854","sysRemark":"系统初始化","validFlag":1,"version":19,"uuid":"317367f28f4842fc8039a7bd917d0854","ascriptionCompanyUuid":"90372","username":"jh-erp-3c_android_19900008888","password":"$2a$10$4w9EFlkF2MS2UKGE3.VYg.X5SKq5gQslPjgiLwsd3hpyIB6XfEiQ.","name":"超级管理员","mobileNum":"19900008888","idCard":"339005201811196888","headImg":null,"searchDays":null,"soClerkFlag":0,"poClerkFlag":1,"serviceEngFlag":0,"updateOtherBillFlag":1,"viewSupplierFlag":1,"viewCostFlag":1,"viewWsPriceFlag":1,"viewSoPriceFlag":1,"basicUserUuid":"589a17bf01b14b3889948453a6d42efc","enableFlag":1,"remark":"22","id":20181117132221630,"companyUuid":"738d83b7443544b69dfcd89b4a5d61b2"},"permissions":["erp_app_soSelect_print","erp_app_soSelect_audit","erp_app_operationAnalysis_crud_delete","erp_app_operationAnalysis_crud_view","erp_app_stockManage_print","erp_app_stockManage_crud_create","erp_app_sale_crud_create","erp_app_statisticalAnalysis_crud_delete","erp_app_stockSelect_crud_view","erp_app_soApp_print","erp_app_operationAnalysis_crud_create","erp_app_statisticalAnalysis_print","erp_app_stockSelect_print","erp_app_soApp_audit","erp_app_stockManage_crud_update","erp_app_stockSelect_crud_delete","erp_app_sale_crud_view","erp_app_soApp_crud_update","erp_app_stockSelect_unaudit","erp_app_stockManage_crud_view","erp_app_soSelect_unaudit","erp_app_sale_crud_delete","erp_app_operationAnalysis_unaudit","erp_app_stockManage_crud_delete","erp_app_soApp_crud_create","erp_app_sale_audit","erp_app_soSelect_crud_delete","erp_app_sale_unaudit","erp_app_stockManage_unaudit","erp_app_soSelect_crud_view","erp_app_operationAnalysis_audit","erp_app_soSelect_crud_update","erp_app_stockSelect_audit","erp_app_stockSelect_crud_create","erp_app_operationAnalysis_crud_update","erp_app_statisticalAnalysis_unaudit","erp_app_stockSelect_crud_update","erp_app_statisticalAnalysis_audit","erp_app_soApp_unaudit","erp_app_statisticalAnalysis_crud_update","erp_app_sale_crud_update","erp_app_sale_print","erp_app_statisticalAnalysis_crud_create","erp_app_soApp_crud_view","erp_app_statisticalAnalysis_crud_view","erp_app_soSelect_crud_create","erp_app_operationAnalysis_print","erp_app_stockManage_audit","erp_app_soApp_crud_delete"],"roleUuid":["2c85ff41077d4a18b656bc8d921db78f"],"roleName":["超级管理员"],"topCompanyUuid":"738d83b7443544b69dfcd89b4a5d61b2","topCompanyCode":"90372","companyUuid":"738d83b7443544b69dfcd89b4a5d61b2","companyCode":"90372","dbName":"dev_02","from":"APP","topCompnayName":"总公司","companyName":"总公司"}
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
         * sysUser : {"topCompanyCode":"90372","createTime":[2018,11,17,13,22,22],"createUserUuid":null,"updateTime":[2018,11,20,19,9,38],"updateUserUuid":"317367f28f4842fc8039a7bd917d0854","sysRemark":"系统初始化","validFlag":1,"version":19,"uuid":"317367f28f4842fc8039a7bd917d0854","ascriptionCompanyUuid":"90372","username":"jh-erp-3c_android_19900008888","password":"$2a$10$4w9EFlkF2MS2UKGE3.VYg.X5SKq5gQslPjgiLwsd3hpyIB6XfEiQ.","name":"超级管理员","mobileNum":"19900008888","idCard":"339005201811196888","headImg":null,"searchDays":null,"soClerkFlag":0,"poClerkFlag":1,"serviceEngFlag":0,"updateOtherBillFlag":1,"viewSupplierFlag":1,"viewCostFlag":1,"viewWsPriceFlag":1,"viewSoPriceFlag":1,"basicUserUuid":"589a17bf01b14b3889948453a6d42efc","enableFlag":1,"remark":"22","id":20181117132221630,"companyUuid":"738d83b7443544b69dfcd89b4a5d61b2"}
         * permissions : ["erp_app_soSelect_print","erp_app_soSelect_audit","erp_app_operationAnalysis_crud_delete","erp_app_operationAnalysis_crud_view","erp_app_stockManage_print","erp_app_stockManage_crud_create","erp_app_sale_crud_create","erp_app_statisticalAnalysis_crud_delete","erp_app_stockSelect_crud_view","erp_app_soApp_print","erp_app_operationAnalysis_crud_create","erp_app_statisticalAnalysis_print","erp_app_stockSelect_print","erp_app_soApp_audit","erp_app_stockManage_crud_update","erp_app_stockSelect_crud_delete","erp_app_sale_crud_view","erp_app_soApp_crud_update","erp_app_stockSelect_unaudit","erp_app_stockManage_crud_view","erp_app_soSelect_unaudit","erp_app_sale_crud_delete","erp_app_operationAnalysis_unaudit","erp_app_stockManage_crud_delete","erp_app_soApp_crud_create","erp_app_sale_audit","erp_app_soSelect_crud_delete","erp_app_sale_unaudit","erp_app_stockManage_unaudit","erp_app_soSelect_crud_view","erp_app_operationAnalysis_audit","erp_app_soSelect_crud_update","erp_app_stockSelect_audit","erp_app_stockSelect_crud_create","erp_app_operationAnalysis_crud_update","erp_app_statisticalAnalysis_unaudit","erp_app_stockSelect_crud_update","erp_app_statisticalAnalysis_audit","erp_app_soApp_unaudit","erp_app_statisticalAnalysis_crud_update","erp_app_sale_crud_update","erp_app_sale_print","erp_app_statisticalAnalysis_crud_create","erp_app_soApp_crud_view","erp_app_statisticalAnalysis_crud_view","erp_app_soSelect_crud_create","erp_app_operationAnalysis_print","erp_app_stockManage_audit","erp_app_soApp_crud_delete"]
         * roleUuid : ["2c85ff41077d4a18b656bc8d921db78f"]
         * roleName : ["超级管理员"]
         * topCompanyUuid : 738d83b7443544b69dfcd89b4a5d61b2
         * topCompanyCode : 90372
         * companyUuid : 738d83b7443544b69dfcd89b4a5d61b2
         * companyCode : 90372
         * dbName : dev_02
         * from : APP
         * topCompnayName : 总公司
         * companyName : 总公司
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
             * topCompanyCode : 90372
             * createTime : [2018,11,17,13,22,22]
             * createUserUuid : null
             * updateTime : [2018,11,20,19,9,38]
             * updateUserUuid : 317367f28f4842fc8039a7bd917d0854
             * sysRemark : 系统初始化
             * validFlag : 1
             * version : 19
             * uuid : 317367f28f4842fc8039a7bd917d0854
             * ascriptionCompanyUuid : 90372
             * username : jh-erp-3c_android_19900008888
             * password : $2a$10$4w9EFlkF2MS2UKGE3.VYg.X5SKq5gQslPjgiLwsd3hpyIB6XfEiQ.
             * name : 超级管理员
             * mobileNum : 19900008888
             * idCard : 339005201811196888
             * headImg : null
             * searchDays : null
             * soClerkFlag : 0
             * poClerkFlag : 1
             * serviceEngFlag : 0
             * updateOtherBillFlag : 1
             * viewSupplierFlag : 1
             * viewCostFlag : 1
             * viewWsPriceFlag : 1
             * viewSoPriceFlag : 1
             * basicUserUuid : 589a17bf01b14b3889948453a6d42efc
             * enableFlag : 1
             * remark : 22
             * id : 20181117132221630
             * companyUuid : 738d83b7443544b69dfcd89b4a5d61b2
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
            private String remark;
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
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
