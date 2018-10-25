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
     * topCompanyCode : 00001
     * createTime : 2018-09-29 18:11:47
     * createUserUuid : 1
     * updateTime : 2018-09-30 09:44:48
     * updateUserUuid : 1
     * sysRemark : null
     * validFlag : 1
     * version : 1
     * uuid : 1
     * username : whz
     * password : $2a$10$HCHkLz33QI.BfqxNTzgCk.BEF4GyJboWFOnf0WN81l9PsyqgPt2hO
     * name : 吴
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
     * id : 20180929181148008
     * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     */

//        private String topCompanyCode;
//        private String createTime;
//        private String createUserUuid;
//        private String updateTime;
//        private String updateUserUuid;
//        private Object sysRemark;
//        private int validFlag;
//        private int version;
    //唯一识别id
    private String uuid;
    //        private String username;
//        private String password;
    private String name;
    //        private String tel;
//        private String idCard;
//        private int searchDays;
//        private int soClerkFlag;
//        private int poClerkFlag;
//        private int serviceEngFlag;
//        private int updateOtherBillFlag;
//        private int viewSupplierFlag;
//        private int viewCostFlag;
//        private int viewWsPriceFlag;
//        private int viewSoPriceFlag;
//        private int viewSelfFlag;
//        private int enableFlag;
//        private Object remark;
    private long id;
    //选中状态
    private boolean isSelect;
//        private String companyUuid;


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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
