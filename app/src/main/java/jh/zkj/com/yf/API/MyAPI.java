package jh.zkj.com.yf.API;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.CRMInfoBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardBean;
import jh.zkj.com.yf.Bean.CheckLoginBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.CompanyBean;
import jh.zkj.com.yf.Bean.EntExamineListBean;
import jh.zkj.com.yf.Bean.LoginCRMBean;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Bean.RegisterBean;
import jh.zkj.com.yf.Bean.RegisterNextBean;
import jh.zkj.com.yf.Bean.RegisterUpBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;

/**
 * Created by linyujie on 18/11/1.
 */

public class MyAPI {
    public final String API = APIConstant.API;
    public final String TOKEN = "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9." +
            "eNocy00KgzAQQOG7zNpFxkx-9DISzQQiipKkpVB692bcfrz3hb1lmCGQ17xhSJGRRmemuDq0bG302hMqGKC-1h7eR2jpKmeHXKuceRFbKpc3F-HQYEZDajKOnBmAP_cDiFqNAuU6WFaE3x8AAP__." +
            "Ue8Y9olvbo0utJ2P-zJ24n9WwdFIP1UfURH6tTVOPXkpZ0H52ACha3HctzEKAYA7n-5MvKaQwjN0XFb-J1-Q1A";
    private File file;
    private LoadingDialog dialog;


    /**
     * 检查该公司是否已经可以登录
     * @param companyUUid  注册后公司返回的uuid
     * @param iResultMsg
     */
    public void checkLogin(Context context, String companyUUid, final IResultMsg<CheckLoginBean> iResultMsg){
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.77:9001/crmCompany/companyinit/"+ companyUUid)
        .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (dialog.isShowing())
                    dialog.dismissLoading();
                String s = response.body().toString();
                CheckLoginBean checkLoginBean = GsonUtils.GsonToBean(s, CheckLoginBean.class);
                iResultMsg.Result(checkLoginBean);
            }

