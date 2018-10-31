package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by wdefer
 * 2018/10/29
 * use
 */
public class OrderDetailsBean {


    /**
     * uuid : dcf19782-b25c-425f-9b5b-b9d09f612b29
     * bizType : SO
     * bizDate : 2018-10-29
     * billNo : SO00560
     * mobilePhone : 15395617328
     * name : 可是
     * sex : 0
     * statusFlag : 1
     * remark :
     * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     * companyName : 浙江公司test
     * createUserName : 吴
     * topCompanyCode : 00001
     * totalAmount : 0
     * totalQuantity : 0
     * detailDTOList : []
     * createTime : 2018-10-29 17:45:51
     */

    private String uuid;
    private String bizType;
    private String bizDate;
    private String billNo;
    private String mobilePhone;
    private String name;
    private int sex;
    private int statusFlag;
    private String remark;
    private String companyUuid;
    private String companyName;
    private String createUserName;
    private String topCompanyCode;
    private int totalAmount;
    private int totalQuantity;
    private String createTime;
    private List<?> detailDTOList;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getTopCompanyCode() {
        return topCompanyCode;
    }

    public void setTopCompanyCode(String topCompanyCode) {
        this.topCompanyCode = topCompanyCode;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<?> getDetailDTOList() {
        return detailDTOList;
    }

    public void setDetailDTOList(List<?> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }
}
