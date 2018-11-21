package jh.zkj.com.yf.Presenter.Stock;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Bean.SerialNoTrackBean;
import jh.zkj.com.yf.Contract.Stock.SerialNoTrackContract;
import jh.zkj.com.yf.Fragment.Stock.SerialNoTrackFragment;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.StockFilterPopup;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SerialNoTrackPresenter implements SerialNoTrackContract.ISerialNoTrackPresenter {

    private SerialNoTrackFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private Adapter adapter;
    //筛选popup
    private StockFilterPopup popup;
    private StockAPI api;
    private LinearLayout historyLayout;
    private LinearLayout history;

    public SerialNoTrackPresenter(SerialNoTrackFragment fragment) {
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
        recycler = fragment.getRecyclerView();
        historyLayout = fragment.getHistoryLayout();
        history = fragment.getHistory();
    }

    private void initData() {
        popup = new StockFilterPopup(activity);
        initHistory();
        initRecyclerAdapter();

        api = new StockAPI(activity);
    }

    private void initListener() {

        fragment.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getSerialNoTrack(fragment.getSearch().getText().toString());
                }
                return true;
            }
        });


//        popup.setListener(new StockFilterPopup.Listener() {
//            @Override
//            public void onItemClick(int position) {
//                switch (position) {
//                    //公司
//                    case StockFilterPopup.CLICK_TYPE_COMPANY: {
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "公司");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //仓库
//                    case StockFilterPopup.CLICK_TYPE_WAREHOUSE: {
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "仓库");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //商品分类
//                    case StockFilterPopup.CLICK_TYPE_CLASSIFICATION: {
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "商品分类");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //品牌
//                    case StockFilterPopup.CLICK_TYPE_BRAND: {
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "品牌");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //型号
//                    case StockFilterPopup.CLICK_TYPE_MODEL: {
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "型号");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //重置
//                    case StockFilterPopup.CLICK_TYPE_RESET: {
//                        popup.reset();
//                        break;
//                    }
//                    //确认
//                    case StockFilterPopup.CLICK_TYPE_CONFIRM: {
//                        popup.dismiss();
//                        break;
//                    }
//
//                }
//            }
//        });
    }


    private void initHistory() {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - DpUtils.dip2px(activity, 16f) - DpUtils.dip2px(activity, 6f);

        String s[] = {"11", "123", "4321", "asdfg哈哈哈", "zxcvbn", "你", "宛如少女的猫", "我并不想加班", "阿斯顿发生大发发士大夫撒打算的"};
        LinearLayout linear = null;
        int pix = 0;
        for (int i = 0; i < s.length; i++) {
            if (history.getChildCount() > 2) {
                break;
            }
            final TextView tv = new TextView(activity);
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
                linear.setPadding(0, DpUtils.dip2px(activity, 10f), 0, 0);
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
                        linear.setPadding(0, DpUtils.dip2px(activity, 10f), 0, 0);
                        linear.addView(tv);
                        history.addView(linear);
                    }
                }
            }

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = tv.getText().toString();
                    getSerialNoTrack(text);
                    fragment.getSearch().setText(text);
                    fragment.getSearch().setSelection(text.length());
                }
            });
        }

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
//        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }


    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<SerialNoTrackBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<SerialNoTrackBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number_track, parent, false);
            return new ViewHolder(view);
        }

        public SerialNoTrackBean getItem(int position) {
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
            SerialNoTrackBean item = getItem(position);
            if(item != null){
                holder.date.setText(item.getCreateTime());
                holder.from.setText("从：" + item.getFromPlace());
                holder.to.setText("到：" + item.getToPlace());
                holder.operating.setText(item.getFullName());
                holder.billNo.setText("单据号：" + item.getBillNo());
                holder.name.setText("操作人：" + item.getCreateUserName());
                holder.price.setText("￥" + BigDecimalUtils.getBigDecimal(item.getPrice(), 2).doubleValue());
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.number_track_date)
            TextView date;
            @BindView(R.id.number_track_operating)
            TextView operating;
            @BindView(R.id.number_track_from)
            TextView from;
            @BindView(R.id.number_track_to)
            TextView to;
            @BindView(R.id.number_track_phone)
            TextView billNo;
            @BindView(R.id.number_track_name)
            TextView name;
            @BindView(R.id.number_track_price)
            TextView price;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    //**********************************************************************************************
    private void getSerialNoTrack(String s) {
        api.getSerialNoTrack(s, new OrderAPI.IResultMsg<ArrayList<SerialNoTrackBean>>() {
            @Override
            public void Result(ArrayList<SerialNoTrackBean> beans) {
                if (beans != null && beans.size() > 0) {
                    historyLayout.setVisibility(View.GONE);
                    adapter.notifyData(beans);
                }else{
                    historyLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
