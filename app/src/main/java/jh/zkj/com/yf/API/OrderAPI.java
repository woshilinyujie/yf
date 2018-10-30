package jh.zkj.com.yf.API;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.ClientInfoBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;

/**
 * Created by linyujie on 18/10/23.
 */

public class OrderAPI {
    public final String API = APIConstant.API;
    public final String TOKEN = "bearer 38c71baf-96d0-4f27-b4f8-e356aa53244f";

    /**
     * 获取业务员信息
     */
    public void getSalesmanInfo(String soClerkFlag, String currentCompany, final IResultMsg<ArrayList<SalesmanBean>> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_DATA_USER)
                .headers("Authorization", TOKEN)
                .params("soClerkFlag", soClerkFlag)
                .params("currentCompany", currentCompany)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<ArrayList<SalesmanBean>> arrayListBaseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<SalesmanBean>>>(){});

                        if(APIConstant.REQUEST_SUCCESS.equals(arrayListBaseBean.getCode())){
                            iResultMsg.Result(arrayListBaseBean.getData());
                        }else{

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body());
                    }
                });

    }

    /**
     * 搜索商品
     */
    public void getSearchCommodity(String keyWord, int pageNum, int pageSize, final IResultMsg<CommodityBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_PRODUCT_KEYWORDS)
                .headers("Authorization", TOKEN)
                .params("keyWord", keyWord)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if(BuildConfig.DEBUG){
                            Log.d("wdefer" , "json == " + response.body());
                        }
                        BaseBean<CommodityBean> comInfoBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<CommodityBean>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(comInfoBean.getCode())){
                            iResultMsg.Result(comInfoBean.getData());
                        }else{

                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if(BuildConfig.DEBUG){
                            Log.d("wdefer" , "搜索商品 -->");
                        }
                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 获取会员的接口
     */
    public void getClientInfo(String keyWord, int pageNum, int pageSize, final IResultMsg<ArrayList<ClientInfoBean>> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_MEMBER_INFO)
                .headers("Authorization", TOKEN)
                .params("keyWord", keyWord)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .params("identNo", "")
                .params("cardNo", "")
                .params("mobilePhone", "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if (BuildConfig.DEBUG) {
                            Log.e("wdefer", "json == " + response.body());
                        }

                        BaseBean<ArrayList<ClientInfoBean>> client = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<ClientInfoBean>>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(client.getCode())){
                            iResultMsg.Result(client.getData());
                        }else{

                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 生成订单
     */
    public void getCreateOrder(String json, final IResultMsg<String> iResultMsg) {
        OkGo.<String>post(API + HttpConstant.HTTP_BASIC_SO_APP)
                .headers("Authorization", TOKEN)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if(BuildConfig.DEBUG){
                            Log.d("okgo request json" , response.body());
                        }

                        BaseBean<String> orderNum = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<String>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(orderNum.getCode())){
                            iResultMsg.Result(orderNum.getData());
                        }else{

                        }

                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 查询订单详情
     */
    public void getQueryOrder(String orderId, final IResultMsg<OrderDetailsBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_SO_APP + orderId)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(BuildConfig.DEBUG){
                            Log.d("okgo request json" , response.body());
                        }

                        BaseBean<OrderDetailsBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<OrderDetailsBean>>() {});

                        if(APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())){
                            iResultMsg.Result(baseBean.getData());
                        }else{

                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body());
                    }
                });
    }


    public interface IResultMsg<T> {
        void Result(T bean);
        void Error(String json);
    }
}
