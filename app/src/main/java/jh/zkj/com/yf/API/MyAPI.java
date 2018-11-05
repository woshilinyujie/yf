package jh.zkj.com.yf.API;

import android.content.Context;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import jh.zkj.com.yf.Bean.CalibrateIdCardBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;

/**
 * Created by linyujie on 18/11/1.
 */

public class MyAPI {
    public final String API = APIConstant.API;
    public final String TOKEN = "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNocy00KgzAQQOG7zNpFxkx-9DISzQQiipKkpVB692bcfrz3hb1lmCGQ17xhSJGRRmemuDq0bG302hMqGKC-1h7eR2jpKmeHXKuceRFbKpc3F-HQYEZDajKOnBmAP_cDiFqNAuU6WFaE3x8AAP__.Ue8Y9olvbo0utJ2P-zJ24n9WwdFIP1UfURH6tTVOPXkpZ0H52ACha3HctzEKAYA7n-5MvKaQwjN0XFb-J1-Q1A";
    ArrayList<File> list;
    private File file;
    private LoadingDialog dialog;


    /**
     * 身份证id校验
     * fileCategory  F   前   R后
     */
    public void CalibrateIdCard(final Context context, final String fileCategory, final String path, final IResultMsgTwo<CalibrateIdCardBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();

        file = new File(path);
        OkGo.<String>post("https://ai.msxf.com/apiocr/out/idcard")
                .tag(this)
                .params("token", TOKEN)
                .params("fileCategory", fileCategory)
                .params("fileData", file)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();
                        String json = response.body().toString();

                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            if (code.equals("200")) {
                                CalibrateIdCardBean calibrateIdCardBean = GsonUtils.GsonToBean(json, CalibrateIdCardBean.class);
                                if (fileCategory.equals("F")) {
                                    iResultMsg.Result(calibrateIdCardBean, 0, path);
                                } else {
                                    iResultMsg.Result(calibrateIdCardBean, 1, path);
                                }
                            } else {
                                showToast(context,message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if(dialog.isShowing())
                            dialog.dismissLoading();
                        iResultMsg.Error(response.body(),0);
                    }
                });
    }

    public interface IResultMsg<T> {
        void Result(T bean);

        void Error(String json);
    }

    public interface IResultMsgOne<T> {
        void Result(T bean, int flag);

        void Error(String json, int flag);
    }

    public interface IResultMsgTwo<T> {
        void Result(T bean, int flag, String path);

        void Error(String json, int flag);
    }

    public  void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
