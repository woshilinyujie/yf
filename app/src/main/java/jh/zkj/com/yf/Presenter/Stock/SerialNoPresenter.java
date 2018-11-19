package jh.zkj.com.yf.Presenter.Stock;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Bean.SerialNoBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Stock.SerialNoContract;
import jh.zkj.com.yf.Fragment.Stock.StockSerialNoFragment;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.StockFilterPopup;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use
 */
public class SerialNoPresenter implements SerialNoContract.ISerialNoPresenter {


    private StockSerialNoFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private Adapter adapter;
    //筛选popup
    private StockFilterPopup popup;
    private LinearLayout historyLayout;
    private LinearLayout history;
    private StockAPI api;

    public SerialNoPresenter(StockSerialNoFragment fragment) {
        this.fragment = fragment;
        activity = (MainActivity) fragment.getActivity();
        initPresenter();
    }

    private void initPresenter() {

        initView();
        initData();
        initListener();

    }

    private void initView() {
        popup = new StockFilterPopup(activity);
        recycler = fragment.getRecyclerView();
        historyLayout = fragment.getHistoryLayout();
        history = fragment.getHistory();
    }

    private void initData() {
        initRecyclerAdapter();
        initHistory();
        initListener();

        api = new StockAPI(activity);
        getSerialNoList("");
    }

    private void initHistory() {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - DpUtils.dip2px(activity, 16f) * 2;         // 屏幕宽度（像素）

        String s[] = {"11", "123", "4321", "asdfg", "zxcvbn", "你", "宛如少女的猫", "我并不想加班", "阿斯顿发生大发发士大夫撒打算的"};
        LinearLayout linear = null;
        int pix = 0;
        for (int i = 0; i < s.length; i++) {
            if (history.getChildCount() > 2) {
                break;
            }
            TextView tv = new TextView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, DpUtils.dip2px(activity, 10f), 0);
            tv.setLayoutParams(params);
            tv.setSingleLine();
            tv.setTextColor(0xff333333);
            tv.setTextSize(14);
            tv.setPadding(DpUtils.dip2px(activity, 14f), DpUtils.dip2px(activity, 9f)
                    , DpUtils.dip2px(activity, 14f), DpUtils.dip2px(activity, 9f));

            tv.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
            tv.setText(s[i]);
            if (linear == null) {
                linear = new LinearLayout(activity);
                linear.setOrientation(LinearLayout.HORIZONTAL);
                linear.setPadding(0, 0, 0, DpUtils.dip2px(activity, 10f));
                history.addView(linear);
            }
            linear.addView(tv);

            if (linear.getChildCount() > 0) {
                TextView childAt = (TextView) linear.getChildAt(linear.getChildCount() - 1);
                childAt.measure(0, 0);
                pix += childAt.getMeasuredWidth() + DpUtils.dip2px(activity, 10f);
                if (linear.getChildCount() > 1) {
                    if (pix > width) {
                        linear.removeView(childAt);

                        pix = childAt.getMeasuredWidth() + DpUtils.dip2px(activity, 10f);
                        linear = new LinearLayout(activity);
                        linear.setOrientation(LinearLayout.HORIZONTAL);
                        linear.setPadding(0, 0, 0, DpUtils.dip2px(activity, 10f));
                        linear.addView(tv);
                        history.addView(linear);
                    }
                }
            }
        }

    }

    private void initListener() {
        popup.setListener(new StockFilterPopup.Listener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    //公司
                    case StockFilterPopup.CLICK_TYPE_COMPANY: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "公司");
                        activity.startActivity(intent);
                        break;
                    }
                    //仓库
                    case StockFilterPopup.CLICK_TYPE_WAREHOUSE: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "仓库");
                        activity.startActivity(intent);
                        break;
                    }
                    //商品分类
                    case StockFilterPopup.CLICK_TYPE_CLASSIFICATION: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "商品分类");
                        activity.startActivity(intent);
                        break;
                    }
                    //品牌
                    case StockFilterPopup.CLICK_TYPE_BRAND: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "品牌");
                        activity.startActivity(intent);
                        break;
                    }
                    //型号
                    case StockFilterPopup.CLICK_TYPE_MODEL: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "型号");
                        activity.startActivity(intent);
                        break;
                    }
                    //重置
                    case StockFilterPopup.CLICK_TYPE_RESET: {
                        popup.reset();
                        break;
                    }
                    //确认
                    case StockFilterPopup.CLICK_TYPE_CONFIRM: {
                        popup.dismiss();
                        break;
                    }

                }
            }
        });
    }

    //recyclerView兼容跟多形式的嵌套布局 相比listview来说坑会少一些 方便后期维护
    private void initRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<SerialNoBean.RecordsBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<SerialNoBean.RecordsBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_number, parent, false);
            return new ViewHolder(view);
        }

        public SerialNoBean.RecordsBean getItem(int position) {
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
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            SerialNoBean.RecordsBean item = getItem(position);
            if(item != null){
                holder.name.setText(item.getFullName());
                holder.serialNo.setText(item.getSerialNo());
                holder.warehouse.setText(item.getWarehouseName());
                holder.purchasePrice.setText(item.getStockPrice());
                holder.costPrice.setText(item.getStockPrice());
                holder.totalLibraryAge.setText(item.getCurrentStockAge());
                holder.nowLibraryAge.setText(item.getStockAge());
                holder.transcendLibraryAge.setText(item.getExceedStockAge());

                holder.orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(fragment.getContext(), RetailOrderActivity.class);
                        fragment.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //下单
            @BindView(R.id.stock_number_order_btn)
            View orderBtn;
            //序列号
            @BindView(R.id.stock_number_order)
            TextView serialNo;
            //仓库
            @BindView(R.id.stock_number_order_status)
            TextView warehouse;
            //商品名称
            @BindView(R.id.stock_number_name)
            TextView name;
            //采购价
            @BindView(R.id.stock_number_order_title)
            TextView purchasePrice;
            //成本价
            @BindView(R.id.stock_number_order_title1)
            TextView costPrice;
            //总库龄
            @BindView(R.id.stock_number_date)
            TextView totalLibraryAge;
            //当前库龄
            @BindView(R.id.stock_number_date1)
            TextView nowLibraryAge;
            //超库龄天数
            @BindView(R.id.stock_number_date2)
            TextView transcendLibraryAge;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    //**********************************************************************************************

    public void getSerialNoList(String keywords) {
        api.getSerialNoList(keywords, new OrderAPI.IResultMsg<SerialNoBean>() {
            @Override
            public void Result(SerialNoBean bean) {
                if (bean != null) {
                    ArrayList<SerialNoBean.RecordsBean> records = bean.getRecords();
                    if(records != null && records.size() > 0){
                        adapter.notifyData(records);
                        historyLayout.setVisibility(View.GONE);
                    }else{
                        historyLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
