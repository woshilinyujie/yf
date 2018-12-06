package jh.zkj.com.yf.Presenter.Stock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.AppConfig;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Bean.SerialNoTrackBean;
import jh.zkj.com.yf.Contract.Stock.SerialNoTrackContract;
import jh.zkj.com.yf.Fragment.Stock.SerialNoTrackFragment;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.StockFilterPopup;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SerialNoTrackPresenter implements SerialNoTrackContract.ISerialNoTrackPresenter {

    private static final int REQUEST_SCAN = 1;

    private SerialNoTrackFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private Adapter adapter;
    //筛选popup
    private StockFilterPopup popup;
    private StockAPI api;
    private OrderAPI orderApi;
    private LinearLayout historyLayout;
    private LinearLayout history;
    private int drawables[] = {R.mipmap.stock_dot_0, R.mipmap.stock_dot_1, R.mipmap.stock_dot_2
            , R.mipmap.stock_dot_3, R.mipmap.stock_dot_4, R.mipmap.stock_dot_5, R.mipmap.stock_dot_6
            , R.mipmap.stock_dot_7, R.mipmap.stock_dot_8, R.mipmap.stock_dot_9};
    private MyBean myBean;

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
        orderApi = new OrderAPI(activity);
    }

    private void initListener() {

        fragment.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchText = fragment.getSearch().getText().toString();
                    if (TextUtils.isEmpty(searchText)) {
                        fragment.getTitleLayout().setVisibility(View.GONE);
                        historyLayout.setVisibility(View.VISIBLE);
                        initHistory();
                        return true;
                    }
                    putSearchSP(searchText);
                    initHistory();
                    getSerialInfoList(searchText, 1, 50);
                }
                return true;
            }
        });

    }

    private void initHistory() {
        String historyText = PrefUtils.getString(activity, StockConfig.TYPE_STRING_SERIAL_NO_TRACK_HISTORY, "");
        ArrayList<String> arr = (ArrayList<String>) JSONObject.parseArray(historyText, String.class);
        history.removeAllViews();
        if (arr == null) {
            return;
        }

        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - DpUtils.dip2px(activity, 16f) - DpUtils.dip2px(activity, 6f);

        LinearLayout linear = null;
        int pix = 0;
        int position = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (history.getChildCount() > 2) {
                position = i;
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
            tv.setText(arr.get(i));
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
                    getSerialInfoList(text, 1, 50);
                    fragment.getSearch().setText(text);
                    fragment.getSearch().setSelection(text.length());
                }
            });
            position = i;
        }

        for (int i = 0; i < position; i++) {
            if (arr.size() - 1 > position) {
                arr.remove(position);
            }
        }

        PrefUtils.putString(activity, StockConfig.TYPE_STRING_SERIAL_NO_TRACK_HISTORY, JSON.toJSONString(arr));
    }

    //recyclerView兼容跟多形式的嵌套布局 相比listview来说坑会少一些 方便后期维护
    private void initRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    private void putSearchSP(String searchText) {
        if (!TextUtils.isEmpty(searchText)) {
            String searchHistory = PrefUtils.getString(activity, StockConfig.TYPE_STRING_SERIAL_NO_TRACK_HISTORY, "");
            ArrayList<String> strings = (ArrayList<String>) JSON.parseArray(searchHistory, String.class);
            if (strings == null) {
                strings = new ArrayList<>();
            }
            for (int i = 0; i < strings.size(); i++) {
                if (searchText.equals(strings.get(i))) {
                    strings.remove(i);
                    break;
                }
            }
            strings.add(0, searchText);
            PrefUtils.putString(activity, StockConfig.TYPE_STRING_SERIAL_NO_TRACK_HISTORY, JSON.toJSONString(strings));
        }
    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
//        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }

    @Override
    public void openScan() {
        Intent intent = new Intent(activity, ScanActivity.class);
        fragment.startActivityForResult(intent, REQUEST_SCAN);
    }

    @Override
    public void deleteHistory() {
        PrefUtils.putString(activity, StockConfig.TYPE_STRING_SERIAL_NO_TRACK_HISTORY, "");
        initHistory();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String searchText = data.getStringExtra(AppConfig.RESULT_STRING_SCAN_NUMBER);
                    if(popup != null){
                        popup.reset();
                    }

                    putSearchSP(searchText);

                    fragment.getSearch().setText(searchText);

                    getSerialNoTrack(searchText);
                }
            }
        }
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        //0是详情  1是搜索
        private int typeStatus = 0;

        private ArrayList<SerialNoTrackBean.DetailsBean> mArr = new ArrayList<>();
        private ArrayList<CommodityInfoBean> mComArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<SerialNoTrackBean.DetailsBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            typeStatus = 0;
            notifyDataSetChanged();
        }

        public void notifyComData(ArrayList<CommodityInfoBean> arr) {
            mComArr.clear();
            if (arr != null) {
                mComArr.addAll(arr);
            }
            typeStatus = 1;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(typeStatus == 0){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number_track, parent, false);
                return new ViewHolder(view);
            }else{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_serial_no, parent, false);
                return new ComViewHolder(view);
            }

        }

        public SerialNoTrackBean.DetailsBean getItem(int position) {
            if (mArr != null && mArr.size() > position) {
                return mArr.get(position);
            }
            return null;
        }

        public CommodityInfoBean getComItem(int position) {
            if (mComArr != null && mComArr.size() > position) {
                return mComArr.get(position);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return typeStatus;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position) == 0){
                SerialNoTrackBean.DetailsBean item = getItem(position);
                ViewHolder holderView = (ViewHolder)holder;
                if (item != null) {
                    holderView.date.setText(item.getBiz_date());
                    holderView.from.setText("从：" + item.getSource());
                    holderView.to.setText("到：" + item.getTarget());
                    holderView.operating.setText(StockConfig.getBizType(item.getBiz_type()));
                    holderView.billNo.setText("单据号：" + item.getBill_no());
                    holderView.name.setText("制单人：" + item.getCreate_user_name());
                    BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(String.valueOf(item.getPrice()), 2);
                    String price = BigDecimalUtils.fmtMicrometer(bigDecimal.toString());
                    holderView.price.setText("¥" + price);
                    holderView.dot.setImageResource(drawables[item.getColorType()]);
                    holderView.comNmae.setText(item.getSku_full_name());
                    if(position == 0){
                        holderView.space.setVisibility(View.VISIBLE);
                    }else{
                        holderView.space.setVisibility(View.GONE);
                    }
                }
            }else{
                final CommodityInfoBean item = getComItem(position);
                ComViewHolder holderView = (ComViewHolder)holder;
                if (item != null) {
                    holderView.serialOn.setText(item.getSerialNo());
                    holderView.view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getSerialNoTrack(item.getSerialNo());
                        }
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            if(typeStatus == 0){
                return mArr == null ? 0 : mArr.size();
            }else{
                return mComArr == null ? 0 : mComArr.size();
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.number_track_date)
            TextView date;
            @BindView(R.id.number_track_com_name)
            TextView comNmae;
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
            @BindView(R.id.number_track_dot)
            ImageView dot;
            @BindView(R.id.number_track_title_space)
            View space;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }

        class ComViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.find_serial_on)
            TextView serialOn;
            private View view;

            public ComViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    //**********************************************************************************************
    private void getSerialNoTrack(String s) {
        api.getSerialNoTrack(s, new OrderAPI.IResultMsg<SerialNoTrackBean>() {
            @Override
            public void Result(SerialNoTrackBean bean) {
                if (bean != null && bean.getDetails() != null && bean.getDetails().size() > 0) {
                    historyLayout.setVisibility(View.GONE);
                    int color_type = -1;
                    String uuid = "";
                    for (int i = 0 ; i < bean.getDetails().size() ; i++){
                        if(!bean.getDetails().get(i).getSerial_info_uuid().equals(uuid)) {
                            uuid = bean.getDetails().get(i).getSerial_info_uuid();
                            ++color_type;
                        }
                        bean.getDetails().get(i).setColorType(color_type);
                    }
                    fragment.getTitleLayout().setVisibility(View.VISIBLE);
                    fragment.setSerialNo("序列号：" + bean.getSerials());
//                    fragment.setSerialNoName("商品名称：" + bean.getSku_full_name());

                    adapter.notifyData(bean.getDetails());
                } else {
                    fragment.getTitleLayout().setVisibility(View.GONE);
                    historyLayout.setVisibility(View.VISIBLE);
                    initHistory();
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }


    private void getSerialInfoList(String keywords, int page, int size) {
        String erp_json = PrefUtils.getString(activity, "erp_json", "");
        if(TextUtils.isEmpty(erp_json)){
            myBean = JSON.parseObject(erp_json, MyBean.class);
        }
        orderApi.getSerialInfoList(myBean == null ? "" : myBean.getData().getCompanyUuid()
                , keywords, page, size, false, new OrderAPI.IResultMsg<ArrayList<CommodityInfoBean>>() {
            @Override
            public void Result(ArrayList<CommodityInfoBean> bean) {
                if (bean != null && bean.size() > 0) {
                    historyLayout.setVisibility(View.GONE);
                    adapter.notifyComData(bean);
                }else{
                    historyLayout.setVisibility(View.VISIBLE);
                    initHistory();
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
