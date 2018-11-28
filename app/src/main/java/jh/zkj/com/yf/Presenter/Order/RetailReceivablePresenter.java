package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Activity.Order.HarvestModeActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Bean.HarvestModeBean;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Contract.Order.RetailReceivableContract;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.CancelDialog;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/21
 * use
 */
public class RetailReceivablePresenter implements RetailReceivableContract.IRetailOrderPresenter {

    private final int TYPE_HARVEST_MODE = 1;

    private final RetailReceivableActivity activity;
    private RecyclerView recyclerView;
    private RetailReceivableAdapter adapter;

    private String remakeText = "";
    //收款状态
    private String status;
    //收款方式bean
    private ArrayList<HarvestModeBean> modeList = new ArrayList<>();
    //订单详情bean
    private OrderDetailsBean orderBean;
    //总金额
    private String total;
    private OrderAPI api;

    public RetailReceivablePresenter(RetailReceivableActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        activity.getTitleLayout().getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReceivableSuccess();
            }
        });
    }

    private void initData() {
        orderBean = (OrderDetailsBean) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_BEAN);
        status = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS);
        total = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL);

        initAdapter();
        api = new OrderAPI(activity);
        if(orderBean != null){
            setData();
        }else{
            String billNo = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER);
            getQueryOrder(billNo);
        }

    }

    private void setData() {
        activity.setOrder("订单编号：" + orderBean.getBillNo());
        if(OrderConfig.STATUS_UN_SUCCESS.equals(status)){
            activity.setOrderStatus("未收款");
        }else if (OrderConfig.STATUS_SUCCESS.equals(status)){
            activity.setOrderStatus("已收款");
        }else if (OrderConfig.STATUS_CANCEL.equals(status)){
            activity.setOrderStatus("已取消");
        }
        activity.setName(orderBean.getName());
        activity.setPhone(orderBean.getMobilePhone());
        if(orderBean.getDetailDTOList() != null && orderBean.getDetailDTOList().size() > 0){
            int count = 0;
            for(OrderDetailsBean.DetailDTOListBean DTObean : orderBean.getDetailDTOList()){
                count += DTObean.getQty();
            }
            activity.setNumber("共" + count + "件");
            activity.setOrderTitle(orderBean.getDetailDTOList().get(0).getSkuFullName());
        }
        activity.setUserName("下单人：" + orderBean.getCreateUserName());
        activity.setMoney(total);
        activity.setDate(orderBean.getBizDate());
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailReceivableAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = activity.getRecyclerView();
        activity.getTitleLayout().getLetfImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    @Override
    public void harvestMode() {
        Intent intent = new Intent(activity, HarvestModeActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_BEAN, orderBean);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_HARVEST_MODE, modeList);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL, total);
        activity.startActivityForResult(intent, TYPE_HARVEST_MODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TYPE_HARVEST_MODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    modeList = (ArrayList<HarvestModeBean>) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_HARVEST_MODE);
                    if(modeList != null && modeList.size() > 0){
                        int size = modeList.size();
                        for (int i = 0 ; i < size; i++){
                            for (int j = 0 ; j < modeList.size(); j++){
                                if(Double.valueOf(modeList.get(j).getAmount()) == 0){
                                    modeList.remove(j);
                                    break;
                                }
                            }
                        }

                        if(modeList.size() > 0){
                           activity.setHarvestMode("修改收款方式");
                        }
                        adapter.notifyData(modeList);
                        if(orderBean != null){
                            orderBean.setNewCashierList(modeList);
                        }
                    }
                }
            }
        }
    }


    /**
     * 使用：
     */
    class RetailReceivableAdapter extends RecyclerView.Adapter<RetailReceivableAdapter.ViewHolder> {
        private ArrayList<HarvestModeBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<HarvestModeBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_retail_receivable, null);
            return new ViewHolder(view);
        }

        public HarvestModeBean getItem(int position) {
            if (mArr != null && mArr.size() > position) {
                return mArr.get(position);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final HarvestModeBean item = getItem(position);
            if (item != null){
                holder.mode.setText("收款方式" + (position + 1));
                holder.modeText.setText(item.getCashierTypeName());
                holder.money.setText(item.getAmount());
                if(position == mArr.size() - 1){
                    holder.remakeLayout.setVisibility(View.VISIBLE);
                }else{
                    holder.remakeLayout.setVisibility(View.GONE);
                }

                holder.remake.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        remakeText = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) { }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.retail_receivable_mode)
            TextView mode;
            @BindView(R.id.retail_receivable_text)
            TextView modeText;
            @BindView(R.id.retail_receivable_money)
            TextView money;
            @BindView(R.id.retail_receivable_ps)
            EditText remake;
            @BindView(R.id.retail_receivable_remake_layout)
            ConstraintLayout remakeLayout;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    //**********************************************************************************************
    //查询详情
    public void getQueryOrder(final String orderNum) {

        api.getQueryOrder("/" + orderNum, new OrderAPI.IResultMsg<OrderDetailsBean>() {

            @Override
            public void Result(OrderDetailsBean bean) {
                if(bean != null){
                    orderBean = bean;
                    setData();
                }
            }

            @Override
            public void Error(String json) {
            }
        });
    }

    //提交订单
    public void getReceivableSuccess() {
        if(orderBean.getNewCashierList() == null || orderBean.getNewCashierList().size() == 0){
            MToast.makeText(activity, "请确认信息已全部正确填写 确认后将不可修改", MToast.LENGTH_SHORT).show();
            return;
        }

        orderBean.setRemark(remakeText);
        if(!TextUtils.isEmpty(orderBean.getUuid())){
            orderBean.setBizSoUuid(orderBean.getUuid());
            orderBean.setUuid(null);
        }
        orderBean.getMemberDTO().setCompanyUuid(orderBean.getCompanyUuid());
        orderBean.getMemberDTO().setMemberUuid(orderBean.getMemberUuid());
        orderBean.getMemberDTO().setName(orderBean.getName());
        orderBean.getMemberDTO().setMobilePhone(orderBean.getMobilePhone());
        orderBean.getMemberDTO().setSex(orderBean.getSex());
        orderBean.setCreateTime(null);
        orderBean.setUpdateTime(null);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        orderBean.setBizDate(simpleDateFormat.format(date));

        //所属公司
//        String erp_json = PrefUtils.getString(activity, "erp_json", "");
//        MyBean myBean = JSON.parseObject(erp_json, MyBean.class);
//        if(myBean != null && myBean.getData().getSysUser() != null){
//            orderBean.setAscriptionCompanyUuid(myBean.getData().getSysUser().getAscriptionCompanyUuid());
//        }

        final String json = JSON.toJSONString(orderBean);

        final CancelDialog dialog = new CancelDialog(activity);
        dialog.setCancleS("取消");
        dialog.setSureS("确定");
        dialog.setMsgS("请确认信息已全部正确填写\n确认后将不可修改");
        dialog.show();
        dialog.getSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                api.getReceivableSuccess(json, new OrderAPI.IResultMsg<String>() {
                    @Override
                    public void Result(String bean) {
                        activity.setResult(Activity.RESULT_OK);
                        activity.finish();
                    }

                    @Override
                    public void Error(String json) {
                    }
                });

                dialog.dismiss();

            }
        });
        dialog.getCancle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
