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
import jh.zkj.com.yf.Bean.CRMCalibrateBean;
import jh.zkj.com.yf.Bean.CRMInfoBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardTokenBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardTokeupBean;
import jh.zkj.com.yf.Bean.CheckLoginBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.CompanyBean;
import jh.zkj.com.yf.Bean.EntExamineListBean;
import jh.zkj.com.yf.Bean.JoinCompanyBean;
import jh.zkj.com.yf.Bean.JoinCompanyUpBean;
import jh.zkj.com.yf.Bean.LoginCRMBean;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Bean.RegisterBean;
import jh.zkj.com.yf.Bean.RegisterNextBean;
import jh.zkj.com.yf.Bean.RegisterUpBean;
import jh.zkj.com.yf.Bean.SendCodeBean;
import jh.zkj.com.yf.Bean.SendCodeNextBean;
import jh.zkj.com.yf.Bean.SendRegisterCodeNextBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;

/**
 * Created by linyujie on 18/11/1.
 */

public class MyAPI {
    public final String API = "http://192.168.68.12";
    private File file;
    private LoadingDialog dialog;



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
        OkGo.<String>get(API+":3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtY3JtOmpoLWNybQ==")
                .headers("device", "android")
                .params("grant_type", "password")
                .params("username", "jh-crm_android_" + phone)
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
     * crm 验证码登录前校验
     * @param context
     * @param phone
     * @param iResultMsg
     */
    public void loginCRMCalibrate(final Context context, String phone , final IResultMsg<CRMCalibrateBean> iResultMsg){
        OkGo.<String>get(API+":3001/crm/crmCompany/smslogin/valid").tag(context)
                .params("phone",phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s = response.body().toString();
                        CRMCalibrateBean crmCalibrateBean = GsonUtils.GsonToBean(s, CRMCalibrateBean.class);
                        if(crmCalibrateBean.getCode()==0){
                            iResultMsg.Result(crmCalibrateBean);
                        }else{
                            showToast(context,crmCalibrateBean.getMsg());
                        }
                    }
                });
    }



    /**
     * 登录CRM  验证码登录
     * Authorization 固定值
     * grant_type  固定值
     * scope       固定值
     */
    public void loginCRMCode(Context context, String phone, String smsCode,final IResultMsg<LoginCRMBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(API+":3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtY3JtOmpoLWNybQ==")
                .headers("device", "android")
                .headers("smsCode", "true")
                .params("grant_type", "password")
                .params("username", "jh-crm_code_android_" + phone)
                .params("password", "123456")
                .params("scope", "server")
                .params("smsCode", smsCode)
                .params("smsPhone", phone)
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
        OkGo.<String>get(API+":9001/stdUser/company").tag(context)
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
     * 登入ERP  密码登录
     * Authorization  固定值
     * grant_type   固定值
     * usernameType  登录CRM productsType字段获取
     * password  登录CRM  password 字段获得
     */
    public void loginERP(Context context, String usernameType, String username, String password, String companyCode,final IResultMsg<LoginERPBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(API+":3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtZXJwLTNjOmpoLWVycC0zYw==")
                .headers("device", "android")
                .params("grant_type", "password")
                .params("username", usernameType + "_" + "android" + "_" + username+"_"+companyCode)
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
     * 登入ERP  验证码登录
     * Authorization  固定值
     * grant_type   固定值
     * usernameType  登录CRM productsType字段获取
     * password  登录CRM  password 字段获得
     */
    public void loginERPCode(Context context, String usernameType, String username, String password, String companyCode,String smsCode,final IResultMsg<LoginERPBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(API+":3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtZXJwLTNjOmpoLWVycC0zYw==")
                .headers("device", "android")
                .headers("smsCode", "true")
                .params("grant_type", "password")
                .params("username", usernameType + "_" + "code_android" + "_" + username+"_"+companyCode)
                .params("password", password)
                .params("scope", "server")
                .params("smsCode", smsCode)
                .params("smsPhone", username)
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
     * 获取上传照片所需的token
     *
     * @param context
     */
    public void CalibrateIdCardToke(final Context context, final IResultMsg<CalibrateIdCardTokenBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        CalibrateIdCardTokeupBean upbean = new CalibrateIdCardTokeupBean();
        upbean.setApp_key("platform");
        upbean.setApplication_type("101");
        upbean.setApp_secret("123456");
        upbean.setApply_no("2816c1948b634a24a448ba3f16c94c3e");
        upbean.setData(new CalibrateIdCardTokeupBean.DataBean());
        String s1 = GsonUtils.GsonString(upbean);
        OkGo.<String>post("https://ai.msxf.com/apply").tag(context)
                .headers("Content-Type", "application/json")
                .upJson(s1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s = response.body().toString();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            if (code.equals("200")) {
                                CalibrateIdCardTokenBean bean = GsonUtils.GsonToBean(s, CalibrateIdCardTokenBean.class);
                                iResultMsg.Result(bean);
                            } else {
                                showToast(context, message);
                                if (dialog.isShowing())
                                    dialog.dismissLoading();
                            }
                        } catch (JSONException e) {
                            if (dialog.isShowing())
                                dialog.dismissLoading();
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
     * 身份证id校验
     * fileCategory  F   前   R后
     */
    public void CalibrateIdCard(final Context context, final String fileCategory, final String path, String token, final IResultMsgTwo<CalibrateIdCardBean> iResultMsg) {

        file = new File(path);
        OkGo.<String>post("https://ai.msxf.com/apiocr/out/idcard")
                .tag(context)
                .params("token", token)
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
     * @param businessCode    统一社会信用码
     * @param description     公司名称
     * @param address         详细地址
     * @param legalPerson     法人
     * @param contactPerson   联系人
     * @param contactPhone    联系号码
     * @param zipCode         邮编
     * @param businessLicense 营业执照
     * @param productType     jh-erp-3c
     * @param regionFullName  省市区全拼
     * @param password        密码
     * @param phone           手机号码
     */
    public void Register(final Context context, String businessCode, String description
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
        OkGo.<String>post(API+":3001/crm/crmCompany/company/register").tag(context)
                .upJson(slist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            RegisterBean registerBean = GsonUtils.GsonToBean(s, RegisterBean.class);
                            if (registerBean.getCode() == 0) {
                                iResultMsg.Result(registerBean);
                            } else {
                                showToast(context, registerBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                    }
                });
    }

    /**
     * 注册发送验证码
     *
     * @param context
     * @param phone
     * @param iResultMsg
     */
    public void sendRegisterCode(final Context context, String phone, final IResultMsg<SendCodeBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(API+":3001/mobileCode").tag(context)
                .params("mobile", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            SendCodeBean sendCodeBean = GsonUtils.GsonToBean(s, SendCodeBean.class);
                            if (sendCodeBean.getMsg().equals("发送成功")) {
                                iResultMsg.Result(sendCodeBean);
                                showToast(context, "验证码已发送到您的手机");
                            } else {
                                showToast(context, sendCodeBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
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
     * 注册发送验证码后下一步
     *
     * @param context
     * @param phone
     * @param iResultMsg
     */
    public void sendRegisterCodeNext(final Context context, String smsCode, String phone, final IResultMsg<SendRegisterCodeNextBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        SendCodeNextBean bean = new SendCodeNextBean();
        bean.setMobilePhone(phone);
        String s1 = GsonUtils.GsonString(bean);
        OkGo.<String>post(API+":3001/crm/stdUser/beforejoincompanyvalid?smsCode=" + smsCode + "&smsPhone=" + phone).tag(context)
                .headers("Content-Type", "application/json")
                .headers("smsCode", "true")
                .upJson(s1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            SendRegisterCodeNextBean sendRegisterCodeNextBean = GsonUtils.GsonToBean(s, SendRegisterCodeNextBean.class);
                            if (sendRegisterCodeNextBean.getCode() == 0) {
                                iResultMsg.Result(sendRegisterCodeNextBean);
                            } else {
                                showToast(context, sendRegisterCodeNextBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
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
     * @param phone
     * @param password
     * @param confirmPassword 确认密码
     * @param code            公司代码
     * @param name            姓名
     * @param username        登录名
     * @param identNo         身份证
     * @param sex
     * @param regionFullName  所在地区
     * @param identAddress    详细地址
     * @param identImgFront
     * @param identImgBack
     * @param iResultMsg
     */
    public void joinCompanySave(final Context context, String phone, String password, String confirmPassword
            , String code, String name, String username, String identNo, String sex, String regionFullName, String identAddress
            , String identImgFront, String identImgBack, final IResultMsg<JoinCompanyBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        final JoinCompanyUpBean bean = new JoinCompanyUpBean();
        bean.setMobilephone(phone);
        bean.setPassword(password);
        bean.setConfirmPassword(confirmPassword);
        bean.setCode(code);
        bean.setName(name);
        bean.setUsername(username);
        bean.setIdentNo(identNo);
        bean.setSex(sex);
        bean.setRegionFullName(regionFullName);
        bean.setIdentAddress(identAddress);
        bean.setIdentImgFront(identImgFront);
        bean.setIdentImgBack(identImgBack);
        String s1 = GsonUtils.GsonString(bean);
        OkGo.<String>post(API+":3001/crm/stdUser/joincompany").tag(context)
                .upJson(s1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            JoinCompanyBean bean1 = GsonUtils.GsonToBean(s, JoinCompanyBean.class);
                            if (bean1.getCode() == 0) {
                                iResultMsg.Result(bean1);
                            } else {
                                showToast(context, bean1.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
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
