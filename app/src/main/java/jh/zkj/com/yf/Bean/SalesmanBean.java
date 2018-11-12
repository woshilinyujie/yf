package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/10/23
 * use
 */
public class SalesmanBean implements Serializable{

    /**
     * records : [{"topCompanyCode":"00001","createTime":"2018-11-09 10:10:02","createUserUuid":"1","updateTime":"2018-11-09 15:10:09","updateUserUuid":"1","sysRemark":null,"validFlag":1,"version":2,"uuid":"7bd8715ca6bd46eebeaff54f9e4a8050","ascriptionCompanyUuid":null,"password":"$2a$10$DEjknO3q8ASe./oTcNcFGedWoErVZ/sH6zB7gIvGjgnk8.uyGQi7i","name":"42rfdfffgfg","mobileNum":null,"idCard":"130444199809084559","searchDays":32,"soClerkFlag":1,"poClerkFlag":0,"serviceEngFlag":1,"updateOtherBillFlag":1,"viewSupplierFlag":0,"viewCostFlag":1,"viewWsPriceFlag":1,"viewSoPriceFlag":0,"basicUserUuid":null,"enableFlag":0,"remark":null,"id":20181109101002036,"companyUuid":"03f1d065adbd45809f05fe2914bf20dc","companyName":"舟山营销中心","roleName":"超级管理员","roleUuid":null}]
     * total : 11
     * size : 10
     * current : 1
     * pages : 2
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
         * topCompanyCode : 00001
         * createTime : 2018-11-09 10:10:02
         * createUserUuid : 1
         * updateTime : 2018-11-09 15:10:09
         * updateUserUuid : 1
         * sysRemark : null
         * validFlag : 1
         * version : 2
         * uuid : 7bd8715ca6bd46eebeaff54f9e4a8050
         * ascriptionCompanyUuid : null
         * password : $2a$10$DEjknO3q8ASe./oTcNcFGedWoErVZ/sH6zB7gIvGjgnk8.uyGQi7i
         * name : 42rfdfffgfg
         * mobileNum : null
         * idCard : 130444199809084559
         * searchDays : 32
         * soClerkFlag : 1
         * poClerkFlag : 0
         * serviceEngFlag : 1
         * updateOtherBillFlag : 1
         * viewSupplierFlag : 0
         * viewCostFlag : 1
         * viewWsPriceFlag : 1
         * viewSoPriceFlag : 0
         * basicUserUuid : null
         * enableFlag : 0
         * remark : null
         * id : 20181109101002036
         * companyUuid : 03f1d065adbd45809f05fe2914bf20dc
         * companyName : 舟山营销中心
         * roleName : 超级管理员
         * roleUuid : null
         */

        private String topCompanyCode;
        private String createTime;
        private String createUserUuid;
        private String updateTime;
        private String updateUserUuid;
        private Object sysRemark;
        private int validFlag;
        private int version;
        private String uuid;
        private Object ascriptionCompanyUuid;
        private String password;
        private String name;
        private Object mobileNum;
        private String idCard;
        private int searchDays;
        private int soClerkFlag;
        private int poClerkFlag;
        private int serviceEngFlag;
        private int updateOtherBillFlag;
        private int viewSupplierFlag;
        private int viewCostFlag;
        private int viewWsPriceFlag;
        private int viewSoPriceFlag;
        private Object basicUserUuid;
        private int enableFlag;
        private Object remark;
        private long id;
        private String companyUuid;
        private String companyName;
        private String roleName;
        private Object roleUuid;
        private boolean isSelect;

        public String getTopCompanyCode() {
            return topCompanyCode;
        }

        public void setTopCompanyCode(String topCompanyCode) {
            this.topCompanyCode = topCompanyCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUserUuid() {
            return createUserUuid;
        }

        public void setCreateUserUuid(String createUserUuid) {
            this.createUserUuid = createUserUuid;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUserUuid() {
            return updateUserUuid;
        }

        public void setUpdateUserUuid(String updateUserUuid) {
            this.updateUserUuid = updateUserUuid;
        }

        public Object getSysRemark() {
            return sysRemark;
        }

        public void setSysRemark(Object sysRemark) {
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

        public Object getAscriptionCompanyUuid() {
            return ascriptionCompanyUuid;
        }

        public void setAscriptionCompanyUuid(Object ascriptionCompanyUuid) {
            this.ascriptionCompanyUuid = ascriptionCompanyUuid;
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

        public Object getMobileNum() {
            return mobileNum;
        }

        public void setMobileNum(Object mobileNum) {
            this.mobileNum = mobileNum;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getSearchDays() {
            return searchDays;
        }

        public void setSearchDays(int searchDays) {
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

        public Object getBasicUserUuid() {
            return basicUserUuid;
        }

        public void setBasicUserUuid(Object basicUserUuid) {
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

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public Object getRoleUuid() {
            return roleUuid;
        }

        public void setRoleUuid(Object roleUuid) {
            this.roleUuid = roleUuid;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
