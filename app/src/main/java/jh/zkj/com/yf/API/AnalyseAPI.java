package jh.zkj.com.yf.API;

import android.content.Context;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import jh.zkj.com.yf.Bean.LineDataBean;
import jh.zkj.com.yf.Bean.ShopNameBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.Toast.MToast;

/**
 * Created by linyujie on 18/11/5.
 */

public class AnalyseAPI {
    public final String TOKEN="ec60a5db-b7cf-4577-bb8c-e21c36db290d";
    private LoadingDialog dialog;


    public void getShopName(final Context context, final IResultMsg<ShopNameBean> iResultMsg){
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.200:3001/erp/basic/data/company").tag(context)
               .params("access_token",TOKEN)
                .params("type","company")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        ShopNameBean shopNameBean = GsonUtils.GsonToBean(s, ShopNameBean.class);
                        if(shopNameBean.getCode()==0){
                            iResultMsg.Result(shopNameBean);
                        }else{
                            MToast.makeText(context,shopNameBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }
    /**
     *
     * @param companyCode  店铺代码
     * @param startDate
     * @param endDate
     * @param classifyUuid  商品分类
     * @param brandUuid     商品品牌
     * @param skuName       商品型号
     * @param type       qty销量  sale_amount销售额  profit利润
     */
    public void LineDate(final Context context, String type, String companyCode, String startDate, String endDate
    , String classifyUuid , String  brandUuid, String  skuName, String danjuType,final IResultMsg<LineDataBean> iResultMsg){
        if(classifyUuid.equals("请选择")){
            classifyUuid="";
        }
        if(brandUuid.equals("请选择")){
            brandUuid="";
        }
        if(skuName.equals("请选择")){
            skuName="";
        }
        if(danjuType.equals("全部")){
            danjuType="";
        }
        OkGo.<String>get("http://192.168.68.200:7001/analysis/graph/statistics").tag(context)
                .params("access_token",TOKEN)
                .params("companyCode",companyCode)
                .params("startDate",startDate)
                .params("endDate",endDate)
                .params("classifyUuid",classifyUuid)
                .params(" brandUuid",brandUuid)
                .params("skuName",skuName)
                .params("bizType",danjuType)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s = response.body().toString();
                        LineDataBean lineData = GsonUtils.GsonToBean(s, LineDataBean.class);
                        if(lineData.getCode()==0){
                            iResultMsg.Result(lineData);
                        }else{
                            MToast.makeText(context,lineData.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    public interface IResultMsg<T> {
        void Result(T bean);

        void Error(String json);
    }
}
