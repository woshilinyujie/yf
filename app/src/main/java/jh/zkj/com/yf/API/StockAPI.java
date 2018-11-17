package jh.zkj.com.yf.API;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.Bean.commodityStockBean;
import jh.zkj.com.yf.Mutils.PrefUtils;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class StockAPI {

    private final Context context;
    public String API = APIConstant.API + ":3001/";
    public String TOKEN = "bearer ac37d290-0a14-463c-be3f-efa357362ee6";

    public StockAPI(Context context){
        this.context = context;
//        TOKEN = "bearer " + PrefUtils.getString(context, "erp_token", "");
        API = "http://192.168.68.45:3001/";
    }

    public void getCommodityList(final OrderAPI.IResultMsg<commodityStockBean> iResultMsg){
        OkGo.<String>get(API + HttpConstant.HTTP_REPORT_SKUSTOCK_APP)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        BaseBean<SalesmanBean> baseBean = JSON.parseObject(response.body(),
//                                new TypeReference<BaseBean<SalesmanBean>>() {
//                                });

//                        iResultMsg.Result(baseBean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        iResultMsg.Error(response.body());
                    }
                });
    }
}
