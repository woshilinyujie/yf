package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/29.
 */

public class VersionBean {

    /**
     * data : {"uuid":"zkj-product-type-jherp-3c-app-android","productsClientUuid":"zkj-product-type-jherp-3c-for-app","type":"APP","typeDetail":"ANDROID","upgradeDate":"2018-12-08","version":"1.0.0","interfaceVersion":"10000","remark":"系统上线","flag":1,"createUserUuid":null,"createTime":"2018-11-28 13:20:21.0","updateUserUuid":null,"updateTime":"2018-11-28 13:48:37.0","sysRemark":null,"id":1}
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
         * uuid : zkj-product-type-jherp-3c-app-android
         * productsClientUuid : zkj-product-type-jherp-3c-for-app
         * type : APP
         * typeDetail : ANDROID
         * upgradeDate : 2018-12-08
         * version : 1.0.0
         * interfaceVersion : 10000
         * remark : 系统上线
         * flag : 1
         * createUserUuid : null
         * createTime : 2018-11-28 13:20:21.0
         * updateUserUuid : null
         * updateTime : 2018-11-28 13:48:37.0
         * sysRemark : null
         * id : 1
         */

        private String uuid;
        private String productsClientUuid;
        private String type;
        private String typeDetail;
        private String upgradeDate;
        private String version;
        private String interfaceVersion;
        private String remark;
        private int flag;
        private Object createUserUuid;
        private String createTime;
        private Object updateUserUuid;
        private String updateTime;
        private Object sysRemark;
        private int id;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getProductsClientUuid() {
            return productsClientUuid;
        }

        public void setProductsClientUuid(String productsClientUuid) {
            this.productsClientUuid = productsClientUuid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeDetail() {
            return typeDetail;
        }

        public void setTypeDetail(String typeDetail) {
            this.typeDetail = typeDetail;
        }

        public String getUpgradeDate() {
            return upgradeDate;
        }

        public void setUpgradeDate(String upgradeDate) {
            this.upgradeDate = upgradeDate;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getInterfaceVersion() {
            return interfaceVersion;
        }

        public void setInterfaceVersion(String interfaceVersion) {
            this.interfaceVersion = interfaceVersion;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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
    }
}
