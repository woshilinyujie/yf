package jh.zkj.com.yf.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Mview.Toast.EToast;

/**
 * Created by linyujie on 18/10/23.
 */

public class OrderAPI {
    public final String API = APIConstant.API;
    public final String TOKEN = "8c93fcac-7fb5-46de-8102-f810adbec829";

    /**
     * 获取业务员信息
     */
    public void getSalesmanInfo(String soClerkFlag, String currentCompany, final IResultMsg iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_DATA_USER)
                .params("access_token", TOKEN)
                .params("soClerkFlag", soClerkFlag)
                .params("currentCompany", currentCompany)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iResultMsg.Result(response.body());
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
    public void getSearchCommodity(String keyWord, int pageNum, int pageSize, final IResultMsg iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_PRODUCT_KEYWORDS)
                .params("access_token", TOKEN)
                .params("keyWord", keyWord)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(BuildConfig.DEBUG){
                            Log.d("wdefer" , "搜索商品 -->");
                        }
                        iResultMsg.Result(response.body());
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
    public void getClientInfo(String keyWord, int pageNum, int pageSize, final IResultMsg iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_MEMBER_INFO)
                .params("access_token", TOKEN)
                .params("keyWord", keyWord)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .params("identNo", "")
                .params("cardNo", "")
                .params("mobilePhone", "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iResultMsg.Result(response.body());
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 我的订单
     * type  1.未收款 2.以收款 3.已取消
     */
    public void getMyOrderList(Context context, String type, String keywords, int pageNum, int pageSize, final int flag, final IResultMsgOne iResultMsg){
        try{
            OkGo.<String>get(API+HttpConstant.HTTP_BASIC_GET_ORDER_LIST)
                    .params("access_token", TOKEN)
                    .params("type",type)
                    .params("pageNum", pageNum)
                    .params("pageSize", pageSize)
                    .params("keywords", keywords)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            iResultMsg.Result(response.body(),flag);
                        }
                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            iResultMsg.Error(response.body(),flag);
                        }
                    });
        }catch (Exception e){
            EToast.makeText(context,"信息解析错误"+e.toString(),Toast.LENGTH_SHORT).show();
        }

    }


    public interface IResultMsg {
        void Result(String json);
        void Error(String json);
    }
    public interface IResultMsgOne {
        void Result(String json,int flag);
        void Error(String json,int flag);
    }
}
