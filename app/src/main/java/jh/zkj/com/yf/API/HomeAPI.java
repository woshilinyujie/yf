package jh.zkj.com.yf.API;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.HomeMenuBean;
import jh.zkj.com.yf.Bean.VersionBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.Toast.MToast;

/**
 * Created by wdefer
 * 2018/11/8
 * use
 */
public class HomeAPI {
    public final String API = APIConstant.API;
    //    public final String TOKEN = "bearer 292f06ac-f530-4218-a991-b1440ebc3d17";
    public final String TOKEN = "bearer 73677450-0871-4b41-8059-c0e4aea6348d";


    public void getVersion(final Context context, final IResultMsg<VersionBean> iResultMsg) {
        OkGo.<String>get(API + ":3001/crm/stdProductsVersion/currentVersion").tag(context)
                .params("version", "1.0.0")
                .params("productType", "jh-erp-3c")
                .params("typeDetail", "ANDROID")
                .params("type", "APP")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s = response.body().toString();
                        try {
                            VersionBean versionBean = GsonUtils.GsonToBean(s, VersionBean.class);
                            if (versionBean.getCode() == 0 && versionBean.getData() != null) {
                                iResultMsg.Result(versionBean);
                            } else {
                                MToast.makeText(context, versionBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            MToast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public interface IResultMsg<T> {
        void Result(T bean);

        void Error(String json);
    }
}
