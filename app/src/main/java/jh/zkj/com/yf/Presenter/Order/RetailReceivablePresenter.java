package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.HarvestModeActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Bean.HarvestModeBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Contract.Order.RetailReceivableContract;
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

    private String status;
    private ArrayList<HarvestModeBean> modeList = new ArrayList<>();
    private OrderDetailsBean orderBean;
    private String total;

    public RetailReceivablePresenter(RetailReceivableActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        activity.getTitleLayout().getRigthText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getReceivableSuccess();
            }
        });
    }

    private void initData() {
        orderBean = (OrderDetailsBean) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_BEAN);
        status = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS);
        total = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL);

        if(orderBean != null){
            activity.setOrder(orderBean.getBillNo());
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
                activity.setNumber("共" + orderBean.getDetailDTOList().size() + "件");
                activity.setOrderTitle(orderBean.getDetailDTOList().get(0).getSkuFullName());
            }
            activity.setUserName(orderBean.getCreateUserName());
            activity.setMoney(total);
            activity.setDate(orderBean.getBizDate());
        }

        initAdapter();
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
                                if(Double.valueOf(modeList.get(j).getMoney()) == 0){
                                    modeList.remove(j);
                                    break;
                                }
                            }
                        }

                        if(modeList.size() > 0){
                           activity.setHarvestMode("修改收款方式");
                        }
                        adapter.notifyData(modeList);
                    }
                }
            }
        }
    }

//    public void getReceivableSuccess() {
//        OrderAPI api = new OrderAPI();
//        api.getReceivableSuccess(orderBean, new OrderAPI.IResultMsg<ArrayList<HarvestModeBean>>() {
//            @Override
//            public void Result(ArrayList<HarvestModeBean> bean) {
//
//            }
//
//            @Override
//            public void Error(String json) {
//
//            }
//        });
//    }

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
                holder.money.setText(item.getMoney());
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
}
