package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/11/20
 * use 商品品牌下拉选择
 */
public class FilterBrandBean implements Serializable {

    /**
     * topCompanyCode : 00001
     * createTime : 2018-10-08 19:49:25
     * createUserUuid : 1
     * updateTime : 2018-10-19 06:17:02
     * updateUserUuid : 1
     * sysRemark : null
     * validFlag : 1
     * version : 4
     * uuid : 5419c9e443da46b58eff86da6fa9d3d6
     * name : VIVO
     * sortNum : null
     * industryCode : 800001
     * remark : 66666666
     * id : 20181008194925244
     * basicBrandUuid : null
     * companyUuid : 92156253-0723-4b3a-b09e-de6f6d8f192d
     * enableFlag : 1
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
    private String name;
    private Object sortNum;
    private int industryCode;
    private String remark;
    private long id;
    private Object basicBrandUuid;
    private String companyUuid;
    private int enableFlag;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSortNum() {
        return sortNum;
    }

    public void setSortNum(Object sortNum) {
        this.sortNum = sortNum;
    }

    public int getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(int industryCode) {
        this.industryCode = industryCode;
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

    public Object getBasicBrandUuid() {
        return basicBrandUuid;
    }

    public void setBasicBrandUuid(Object basicBrandUuid) {
        this.basicBrandUuid = basicBrandUuid;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public int getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(int enableFlag) {
        this.enableFlag = enableFlag;
    }

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
