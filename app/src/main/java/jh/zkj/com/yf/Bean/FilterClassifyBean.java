package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/11/20
 * use 商品分类下拉选择
 */
public class FilterClassifyBean implements Serializable {

    /**
     * uuid : ef73844556b446c5bb40ef3bce03435c
     * parentUuid : null
     * level : 1
     * name : 商品分类测试-手机1
     * enableFlag : 1
     * remark : null
     * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     * version : 2
     * levelFullName : 商品分类测试-手机1
     */

    private String uuid;
    private Object parentUuid;
    private String level;
    private String name;
    private int enableFlag;
    private Object remark;
    private String companyUuid;
    private int version;
    private String levelFullName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Object getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(Object parentUuid) {
        this.parentUuid = parentUuid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLevelFullName() {
        return levelFullName;
    }

    public void setLevelFullName(String levelFullName) {
        this.levelFullName = levelFullName;
    }

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
