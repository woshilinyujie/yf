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
import jh.zkj.com.yf.Bean.CRMPassWordBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardTokenBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardTokeupBean;
import jh.zkj.com.yf.Bean.CheckLoginBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.CompanyBean;
import jh.zkj.com.yf.Bean.EntExamineListBean;
import jh.zkj.com.yf.Bean.ForgetCRMPassWordBean;
import jh.zkj.com.yf.Bean.ForgetupBean;
import jh.zkj.com.yf.Bean.JoinCompanyBean;
import jh.zkj.com.yf.Bean.JoinCompanyUpBean;
import jh.zkj.com.yf.Bean.LoginCRMBean;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Bean.ModifyCRMHeadUpBean;
import jh.zkj.com.yf.Bean.ModifyCRMNameBean;
import jh.zkj.com.yf.Bean.ModifyCRMNameUpBean;
import jh.zkj.com.yf.Bean.ModifyPasswordBean;
import jh.zkj.com.yf.Bean.ModifyPhoneBean;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Bean.RegisterBean;
import jh.zkj.com.yf.Bean.RegisterNextBean;
import jh.zkj.com.yf.Bean.RegisterUpBean;
import jh.zkj.com.yf.Bean.SendCodeBean;
import jh.zkj.com.yf.Bean.SendCodeNextBean;
import jh.zkj.com.yf.Bean.SendRegisterCodeNextBean;
import jh.zkj.com.yf.Bean.UpFileBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.Toast.MToast;

/**
 * Created by linyujie on 18/11/1.
 */

public class MyAPI {
//    public final String API = APIConstant.API;
    private File file;
    private LoadingDialog dialog;


