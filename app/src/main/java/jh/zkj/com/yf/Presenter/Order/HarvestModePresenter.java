package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.HarvestModeActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Bean.HarvestModeBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Contract.Order.HarvestModeContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/31
 * use
 */
public class HarvestModePresenter implements HarvestModeContract.IHarvestModePresenter {

    private HarvestModeActivity activity;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private OrderAPI api;
    DecimalFormat dFormat = new DecimalFormat("#.##");
    private OrderDetailsBean orderBean;
    private String status;
    //总金额
    private String total = "0";
    private HarvestModeBean topBean;
    private ArrayList<HarvestModeBean> modeList = new ArrayList<>();
    private ArrayList<HarvestModeBean> modeBean = new ArrayList<>();

    public HarvestModePresenter(HarvestModeActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        recyclerView = activity.getRecyclerView();
    }

    private void initData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        api = new OrderAPI();

        orderBean = (OrderDetailsBean) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_BEAN);
        status = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS);
        total = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL);
        modeList = (ArrayList<HarvestModeBean>) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_HARVEST_MODE);

        activity.setMoney(total);
        getCashierType("/" + orderBean.getBillNo());

    }

    private void initListener() {
        activity.getTitleRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ArrayList<HarvestModeBean> arr = adapter.getmArr();
                if(arr != null){
                    arr.add(0, topBean);
                }
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_HARVEST_MODE, arr);
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
            }
        });
    }

    private void refreshTopItem() {
        if(topBean != null){

            BigDecimal bigDecimal = new BigDecimal("0");
            BigDecimal totalDecimal = new BigDecimal(total);
            for (HarvestModeBean bean : modeBean){
                BigDecimal bigDecimal1 = new BigDecimal(bean.getMoney());
                bigDecimal = bigDecimal.add(bigDecimal1);
            }
            double v = totalDecimal.subtract(bigDecimal).doubleValue();
            String format = dFormat.format(v);
            topBean.setMoney(format);

            activity.setEtMoney(topBean.getMoney());
        }
    }


    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private Bitmap blue;
        private Bitmap gray;

        private boolean ischange = true;

        private ArrayList<HarvestModeBean> mArr = new ArrayList<>();

        public Adapter(){
            Resources res= activity.getResources();
            blue = BitmapFactory.decodeResource(res, R.mipmap.select_blue);
            gray = BitmapFactory.decodeResource(res, R.mipmap.select_gray);
        }

        //后期传入刷新
        public void notifyData(ArrayList<HarvestModeBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        public ArrayList<HarvestModeBean> getmArr(){
            return mArr;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_harvest_mode, parent, false);
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
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            final HarvestModeBean item = getItem(position);
            if(item != null){
                ischange = true;

                holder.modeCashIcon.setVisibility(View.GONE);
                holder.modeIcon.setVisibility(View.VISIBLE);
                holder.modeText.setText(item.getCashierTypeName());
                holder.money.setEnabled(true);
                if(TextUtils.isEmpty(item.getMoney())){
                    holder.select.setImageBitmap(gray);
                    holder.money.setText("");
                }else{
                    if(Double.valueOf(item.getMoney()) != 0){
                        holder.select.setImageBitmap(blue);
                        holder.money.setText(item.getMoney());
                    }else{
                        holder.select.setImageBitmap(gray);
                        holder.money.setText("");
                    }
                }

                ischange = false;

                holder.money.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!ischange){
                            if(!TextUtils.isEmpty(s.toString())){
                                //每次都要遍历集合进行计算
                                item.setMoney(s.toString());
                                holder.select.setImageBitmap(blue);
                            }else{
                                holder.select.setImageBitmap(gray);
                                item.setMoney("0");
                            }

//                            BigDecimal bigDecimal = new BigDecimal("0");
////                            BigDecimal totalDecimal = new BigDecimal(total);
////                            for (HarvestModeBean bean : mArr){
////                                BigDecimal bigDecimal1 = new BigDecimal(bean.getMoney());
////                                bigDecimal = bigDecimal.add(bigDecimal1);
////                            }
////                            double v = totalDecimal.subtract(bigDecimal).doubleValue();
////                            String format = dFormat.format(v);
////                            topBean.setMoney(format);
                            refreshTopItem();
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

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

            @BindView(R.id.harvest_mode_cash_icon)
            ImageView modeCashIcon;
            @BindView(R.id.harvest_mode_icon)
            ImageView modeIcon;
            @BindView(R.id.harvest_mode_text)
            TextView modeText;
            @BindView(R.id.harvest_mode_select)
            ImageView select;
            @BindView(R.id.harvest_mode_money_et)
            EditText money;
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }

    //******************************************************************************************
    //获取收款方式
    public void getCashierType(String orderId) {
        api.getCashierType(orderId, new OrderAPI.IResultMsg<ArrayList<HarvestModeBean>>() {
            @Override
            public void Result(ArrayList<HarvestModeBean> bean) {
                modeBean = bean;
                if(bean.size() > 0){
                    topBean = bean.remove(0);
                    topBean.setMoney(total);
                }

                if(modeList != null){
                    for (int i = 0 ; i < modeList.size(); i++){
                        for (int j = 0 ; j < bean.size(); j++){
                            if(modeList.get(i).getCashierTypeName().equals(bean.get(j).getCashierTypeName())){
                                bean.get(j).setMoney(modeList.get(i).getMoney());
                                break;
                            }
                        }
                    }
                }
                refreshTopItem();
                adapter.notifyData(bean);
            }

            @Override
            public void Error(String json) {

            }
        });
    }

}
