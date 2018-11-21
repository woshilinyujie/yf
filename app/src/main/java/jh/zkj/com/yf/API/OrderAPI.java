package jh.zkj.com.yf.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.ClientInfoBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.HarvestModeBean;
import jh.zkj.com.yf.Bean.HomeMenuBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Bean.OrderListBean;
import jh.zkj.com.yf.Bean.ReceivableTypeBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.Toast.EToast;

/**
 * Created by linyujie on 18/10/23.
 */

public class OrderAPI {
    public String API = APIConstant.API + ":3001/";
//    public final String TOKEN = "bearer 292f06ac-f530-4218-a991-b1440ebc3d17";
    public String TOKEN = "bearer 7cf3dac3-6f53-4a41-86a7-07f2ab75702e";

    public OrderAPI(Context context){
        TOKEN = "bearer " + PrefUtils.getString(context, "erp_token", "");
//        API = "http://192.168.68.128:3001/";
    }

    /**
     * 获取业务员信息
     * http://localhost:3001/erp/basic/user/list?access_token=73677450-0871-4b41-8059-c0e4aea6348d&pageNum=1&pageSize=50&name=吴&soClerkFlag=1
     */

    public void getSalesmanInfo(String name, int pageNum, int pageSize, final IResultMsg<SalesmanBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_USER_LIST)
                .headers("Authorization", TOKEN)
                .params("name", name)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .params("soClerkFlag", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<SalesmanBean> arrayListBaseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<SalesmanBean>>() {
                                });

                        if (APIConstant.REQUEST_SUCCESS.equals(arrayListBaseBean.getCode())) {
                            iResultMsg.Result(arrayListBaseBean.getData());
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

    /**
     * 搜索商品
     */
    public void getSearchCommodity(String keyWord, int pageNum, int pageSize, final IResultMsg<CommodityBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_PRODUCT_KEYWORDS)
                .headers("Authorization", TOKEN)
                .params("keywords", keyWord)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if (BuildConfig.DEBUG) {
                            Log.d("wdefer", "json == " + response.body());
                        }
                        BaseBean<CommodityBean> comInfoBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<CommodityBean>>() {});

                        if (APIConstant.REQUEST_SUCCESS.equals(comInfoBean.getCode())) {
                            iResultMsg.Result(comInfoBean.getData());
                        } else {

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (BuildConfig.DEBUG) {
                            Log.d("wdefer", "搜索商品 -->");
                        }
                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 获取会员的接口
     */
    public void getClientInfo(String keyWord, int pageNum, int pageSize, final IResultMsg<ClientInfoBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_MEMBER_INFO)
                .headers("Authorization", TOKEN)
                .params("keywords", keyWord)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .params("identNo", "")
                .params("cardNo", "")
                .params("mobilePhone", "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if (BuildConfig.DEBUG) {
                            Log.e("wdefer", "json == " + response.body());
                        }

                        BaseBean<ClientInfoBean> client = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ClientInfoBean>>() {
                                });

                        if (APIConstant.REQUEST_SUCCESS.equals(client.getCode())) {
                            iResultMsg.Result(client.getData());
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

    /**
     * 生成订单
     */
    public void getCreateOrder(String json, final IResultMsg<String> iResultMsg) {
        OkGo.<String>post(API + HttpConstant.HTTP_BASIC_SO_APP)
                .headers("Authorization", TOKEN)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if (BuildConfig.DEBUG) {
                            Log.d("okgo request json", response.body());
                        }

                        BaseBean<String> orderNum = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<String>>() {
                                });

                        if (APIConstant.REQUEST_SUCCESS.equals(orderNum.getCode())) {
                            iResultMsg.Result(orderNum.getData());
                        } else {
                            iResultMsg.Result(null);
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body());
                    }
                });
    }

    /**
     * 查询订单详情
     */
    public void getQueryOrder(String orderId, final IResultMsg<OrderDetailsBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_SO_APP + orderId)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (BuildConfig.DEBUG) {
                            Log.d("okgo request json", response.body());
                        }

                        BaseBean<OrderDetailsBean> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<OrderDetailsBean>>() {});

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

    /**
     * 我的订单
     * type  1.未收款 2.以收款 3.已取消
     */

    public void getMyOrderList(String type, String keywords, int pageNum, int pageSize, final int flag
            , String scope, final IResultMsgOne<OrderListBean> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_GET_ORDER_LIST)
                .headers("Authorization", TOKEN)
                .params("type", type)
                .params("pageNum", pageNum)
                .params("pageSize", pageSize)
                .params("keywords", keywords)
                .params("scope", scope)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        OrderListBean bean = GsonUtils.GsonToBean(response.body(), OrderListBean.class);
                        if (APIConstant.REQUEST_SUCCESS.equals(bean.getCode())) {
                            iResultMsg.Result(bean, flag);
                        } else {

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iResultMsg.Error(response.body(), flag);
                    }
                });

    }

    /**
     * 获取收款方式：
     */
    public void getCashierType(String orderId, final IResultMsg<ArrayList<HarvestModeBean>> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BASIC_GET_CASHIER_TYPE_COMPANY + orderId)
                .headers("Authorization", TOKEN)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<ArrayList<HarvestModeBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<HarvestModeBean>>>() {});
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

    /**
     * 确认收款
     */
    public void getReceivableSuccess(String json, final IResultMsg<String> iResultMsg) {
        OkGo.<String>post(API + HttpConstant.HTTP_BIZ_SO_OUT_APP)
                .headers("Authorization", TOKEN)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<String> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<String>>() {});
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

    /**
     * 取消订单
     */
    public void getCancelOrder(String billNo, String reason, final IResultMsg<String> iResultMsg) {
        OkGo.<String>delete(API + HttpConstant.HTTP_BIZ_SO_CANCEL_ORDER)
                .headers("Authorization", TOKEN)
                .isSpliceUrl(true)
                .params("reason", reason)
                .params("billNo", billNo)
//                .upJson(reason)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<String> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<String>>() {});
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


    /**
     * 获取收款方式详情：
     */
    public void getCashierTypeDetail(String uuid, String bizSoOutUuid, final IResultMsg<ArrayList<ReceivableTypeBean>> iResultMsg) {
        OkGo.<String>get(API + HttpConstant.HTTP_BIZ_SO_RECEIVABLE_DETAIL + uuid)
                .headers("Authorization", TOKEN)
                .params("bizSoOutUuid", bizSoOutUuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseBean<ArrayList<ReceivableTypeBean>> baseBean = JSON.parseObject(response.body(),
                                new TypeReference<BaseBean<ArrayList<ReceivableTypeBean>>>() {});
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

    public interface IResultMsgOne<T> {
        void Result(T bean, int flag);

        void Error(String json, int flag);
    }
}
