package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/11/20
 * use 商品分类下拉选择
 */
public class FilterCompanyBean implements Serializable{

    /**
     * uuid : 05ae915f66e343c69dc2774231641bfc
     * code : 00001002001
     * name : 乐清店
     */

    private String uuid;
    private String code;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
