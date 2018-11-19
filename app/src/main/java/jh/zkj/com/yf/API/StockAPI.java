package jh.zkj.com.yf.API;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.SerialNoBean;
import jh.zkj.com.yf.Bean.SerialNoTrackBean;
import jh.zkj.com.yf.Bean.SkuStockBean;
import jh.zkj.com.yf.Bean.commodityStockBean;
import jh.zkj.com.yf.Mview.LoadingDialog;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class StockAPI {

    private final Context context;
    public String API = APIConstant.API + ":3001/";

    public String TOKEN = "bearer a5b5729e-4ca8-4f76-a6ad-c6eac3fa7be7";
    private final LoadingDialog dialog;

    public StockAPI(Context context){
        this.context = context;
//        TOKEN = "bearer " + PrefUtils.getString(context, "erp_token", "");
        API = "http://192.168.68.172:3001/";
        dialog = new LoadingDialog(context);
    }

    /**
     * 商品库存
     * @param classifyUuid 商品分类
     * @param companyUuid 公司uuid
     * @param brandUuid 品牌uuid
     * @param skuUuid 型号uuid
     * @param pageNumber 页码
     * @param pageSize 每页展示数
     * @param iResultMsg msg
     */
    public void getCommodityList(String classifyUuid, String companyUuid, String brandUuid
            , String skuUuid, int pageNumber, int pageSize, final OrderAPI.IResultMsg<commodityStockBean> iResultMsg){
        dialog.showLoading();
        OkGo.<String>get(API + HttpConstant.HTTP_REPORT_SKUSTOCK_APP)
                .headers("Authorization", TOKEN)
                .params("classifyUuid", classifyUuid)
                .params("companyUuid", companyUuid)
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

                        iResultMsg.Result(baseBean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing())
                            dialog.dismissLoading();
                        iResultMsg.Error(response.body());
                    }
                });
    }

    //库存序列号
    public void getSerialNoList(String keywords, final OrderAPI.IResultMsg<SerialNoBean> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(API + HttpConstant.HTTP_BIZ_SERIAL_NO_LIST)
                .headers("Authorization", TOKEN)
                .params("keywords", keywords)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<SerialNoBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<SerialNoBean>>() {});

                        iResultMsg.Result(baseBean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //序列号追踪
    public void getSerialNoTrack(String serial, final OrderAPI.IResultMsg<ArrayList<SerialNoTrackBean>> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(API + HttpConstant.HTTP_BIZ_SERIAL_NO_TRACK)
                .headers("Authorization", TOKEN)
                .params("serial", serial)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<SerialNoTrackBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<SerialNoTrackBean>>>() {});

                        iResultMsg.Result(baseBean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        iResultMsg.Error(response.body());
                    }
                });
    }

    //分仓库存
    public void getSkuStockList(String warehouseName, final OrderAPI.IResultMsg<SkuStockBean> iResultMsg){
        dialog.showLoading();

        OkGo.<String>get(API + HttpConstant.HTTP_REPORT_SKUSTOCK_APP_STOCKPART)
                .headers("Authorization", TOKEN)
                .headers("warehouseName", warehouseName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<SkuStockBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<SkuStockBean>>() {});

                        iResultMsg.Result(baseBean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(dialog.isShowing())
                            dialog.dismissLoading();

                        iResultMsg.Error(response.body());
                    }
                });
    }

}
