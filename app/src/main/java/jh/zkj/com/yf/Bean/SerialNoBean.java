package jh.zkj.com.yf.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/11/17
 * use
 */
public class SerialNoBean {

    /**
     * records : [{"uuid":"163cc95454124fe3b5c6e1cc7f25507c","name":"iPhone X","fullName":"iPhone X 白","stockAge":"0","stockQty":"1","warehouseUuid":"6b064ee2c14144f1ab0a9989f4fe18c5","warehouseName":"仓库01","serialNo":"2345454345","firstInTime":"2018-10-31 20:21:04","currentStockAge":"14","exceedStockAge":"0","stockPrice":"1000.00000000"}]
     * total : 3
     * size : 50
     * current : 1
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private int pages;
    private ArrayList<RecordsBean> records;

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

    public ArrayList<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * uuid : 163cc95454124fe3b5c6e1cc7f25507c
         * name : iPhone X
         * fullName : iPhone X 白
         * stockAge : 0
         * stockQty : 1
         * warehouseUuid : 6b064ee2c14144f1ab0a9989f4fe18c5
         * warehouseName : 仓库01
         * serialNo : 2345454345
         * firstInTime : 2018-10-31 20:21:04
         * currentStockAge : 14
         * exceedStockAge : 0
         * stockPrice : 1000.00000000
         */

        private String uuid;
        private String name;
        private String fullName;
        private String stockAge;
        private String stockQty;
        private String warehouseUuid;
        private String warehouseName;
        private String serialNo;
        private String firstInTime;
        private String currentStockAge;
        private String exceedStockAge;
        private String stockPrice;

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

        public String getStockQty() {
            return stockQty;
        }

        public void setStockQty(String stockQty) {
            this.stockQty = stockQty;
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

        public String getStockPrice() {
            return stockPrice;
        }

        public void setStockPrice(String stockPrice) {
            this.stockPrice = stockPrice;
        }
    }
}
