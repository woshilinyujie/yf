package jh.zkj.com.yf.Bean;

/**
 * Created by wdefer
 * 2018/10/23
 * use
 */
public class SalesmanBean {


    /**
     * topCompanyCode : 00001
     * createTime : 2018-10-11 19:28:59
     * createUserUuid : 1
     * updateTime : null
     * updateUserUuid : null
     * sysRemark : null
     * validFlag : 1
     * version : 0
     * uuid : 73ed13c418f54d6fbca98d66e08d081a
     * username : lxl5
     * password : $2a$10$edpZ2LfRFu7GQFeKxSRUHeNvtRzRZsRVb05iUdf/NVg8.mw6yqxOW
     * name : 刘小龙
     * tel : 18970313532
     * idCard : 362331199108213012
     * searchDays : 10
     * soClerkFlag : 1
     * poClerkFlag : 0
     * serviceEngFlag : 0
     * updateOtherBillFlag : 0
     * viewSupplierFlag : 0
     * viewCostFlag : 0
     * viewWsPriceFlag : 0
     * viewSoPriceFlag : 0
     * viewSelfFlag : 0
     * enableFlag : 1
     * remark : null
     * id : 20181011192859171
     * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     */

//    private String topCompanyCode;
//    private String createTime;
//    private String createUserUuid;
//    private Object updateTime;
//    private Object updateUserUuid;
//    private Object sysRemark;
//    private int validFlag;
//    private int version;
    private String uuid;
//    private String username;
//    private String password;
    private String name;
//    private String tel;
//    private String idCard;
//    private int searchDays;
//    private int soClerkFlag;
//    private int poClerkFlag;
//    private int serviceEngFlag;
//    private int updateOtherBillFlag;
//    private int viewSupplierFlag;
//    private int viewCostFlag;
//    private int viewWsPriceFlag;
//    private int viewSoPriceFlag;
//    private int viewSelfFlag;
//    private int enableFlag;
//    private Object remark;
    private long id;
//    private String companyUuid;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
