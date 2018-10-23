package jh.zkj.com.yf.API;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.SalesmanBean;

/**
 * Created by linyujie on 18/10/23.
 */

public class OrderAPI {
    public final String API = APIConstant.API;
    private final Gson gson;

    public OrderAPI(){

        gson = new Gson();
    }

    /**
     * 根据串号获取商品信息
     */
    public void getArticleMsg(String soClerkFlag, String currentCompany, final IResultMsg iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_DATA_USER)
                .params("access_token", "97298f2c-6b25-4bdb-b26b-4ff8901efb11")
                .params("soClerkFlag", soClerkFlag)
                .params("currentCompany", currentCompany)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<ArrayList<SalesmanBean>> baseBean = gson.fromJson(response.body(), BaseBean.class);
                        ArrayList<SalesmanBean> data = baseBean.getData();
                        String name = data.get(0).getName();
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
