package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/10/25
 * use
 */
public class CommodityBean implements Serializable{

    /**
     * records : [{"name":"iPhone X","fullName":"iPhone X 白","stockAge":"11","warehouseUuid":"6b064ee2c14144f1ab0a9989f4fe18c5","warehouseName":"仓库01","serialNo":"3333333333423","firstInTime":"2018-10-15 19:09:08","currentStockAge":"9","exceedStockAge":"0"}]
     * total : 12
     * size : 20
     * current : 1
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private int pages;
    private ArrayList<CommodityInfoBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<CommodityInfoBean> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<CommodityInfoBean> records) {
        this.records = records;
    }

}
