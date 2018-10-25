package jh.zkj.com.yf.API;

import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;

/**
 * Created by linyujie on 18/10/23.
 */

public class OrderAPI {
    public final String API = APIConstant.API;
    public final String TOKEN = "43e42efe-6593-4ea6-8a04-d67e64cd435c";

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


    public interface IResultMsg {
        void Result(String json);
        void Error(String json);
    }
}
