package jh.zkj.com.yf.Bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/10/31
 * use
 */
public class HarvestModeBean implements Serializable{


    /**
     * cashierTypeUuid : 3382739f730f4857a9b5185e6fbd8bad
     * cashierTypeName : 现金
     */

    private String cashierTypeUuid;
    private String cashierTypeName;
    //后台改字段  但是后台是用post请求提交的  当转换的时候他们后台接收的是cashierTypeUuid 这个字段
    // 导致json转换的时候会有问题  所以两个都保留
    private String uuid;
    private String name;
    private int enableFlag;
    private String bankUuid;
    //本地使用 存放金额
    private String amount = "0";

    public String getCashierTypeUuid() {
        return cashierTypeUuid;
    }

    public void setCashierTypeUuid(String cashierTypeUuid) {
        if(!TextUtils.isEmpty(cashierTypeUuid))
        this.cashierTypeUuid = cashierTypeUuid;
    }

    public String getCashierTypeName() {
        return cashierTypeName;
    }

    public void setCashierTypeName(String cashierTypeName) {
        if(!TextUtils.isEmpty(cashierTypeName))
            this.cashierTypeName = cashierTypeName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.cashierTypeUuid = uuid;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.cashierTypeName = name;
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String money) {
        this.amount = money;
    }

    public int getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(int enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getBankUuid() {
        return bankUuid;
    }

    public void setBankUuid(String bankUuid) {
        this.bankUuid = bankUuid;
    }
}
