package jh.zkj.com.yf.API;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.FilterBaseWarehouseBean;
import jh.zkj.com.yf.Bean.FilterBrandBean;
import jh.zkj.com.yf.Bean.FilterClassifyBean;
import jh.zkj.com.yf.Bean.FilterCompanyBean;
import jh.zkj.com.yf.Bean.FilterProductBean;
import jh.zkj.com.yf.Bean.SerialNoBean;
import jh.zkj.com.yf.Bean.SerialNoTrackBean;
import jh.zkj.com.yf.Bean.SkuStockBean;
import jh.zkj.com.yf.Bean.commodityStockBean;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.Toast.MToast;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class StockAPI {

    private final Context context;
//    String api = "http://192.168.68.172:3001/";

    public String TOKEN = "bearer 4976b2ee-3fa7-4261-8f84-022001b676f5";
    private final LoadingDialog dialog;

    public StockAPI(Context context){
        this.context = context;
//        TOKEN = "bearer " + PrefUtils.getString(context, "erp_token", "");
//        API = "http://192.168.68.172:3001/";
        TOKEN = "bearer " + PrefUtils.getString(context, "erp_token", "");
        dialog = new LoadingDialog(context);
    }

    /**
     * 商品库存
     * @param classifyUuid 商品分类
     * @param companyUuid 公司uuid
     * @param companyCode 公司code
     * @param brandUuid 品牌uuid
     * @param skuUuid 型号uuid
     * @param pageNumber 页码
     * @param pageSize 每页展示数
     * @param iResultMsg msg
     */
    public void getCommodityList(String skuFullName, String classifyUuid, String companyUuid
            , String companyCode, String brandUuid, String skuUuid, int pageNumber, int pageSize
            , final OrderAPI.IResultMsg<commodityStockBean> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API  + HttpConstant.HTTP_REPORT_SKU_STOCK_APP)
                .headers("Authorization", TOKEN)
                .params("skuFullName", skuFullName)
                .params("classifyUuid", classifyUuid)
                .params("companyUuid", companyUuid)
                .params("companyCode", companyCode)
                .params("brandUuid", brandUuid)
                .params("skuUuid", skuUuid)
                .params("pageNumber", pageNumber)
                .params("pageSize", pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<commodityStockBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<commodityStockBean>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 库存序列号
     * @param keywords 搜索关键字
     * @param classifyUuid 商品分类
     * @param companyUuid 公司uuid
     * @param companyCode 公司code
     * @param brandUuid 品牌uuid
     * @param skuUuid 型号uuid
     * @param warehouseUuid 仓库uuid
     * @param iResultMsg msg
     */
    public void getSerialNoList(String keywords, String classifyUuid, String companyUuid
            , String companyCode, String brandUuid, String skuUuid, String warehouseUuid
            , int pageNum, int pageSize, final OrderAPI.IResultMsg<SerialNoBean> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API + HttpConstant.HTTP_REPORT_SERIAL_SERIAL_STOCK_SERIAL)
                .headers("Authorization", TOKEN)
                .params("keywords", keywords)
                .params("classifyUuid", classifyUuid)
                .params("companyUuid", companyUuid)
                .params("companyCode", companyCode)
                .params("brandUuid", brandUuid)
                .params("skuUuid", skuUuid)
                .params("pageNumber", pageNum)
                .params("pageSize", pageSize)
                .params("warehouseUuid", warehouseUuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<SerialNoBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<SerialNoBean>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //序列号追踪
    public void getSerialNoTrack(String serial, final OrderAPI.IResultMsg<SerialNoTrackBean> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API /*api*/ + HttpConstant.HTTP_BIZ_SERIAL_NO_TRACK)
                .headers("Authorization", TOKEN)
//                .headers("Authorization", "bearer 4976b2ee-3fa7-4261-8f84-022001b676f5")
                .params("serial", serial)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<SerialNoTrackBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<SerialNoTrackBean>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //分仓库存
    public void getSkuStockList(String warehouseName, final OrderAPI.IResultMsg<SkuStockBean> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API + HttpConstant.HTTP_REPORT_SKU_STOCK_APP_STOCK_PART)
                .headers("Authorization", TOKEN)
                .params("warehouseName", warehouseName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<SkuStockBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<SkuStockBean>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //公司下拉选择
    public void getFilterCompany(final OrderAPI.IResultMsg<ArrayList<FilterCompanyBean>> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API + HttpConstant.HTTP_BASIC_DATA_COMPANY)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<FilterCompanyBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<FilterCompanyBean>>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //商品分类下拉选择
    public void getFilterClassify(final OrderAPI.IResultMsg<ArrayList<FilterClassifyBean>> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API + HttpConstant.HTTP_BASIC_DATA_CLASSIFY)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<FilterClassifyBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<FilterClassifyBean>>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //商品品牌下拉选择
    public void getFilterBrand(final OrderAPI.IResultMsg<ArrayList<FilterBrandBean>> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API  + HttpConstant.HTTP_BASIC_DATA_BRAND)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<FilterBrandBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<FilterBrandBean>>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //商品型号下拉选择
    public void getFilterProduct(final OrderAPI.IResultMsg<ArrayList<FilterProductBean>> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API + HttpConstant.HTTP_BASIC_DATA_PRODUCT)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<FilterProductBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<FilterProductBean>>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //仓库下拉选择
    public void getFilterBaseWarehouse(final OrderAPI.IResultMsg<ArrayList<FilterBaseWarehouseBean>> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(APIConstant.API + HttpConstant.HTTP_BASIC_BASE_WAREHOUSE_SELECT_LIST)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<FilterBaseWarehouseBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<FilterBaseWarehouseBean>>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{
                            if (!TextUtils.isEmpty(baseBean.getMsg())){
                                MToast.makeText(context, baseBean.getMsg(), MToast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing()){
                            dialog.dismissLoading();
                        }

                        if (!TextUtils.isEmpty(response.body())){
                            MToast.makeText(context, response.body(), MToast.LENGTH_SHORT).show();
                        }

                        iResultMsg.Error(response.body());
                    }
                });
    }

}