            @Override
            public void onError(Response<String> response) {
                if (dialog.isShowing())
                    dialog.dismissLoading();
            }
        });

    }
    /**
     * 登录CRM
     * Authorization 固定值
     * grant_type  固定值
     * scope       固定值
     */
    public void loginCRM(Context context, String phone, String password, final IResultMsg<LoginCRMBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.77:3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtY3JtOmpoLWNybQ==")
                .headers("device", "android")
                .params("grant_type", "password")
                .params("username", "jh-crm_web_" + phone)
                .params("password", password)
                .params("scope", "server")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String josn = response.body().toString();
                        LoginCRMBean loginCRMBean = GsonUtils.GsonToBean(josn, LoginCRMBean.class);
                        iResultMsg.Result(loginCRMBean);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }


    /**
     * 获取crm信息
     * token 登录CRM  获取
     */
    public void getCRMInfo(Context context, String token, final IResultMsg<CRMInfoBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.77:9001/stdUser/company").tag(context)
                .headers("Authorization", "Bearer" + " " + token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String json = response.body().toString();
                        CRMInfoBean crmInfoBean = GsonUtils.GsonToBean(json, CRMInfoBean.class);
                        iResultMsg.Result(crmInfoBean);
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
     * 登入ERP
     * Authorization  固定值
     * grant_type   固定值
     * usernameType  登录CRM productsType字段获取
     * password  登录CRM  password 字段获得
     */
    public void loginERP(Context context, String usernameType, String username, String password, final IResultMsg<LoginERPBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.77:3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtZXJwLTNjOmpoLWVycC0zYw==")
                .headers("device", "android")
                .params("grant_type", "password")
                .params("username", usernameType + "_" + "android" + "_" + username)
                .params("password", password)
                .params("scope", "server")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String json = response.body().toString();
                        LoginERPBean loginERPBean = GsonUtils.GsonToBean(json, LoginERPBean.class);
                        iResultMsg.Result(loginERPBean);
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
     * 身份证id校验
     * fileCategory  F   前   R后
     */
    public void CalibrateIdCard(final Context context, final String fileCategory, final String path, final IResultMsgTwo<CalibrateIdCardBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();

        file = new File(path);
        OkGo.<String>post("https://ai.msxf.com/apiocr/out/idcard")
                .tag(context)
                .params("token", TOKEN)
                .params("fileCategory", fileCategory)
                .params("fileData", file)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
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
                                showToast(context, message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        iResultMsg.Error(response.body(), 0);
                    }
                });
    }

    /**
     * 注册校验验证码
     */
    public void RegisterNext(Context context, String phone, String code, final IResultMsg<RegisterNextBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.77:9001/crmCompany/register/valid").tag(context)
                .params("phone", phone)
                .params("code", code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        RegisterNextBean registerNextBean = GsonUtils.GsonToBean(s, RegisterNextBean.class);
                        iResultMsg.Result(registerNextBean);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        iResultMsg.Error(response.toString());
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }

    /**
     * @param businessCode       统一社会信用码
     * @param description 公司名称
     * @param address      详细地址
     * @param legalPerson        法人
     * @param contactPerson      联系人
     * @param contactPhone       联系号码
     * @param zipCode            邮编
     * @param businessLicense    营业执照
     * @param productType        jh-erp-3c
     * @param regionFullName     省市区全拼
     * @param password           密码
     * @param phone              手机号码
     */
    public void Register(Context context, String businessCode, String description
            , String address, String legalPerson, String contactPerson,
                         String contactPhone, String zipCode, String businessLicense
            , String productType, String regionFullName, String password, String phone, final IResultMsg<RegisterBean> iResultMsg) {
        RegisterUpBean bean=new RegisterUpBean();
        bean.setAddress(address);
        bean.setBusinessCode(businessCode);
        bean.setdescription(description);
        bean.setContactPerson(contactPerson);
        bean.setName(description);
        bean.setContactPhone(contactPhone);
        bean.setMobileNum(contactPhone);
        bean.setLegalPerson(legalPerson);
        bean.setPassword(password);
        bean.setPhone(phone);
        bean.setZipCode(zipCode);
        bean.setBusinessCode(businessCode);
        bean.setProductType(productType);
        bean.setRegionFullName(regionFullName);
        bean.setIndustryCode("10001");
        String slist = GsonUtils.GsonString(bean);
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>post(" http://192.168.68.12:3001/crm/crmCompany/company/register").tag(context)
                .headers("Authorization", "Bearer a1f8c65d-3e3d-4dae-a9a3-06eb8c8c4d48")
                .upJson(slist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        RegisterBean registerBean = GsonUtils.GsonToBean(s, RegisterBean.class);
                        iResultMsg.Result(registerBean);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }

    /**
     * 审核列表
     *  auditFlag 0 审核中  1已经拒接 和已审核
     */
    public void getEntExamineList(Context context, String uuid, String keywords, String opreate, final IResultMsg<ArrayList<EntExamineListBean>> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.12:3001/" + HttpConstant.HTTP_CRM_STD_USER_APPLY + uuid).tag(context)
                .headers("Authorization", "Bearer a1f8c65d-3e3d-4dae-a9a3-06eb8c8c4d48")
                .params("keywords", keywords)
                .params("opreate", opreate)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<ArrayList<EntExamineListBean>> bean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<EntExamineListBean>>>() {});

                        iResultMsg.Result(bean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        iResultMsg.Error(response.toString());
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }

    /**
     * 企业信息
     */
    public void getCompanyInfo(Context context, final IResultMsg<CompanyBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.12:3001/" + HttpConstant.HTTP_CRM_COMPANY_INFO).tag(context)
                .headers("Authorization", "Bearer a1f8c65d-3e3d-4dae-a9a3-06eb8c8c4d48")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<CompanyBean> bean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<CompanyBean>>() {});

                        iResultMsg.Result(bean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        iResultMsg.Error(response.toString());
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }

    /**
     * 加入企业审核
     */
    public void getOperratorAudit(Context context, String uuid, boolean flag, final IResultMsg<String> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get("http://192.168.68.12:3001/"
                + (flag ? HttpConstant.HTTP_CRM_OPERRATOR_AUDIT : HttpConstant.HTTP_CRM_OPERRATOR_UN_AUDIT)
                + uuid)
                .tag(context)
                .headers("Authorization", "Bearer a1f8c65d-3e3d-4dae-a9a3-06eb8c8c4d48")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<String> bean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<String>>() {});

                        iResultMsg.Result(bean.getData());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        iResultMsg.Error(response.toString());
                        if (dialog.isShowing())
                            dialog.dismissLoading();
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

    public void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
