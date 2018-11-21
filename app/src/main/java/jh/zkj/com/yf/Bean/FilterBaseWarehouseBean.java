package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wdefer
 * 2018/11/20
 * use 仓库下拉选择
 */
public class FilterBaseWarehouseBean implements Serializable {

    /**
     * topCompanyCode : 90372
     * createTime : [2018,11,17,14,37,43]
     * createUserUuid : 317367f28f4842fc8039a7bd917d0854
     * updateTime : [2018,11,17,14,37,43]
     * updateUserUuid : 317367f28f4842fc8039a7bd917d0854
     * sysRemark : null
     * validFlag : 1
     * version : 6
     * uuid : 33ffa4038ab4493c8b6146f95a33ac31
     * name : 总仓
     * code : null
     * pinyin : ZONGCANG
     * mnemonicCode : 543545
     * typeUuid :
     * saleFlag : 1
     * enableFlag : 1
     * ascriptionCompanyUuid : 738d83b7443544b69dfcd89b4a5d61b2
     * remark :
     * id : 20181117143743877
     * companyUuid : 738d83b7443544b69dfcd89b4a5d61b2
     */

    private String topCompanyCode;
    private String createUserUuid;
    private String updateUserUuid;
    private Object sysRemark;
    private int validFlag;
    private int version;
    private String uuid;
    private String name;
    private Object code;
    private String pinyin;
    private String mnemonicCode;
    private String typeUuid;
    private int saleFlag;
    private int enableFlag;
    private String ascriptionCompanyUuid;
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

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid;
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

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public String getTypeUuid() {
        return typeUuid;
    }

    public void setTypeUuid(String typeUuid) {
        this.typeUuid = typeUuid;
    }

    public int getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(int saleFlag) {
        this.saleFlag = saleFlag;
    }

    public int getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(int enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getAscriptionCompanyUuid() {
        return ascriptionCompanyUuid;
    }

    public void setAscriptionCompanyUuid(String ascriptionCompanyUuid) {
        this.ascriptionCompanyUuid = ascriptionCompanyUuid;
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

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
