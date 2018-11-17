package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by wdefer
 * 2018/11/16
 * use 商品库存
 */
public class commodityStockBean {

    /**
     * content : [{"pinyin":"","warehouseList":[{"warehouse_name":"仓库01","qty":2},{"warehouse_name":"仓库2342","qty":22}],"qty":24,"name":"测试配件"},{"pinyin":"11","warehouseList":[{"warehouse_name":"仓库02","qty":0}],"qty":0,"name":"111"},{"pinyin":"23","warehouseList":[{"warehouse_name":"仓库01","qty":19}],"qty":19,"name":"23412"},{"pinyin":"66","warehouseList":[{"warehouse_name":"仓库01","qty":128}],"qty":128,"name":"666test"},{"pinyin":"cesgi","warehouseList":[{"warehouse_name":"仓库2342","qty":10}],"qty":10,"name":"测试配件2"},{"pinyin":"gter","warehouseList":[{"warehouse_name":"仓库01","qty":10}],"qty":10,"name":"gter"},{"pinyin":"huawei","warehouseList":[{"warehouse_name":"test1","qty":0},{"warehouse_name":"test111","qty":0},{"warehouse_name":"仓库01","qty":11}],"qty":11,"name":"华为畅享7手机壳黑色"},{"pinyin":"iPhone","warehouseList":[{"warehouse_name":"仓库01","qty":337}],"qty":337,"name":"iPhone X 白"},{"pinyin":"rongyaov10","warehouseList":[{"warehouse_name":"仓库01","qty":52},{"warehouse_name":"仓库02","qty":32}],"qty":84,"name":"荣耀V10 极光蓝 128G"},{"pinyin":"xiaomi","warehouseList":[{"warehouse_name":"test1","qty":0},{"warehouse_name":"test11","qty":0},{"warehouse_name":"test111","qty":0},{"warehouse_name":"test22","qty":0},{"warehouse_name":"仓库01","qty":37},{"warehouse_name":"仓库02","qty":0}],"qty":37,"name":"小米移动电源/充电宝20000毫安"}]
     * totalElements : 10
     * totalPages : 0
     * number : 0
     * size : 50
     * summary : {"totalQty":667}
     */

    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private SummaryBean summary;
    private List<ContentBean> content;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SummaryBean getSummary() {
        return summary;
    }

    public void setSummary(SummaryBean summary) {
        this.summary = summary;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class SummaryBean {
        /**
         * totalQty : 667.0
         */

        private double totalQty;

        public double getTotalQty() {
            return totalQty;
        }

        public void setTotalQty(double totalQty) {
            this.totalQty = totalQty;
        }
    }

    public static class ContentBean {
        /**
         * pinyin :
         * warehouseList : [{"warehouse_name":"仓库01","qty":2},{"warehouse_name":"仓库2342","qty":22}]
         * qty : 24.0
         * name : 测试配件
         */

        private String pinyin;
        private double qty;
        private String name;
        private List<WarehouseListBean> warehouseList;

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<WarehouseListBean> getWarehouseList() {
            return warehouseList;
        }

        public void setWarehouseList(List<WarehouseListBean> warehouseList) {
            this.warehouseList = warehouseList;
        }

        public static class WarehouseListBean {
            /**
             * warehouse_name : 仓库01
             * qty : 2.0
             */

            private String warehouse_name;
            private double qty;

            public String getWarehouse_name() {
                return warehouse_name;
            }

            public void setWarehouse_name(String warehouse_name) {
                this.warehouse_name = warehouse_name;
            }

            public double getQty() {
                return qty;
            }

            public void setQty(double qty) {
                this.qty = qty;
            }
        }
    }
}
