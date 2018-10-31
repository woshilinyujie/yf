package jh.zkj.com.yf.Bean;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;

import jh.zkj.com.yf.BuildConfig;

/**
 * Created by wdefer
 * 2018/10/25
 * use
 */
public class CommodityInfoBean implements Serializable{

    /**
     * name : iPhone X
     * fullName : iPhone X 白
     * stockAge : 11
     * warehouseUuid : 6b064ee2c14144f1ab0a9989f4fe18c5
     * warehouseName : 仓库01
     * serialNo : 3333333333423
     * firstInTime : 2018-10-15 19:09:08
     * currentStockAge : 9
     * exceedStockAge : 0
     */

    private String name;
    private String fullName;
    //库龄
    private String stockAge;
    private String warehouseUuid;
    private String warehouseName;
    //串号
    private String serialNo;
    //入库时间
    private String firstInTime;
    private String currentStockAge;
    private String exceedStockAge;
    private String uuid;
    private String qty;
    //本地使用  无串号商品记录数量
    private int count;
    //单价
    private BigDecimal price = new BigDecimal("-1");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStockAge() {
        return stockAge;
    }

    public void setStockAge(String stockAge) {
        this.stockAge = stockAge;
    }

    public String getWarehouseUuid() {
        return warehouseUuid;
    }

    public void setWarehouseUuid(String warehouseUuid) {
        this.warehouseUuid = warehouseUuid;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getFirstInTime() {
        return firstInTime;
    }

    public void setFirstInTime(String firstInTime) {
        this.firstInTime = firstInTime;
    }

    public String getCurrentStockAge() {
        return currentStockAge;
    }

    public void setCurrentStockAge(String currentStockAge) {
        this.currentStockAge = currentStockAge;
    }

    public String getExceedStockAge() {
        return exceedStockAge;
    }

    public void setExceedStockAge(String exceedStockAge) {
        this.exceedStockAge = exceedStockAge;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void addCount() {
        this.count++;
    }

    public void lessCount() {
        if(this.count > 0)
            this.count--;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
