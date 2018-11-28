package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/11/20
 * use
 */
public class StockFilterBean implements Serializable{
    //公司
    private FilterCompanyBean comBean;
    //仓库
    private FilterBaseWarehouseBean warehouseBean;
    //分类
    private FilterClassifyBean classifyBean;
    //品牌
    private FilterBrandBean brandBean;
    //型号
    private FilterProductBean productBean;

    public FilterCompanyBean getComBean() {
        return comBean;
    }

    public void setComBean(FilterCompanyBean comBean) {
        this.comBean = comBean;
    }

    public FilterBaseWarehouseBean getWarehouseBean() {
        return warehouseBean;
    }

    public void setWarehouseBean(FilterBaseWarehouseBean warehouseBean) {
        this.warehouseBean = warehouseBean;
    }

    public FilterClassifyBean getClassifyBean() {
        return classifyBean;
    }

    public void setClassifyBean(FilterClassifyBean classifyBean) {
        this.classifyBean = classifyBean;
    }

    public FilterBrandBean getBrandBean() {
        return brandBean;
    }

    public void setBrandBean(FilterBrandBean brandBean) {
        this.brandBean = brandBean;
    }

    public FilterProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(FilterProductBean productBean) {
        this.productBean = productBean;
    }

    public void createCompany(){
        comBean = new FilterCompanyBean();
    }

    public void cleanBean(){
        comBean = null;
        warehouseBean = null;
        classifyBean = null;
        brandBean = null;
        productBean = null;

    }

    public boolean isEmptyComBean(){
        return comBean == null;
    }

    public boolean isEmptyWarehouseBean(){
        return warehouseBean == null;
    }

    public boolean isEmptyClassifyBean(){
        return classifyBean == null;
    }

    public boolean isEmptyBrandBean(){
        return brandBean == null;
    }

    public boolean isEmptyProductBean(){
        return productBean == null;
    }
}