    /**
     * 登录CRM
     * Authorization 固定值
     * grant_type  固定值
     * scope       固定值
     */
    public void loginCRM(final Context context, String phone, String password, final IResultMsg<LoginCRMBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/auth/oauth/token").tag(context)
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
                        try {
                            LoginCRMBean loginCRMBean = JSON.parseObject(josn, LoginCRMBean.class);
                            if (loginCRMBean.getAccess_token() != null) {
                                iResultMsg.Result(loginCRMBean);
                            } else {
                                showToast(context, loginCRMBean.getMsg());
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
     * crm 验证码登录前校验
     *
     * @param context
     * @param phone
     * @param iResultMsg
     */
    public void loginCRMCalibrate(final Context context, String phone, final IResultMsg<CRMCalibrateBean> iResultMsg) {
        OkGo.<String>get(APIConstant.API + ":3001/crm/crmCompany/smslogin/valid").tag(context)
                .params("phone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s = response.body().toString();
                        try {
                            CRMCalibrateBean crmCalibrateBean = GsonUtils.GsonToBean(s, CRMCalibrateBean.class);
                            if (crmCalibrateBean.getCode() == 0 && crmCalibrateBean.getData() != null) {
                                iResultMsg.Result(crmCalibrateBean);
                            } else {
                                showToast(context, crmCalibrateBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
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
    public void loginCRMCode(final Context context, String phone, String smsCode, final IResultMsg<LoginCRMBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/auth/oauth/token").tag(context)
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
                        try {
                            LoginCRMBean loginCRMBean = GsonUtils.GsonToBean(josn, LoginCRMBean.class);
                            if (loginCRMBean.getAccess_token() != null) {
                                iResultMsg.Result(loginCRMBean);
                            } else {
                                showToast(context, loginCRMBean.getMsg());
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
     * crm设置密码
     *
     * @param context
     * @param password
     */
    public void CRMPassWord(final Context context, String password, final IResultMsg<CRMPassWordBean> iResultMsg) {
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        OkGo.<String>get(APIConstant.API + ":3001/crm/crmCompany/set/password").tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .params("password", password)
                .params("confirmPassword", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s = response.body().toString();
                        CRMPassWordBean crmInfoBean = GsonUtils.GsonToBean(s, CRMPassWordBean.class);
                        if (crmInfoBean.getCode() == 0 && crmInfoBean.isData()) {
                            iResultMsg.Result(crmInfoBean);
                        } else {
                            showToast(context, crmInfoBean.getMsg());
                        }
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
    public void loginERP(final Context context, String usernameType, String username, String password, String companyCode, final IResultMsg<LoginERPBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtZXJwLTNjOmpoLWVycC0zYw==")
                .headers("device", "android")
                .params("grant_type", "password")
                .params("username", usernameType + "_" + "android" + "_" + username + "_" + companyCode)
                .params("password", password)
                .params("scope", "server")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String json = response.body().toString();
                        try {
                            LoginERPBean loginERPBean = GsonUtils.GsonToBean(json, LoginERPBean.class);
                            if (loginERPBean.getAccess_token() != null) {
                                iResultMsg.Result(loginERPBean);
                            } else {
                                showToast(context, loginERPBean.getMsg());
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
     * 登入ERP  验证码登录
     * Authorization  固定值
     * grant_type   固定值
     * usernameType  登录CRM productsType字段获取
     * password  登录CRM  password 字段获得
     */
    public void loginERPCode(final Context context, String usernameType, String username, String password, String companyCode, String smsCode, final IResultMsg<LoginERPBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/auth/oauth/token").tag(context)
                .headers("Authorization", "Basic amgtZXJwLTNjOmpoLWVycC0zYw==")
                .headers("device", "android")
                .headers("smsCode", "true")
                .params("grant_type", "password")
                .params("username", usernameType + "_" + "code_android" + "_" + username + "_" + companyCode)
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
                        try {
                            LoginERPBean loginERPBean = GsonUtils.GsonToBean(json, LoginERPBean.class);
                            if (loginERPBean.getAccess_token() != null) {
                                iResultMsg.Result(loginERPBean);
                            } else {
                                showToast(context, loginERPBean.getMsg());
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
     * 添加新企业
     *
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
        RegisterUpBean bean = new RegisterUpBean();
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
        bean.setBusinessLicense(businessLicense);
        bean.setProductType(productType);
        bean.setRegionFullName(regionFullName);
        bean.setIndustryCode("10001");
        String slist = GsonUtils.GsonString(bean);
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        OkGo.<String>post(APIConstant.API+ ":3001/crm/crmCompany/company/register").tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .upJson(slist)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body();
                        try {
                            RegisterBean registerBean = GsonUtils.GsonToBean(s, RegisterBean.class);
                            if (registerBean.getCode() == 0 && registerBean.getData() != null) {
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
        OkGo.<String>get(APIConstant.API+ ":3001/mobileCode").tag(context)
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
        OkGo.<String>post(APIConstant.API + ":3001/crm/stdUser/notoken/beforejoincompanyvalid?smsCode=" + smsCode + "&smsPhone=" + phone).tag(context)
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
                            if (sendRegisterCodeNextBean.getCode() == 0 && sendRegisterCodeNextBean.getData() != null) {
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
        OkGo.<String>post(APIConstant.API+ ":3001/crm/stdUser/notoken/joincompany").tag(context)
                .upJson(s1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            JoinCompanyBean bean1 = GsonUtils.GsonToBean(s, JoinCompanyBean.class);
                            if (bean1.getCode() == 0 && bean1.isData()) {
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
     * auditFlag 0 审核中  1已经拒接 和已审核
     */
    public void getEntExamineList(final Context context, String uuid, String keywords, String opreate, final IResultMsg<ArrayList<EntExamineListBean>> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        OkGo.<String>get(APIConstant.API + ":3001/" + HttpConstant.HTTP_CRM_STD_USER_APPLY + uuid).tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .params("keywords", keywords)
                .params("opreate", opreate)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        try {
                            BaseBean<ArrayList<EntExamineListBean>> bean = JSON.parseObject(response.body(),
                                    new TypeReference<BaseBean<ArrayList<EntExamineListBean>>>() {
                                    });

                            if ("0".equals(bean.getCode()) && bean.getData() != null) {
                                iResultMsg.Result(bean.getData());
                            } else {
                                showToast(context, bean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
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
    public void getCompanyInfo(final Context context, final IResultMsg<CompanyBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        OkGo.<String>get(APIConstant.API + ":3001/" + HttpConstant.HTTP_CRM_COMPANY_INFO).tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            BaseBean<CompanyBean> bean = JSON.parseObject(response.body(),
                                    new TypeReference<BaseBean<CompanyBean>>() {
                                    });

                            if ("0".equals(bean.getCode()) && bean.getData() != null) {
                                iResultMsg.Result(bean.getData());
                            } else {
                                showToast(context, bean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
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
    public void getOperratorAudit(final Context context, String uuid, boolean flag, final IResultMsg<String> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        OkGo.<String>get(APIConstant.API+ ":3001/"
                + (flag ? HttpConstant.HTTP_CRM_OPERATOR_AUDIT : HttpConstant.HTTP_CRM_OPERATOR_UN_AUDIT)
                + uuid)
                .tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();

                        BaseBean<String> bean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<String>>() {
                                });

                        if (bean.getCode().equals(APIConstant.REQUEST_SUCCESS)) {
                            iResultMsg.Result(bean.getData());
                        } else if (bean.getCode().equals("100008")) {
                            MToast.makeText(context, "该用户已经加入企业，请忽略该条申请", MToast.LENGTH_SHORT).show();
                            iResultMsg.Result(bean.getData());
                        } else {
                            MToast.makeText(context, bean.getMsg(), MToast.LENGTH_SHORT).show();
                        }
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
     * 改名
     *
     * @param context
     * @param name
     * @param iResultMsg
     */
    public void ModifyCRMName(final Context context, String name, final IResultMsg<ModifyCRMNameBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        ModifyCRMNameUpBean bean = new ModifyCRMNameUpBean();
        bean.setName(name);
        String s = GsonUtils.GsonString(bean);
        OkGo.<String>post(APIConstant.API+ ":3001/crm/stdUser/set/userInfo").tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .headers("Content-Type", "application/json")
                .upJson(s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iResultMsg.Error(response.toString());
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s1 = response.body().toString();
                        try {
                            ModifyCRMNameBean modifyCRMNameBean = GsonUtils.GsonToBean(s1, ModifyCRMNameBean.class);
                            if (modifyCRMNameBean.getCode() == 0 && modifyCRMNameBean.isData()) {
                                iResultMsg.Result(modifyCRMNameBean);
                            } else {
                                showToast(context, modifyCRMNameBean.getMsg());
                            }
                            iResultMsg.Result(modifyCRMNameBean);
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
                    }
                });
    }

    /**
     * crm改头像
     *
     * @param context
     * @param iResultMsg
     */
    public void ModifyCRMHead(final Context context, String data, final IResultMsg<ModifyCRMNameBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String crm_token = PrefUtils.getString(context, "crm_token", "");
        ModifyCRMHeadUpBean headUpBean = new ModifyCRMHeadUpBean();
        headUpBean.setHeadImg(data);
        String s = GsonUtils.GsonString(headUpBean);
        OkGo.<String>post(APIConstant.API+ ":3001/crm/stdUser/set/userInfo").tag(context)
                .headers("Authorization", "Bearer " + crm_token)
                .headers("Content-Type", "application/json")
                .upJson(s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s1 = response.body().toString();
                        ModifyCRMNameBean modifyCRMNameBean = GsonUtils.GsonToBean(s1, ModifyCRMNameBean.class);
                        if (modifyCRMNameBean.getCode() == 0 && modifyCRMNameBean.isData()) {
                            iResultMsg.Result(modifyCRMNameBean);
                        } else {
                            showToast(context, modifyCRMNameBean.getMsg());
                        }
                        iResultMsg.Result(modifyCRMNameBean);
                    }
                });
    }


    /**
     * 上传头像
     *
     * @param context
     * @param path
     * @param iResultMsg
     */
    public void upFile(final Context context, String path, final IResultMsg<UpFileBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        file = new File(path);
        OkGo.<String>post(APIConstant.API + ":3001/thirdparty/file/upload").tag(context)
                .params("file", file)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iResultMsg.Error(response.toString());
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            UpFileBean upFileBean = GsonUtils.GsonToBean(s, UpFileBean.class);
                            if (upFileBean.getCode() == 0 && upFileBean.getData() != null) {
                                iResultMsg.Result(upFileBean);
                            } else {
                                showToast(context, upFileBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
                    }
                });
    }

    /**
     * erp修改头像
     */
    public void modifyHead(final Context context, String path, final IResultMsg<ModifyPasswordBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String erp_token = PrefUtils.getString(context, "erp_token", "");
        OkGo.<String>get(APIConstant.API + ":3001/erp/basic/user/app/center/update/headImg")
                .params("access_token", erp_token)
                .params("headImg", path)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            ModifyPasswordBean modifyPasswordBean = GsonUtils.GsonToBean(s, ModifyPasswordBean.class);
                            if (modifyPasswordBean.getCode() == 0 && modifyPasswordBean.isData()) {
                                iResultMsg.Result(modifyPasswordBean);
                            } else {
                                showToast(context, modifyPasswordBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
                    }
                });
    }

    /**
     * erp修改密码
     *
     * @param context
     * @param oldPassword
     * @param newPassword
     * @param iResultMsg
     */
    public void modifyPassword(final Context context, String oldPassword, String newPassword, final IResultMsg<ModifyPasswordBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String erp_token = PrefUtils.getString(context, "erp_token", "");
        OkGo.<String>get(APIConstant.API+ ":3001/erp/basic/user/app/center/update/password")
                .params("access_token", erp_token)
                .params("oldPassword", oldPassword)
                .params("newPassword1", newPassword)
                .params("newPassword2", newPassword)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            ModifyPasswordBean modifyPasswordBean = GsonUtils.GsonToBean(s, ModifyPasswordBean.class);
                            if (modifyPasswordBean.getCode() == 0 && modifyPasswordBean.isData()) {
                                iResultMsg.Result(modifyPasswordBean);
                            } else {
                                showToast(context, modifyPasswordBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
                    }
                });
    }

    /**
     * erp修改用户名
     *
     * @param context
     * @param name
     * @param iResultMsg
     */
    public void modifyUserName(final Context context, String name, final IResultMsg<ModifyPasswordBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        String erp_token = PrefUtils.getString(context, "erp_token", "");
        OkGo.<String>get(APIConstant.API + ":3001/erp/basic/user/app/center/update/username")
                .params("access_token", erp_token)
                .params("username", name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            ModifyPasswordBean modifyPasswordBean = GsonUtils.GsonToBean(s, ModifyPasswordBean.class);
                            if (modifyPasswordBean.getCode() == 0 && modifyPasswordBean.isData()) {
                                iResultMsg.Result(modifyPasswordBean);
                            } else {
                                showToast(context, modifyPasswordBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
                    }
                });
    }


    /**
     * 修改手机号
     *
     * @param context
     * @param phone
     * @param code
     * @param iResultMsg
     */
    public void modifyPhone(final Context context, String phone, String code, final IResultMsg<ModifyPhoneBean> iResultMsg) {
        String erp_token = PrefUtils.getString(context, "erp_token", "");
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/erp/basic/user/app/center/update/mobilenum").tag(context)
                .headers("smsCode", "true")
                .params("access_token", erp_token)
                .params("smsCode", code)
                .params("mobileNum", phone)
                .params("smsPhone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            ModifyPhoneBean modifyPhoneBean = GsonUtils.GsonToBean(s, ModifyPhoneBean.class);
                            if (modifyPhoneBean.getCode() == 0 && modifyPhoneBean.isData()) {
                                iResultMsg.Result(modifyPhoneBean);
                            } else {
                                showToast(context, modifyPhoneBean.getMsg());
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
     * 我的信息
     *
     * @param context
     * @param iResultMsg
     */
    public void getMyInfo(final Context context, final IResultMsg<MyBean> iResultMsg) {
        String erp_token = PrefUtils.getString(context, "erp_token", "");
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/erp/basic/user/erploginuser").tag(context)
                .params("access_token", erp_token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String s = response.body().toString();
                        try {
                            MyBean myBean = GsonUtils.GsonToBean(s, MyBean.class);
                            if (myBean.getCode() == 0 && myBean.getData() != null) {
                                iResultMsg.Result(myBean);
                                PrefUtils.putString(context, "erp_json", s);
                            } else {
                                showToast(context, myBean.getMsg());
                            }
                        } catch (Exception e) {
                            showToast(context, e.toString());
                        }
                    }
                });
    }

    /**
     * 忘记crm密码
     *
     * @param context
     * @param code
     * @param password
     * @param phone
     * @param iResultMsg
     */
    public void forgetCRMPassWord(final Context context, String code, String password, String phone, final IResultMsg<ForgetCRMPassWordBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        final ForgetupBean forgetupBean = new ForgetupBean();
        forgetupBean.setMobilePhone(phone);
        forgetupBean.setPassword(password);
        String s = GsonUtils.GsonString(forgetupBean);
        OkGo.<String>post(APIConstant.API + ":3001/crm/stdUser/notoken/sms/set/password?smsCode=" + code + "&smsPhone=" + phone).tag(context)
                .headers("smsCode", "true")
                .upJson(s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String json = response.body().toString();
                        try {
                            ForgetCRMPassWordBean bean = GsonUtils.GsonToBean(json, ForgetCRMPassWordBean.class);
                            if (bean.getCode() == 0) {
                                iResultMsg.Result(bean);
                            } else {
                                MToast.makeText(context, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            MToast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 忘记erp密码
     *
     * @param context
     * @param code
     * @param password
     * @param phone
     * @param iResultMsg
     */
    public void forgetErpPassWord(final Context context, String code, String password, String phone, String companyCode, final IResultMsg<ForgetCRMPassWordBean> iResultMsg) {
        if (dialog == null)
            dialog = new LoadingDialog(context);
        dialog.showLoading();
        OkGo.<String>get(APIConstant.API + ":3001/erp/basic/user/sms/set/password?smsCode=" + code + "&smsPhone=" + phone).tag(context)
                .headers("smsCode", "true")
                .params("password", password)
                .params("companyCode", companyCode)
                .params("phone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (dialog.isShowing())
                            dialog.dismissLoading();
                        String json = response.body().toString();
                        try {
                            ForgetCRMPassWordBean bean = GsonUtils.GsonToBean(json, ForgetCRMPassWordBean.class);
                            if (bean.getCode() == 0) {
                                iResultMsg.Result(bean);
                            } else {
                                MToast.makeText(context, bean.getMsg(), Toast.LENGTH_SHORT).show();
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

    public interface IResultMsgOne<T> {
        void Result(T bean, int flag);

        void Error(String json, int flag);
    }

    public interface IResultMsgTwo<T> {
        void Result(T bean, int flag, String path);

        void Error(String json, int flag);
    }

    public void showToast(Context context, String msg) {
        MToast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
