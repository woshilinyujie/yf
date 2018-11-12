package jh.zkj.com.yf.API;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.HomeMenuBean;

/**
 * Created by wdefer
 * 2018/11/8
 * use
 */
public class HomeAPI {
    public final String API = APIConstant.API;
//    public final String TOKEN = "bearer 292f06ac-f530-4218-a991-b1440ebc3d17";
    public final String TOKEN = "bearer 73677450-0871-4b41-8059-c0e4aea6348d";

    /**
     * 获取首页menu
     */
    public void getBasicMenuList(final OrderAPI.IResultMsg<HomeMenuBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_MENU_LIST)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<HomeMenuBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<HomeMenuBean>>() {});
                        if (APIConstant.REQUEST_SUCCESS.equals(baseBean.getCode())) {
                            iResultMsg.Result(baseBean.getData());
                        } else {

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
