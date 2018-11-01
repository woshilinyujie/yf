package jh.zkj.com.yf.Bean;

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
    //本地使用 存放金额
    private String money = "0";

    public String getCashierTypeUuid() {
        return cashierTypeUuid;
    }

    public void setCashierTypeUuid(String cashierTypeUuid) {
        this.cashierTypeUuid = cashierTypeUuid;
    }

    public String getCashierTypeName() {
        return cashierTypeName;
    }

    public void setCashierTypeName(String cashierTypeName) {
        this.cashierTypeName = cashierTypeName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
