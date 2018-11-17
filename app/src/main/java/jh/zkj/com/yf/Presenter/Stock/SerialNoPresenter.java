package jh.zkj.com.yf.Presenter.Stock;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
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
public class SerialNoPresenter implements SerialNoContract.ISerialNoPresenter{


    private StockSerialNoFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private StockRecyclerAdapter recyclerAdapter;
    //筛选popup
    private StockFilterPopup popup;
    private LinearLayout histortLinear;

    public SerialNoPresenter(StockSerialNoFragment fragment){
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
        histortLinear = fragment.getHistoryLayout();
    }

    private void initData() {
        initRecyclerAdapter();
        initHistory();
        initListener();
    }

    private void initHistory() {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - DpUtils.dip2px(activity, 16f) * 2;         // 屏幕宽度（像素）
        if(BuildConfig.DEBUG){
            Log.e("wdefer" , "width === " + width);
        }

        String s[] = {"11", "123", "4321", "asdfg", "zxcvbn", "你", "宛如少女的猫", "我并不想加班", "阿斯顿发生大发发士大夫撒打算的"};
        LinearLayout linear = null;
        int pix = 0;
        for(int i = 0 ;i < s.length; i++){
            if(histortLinear.getChildCount() > 2){
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
            tv.setPadding(DpUtils.dip2px(activity, 14f),DpUtils.dip2px(activity, 9f)
                    ,DpUtils.dip2px(activity, 14f),DpUtils.dip2px(activity, 9f));

            tv.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
            tv.setText(s[i]);
            if(linear == null){
                linear = new LinearLayout(activity);
                linear.setOrientation(LinearLayout.HORIZONTAL);
                linear.setPadding(0,0,0,DpUtils.dip2px(activity, 10f));
                histortLinear.addView(linear);
            }
            linear.addView(tv);

            if(linear.getChildCount() > 0){
                TextView childAt = (TextView) linear.getChildAt(linear.getChildCount() - 1);
                childAt.measure(0, 0);
                pix += childAt.getMeasuredWidth() + DpUtils.dip2px(activity, 10f);
                if(linear.getChildCount() > 1){
                    if(BuildConfig.DEBUG){
                        Log.e("wdefer" , "pix =========== " +pix);
                        Log.e("wdefer" , "position =========== " +i);
                    }

                    if(pix > width){
                        if(BuildConfig.DEBUG){
                            Log.e("wdefer" , "position == "+ i);
                        }
                        linear.removeView(childAt);

                        pix = childAt.getMeasuredWidth() + DpUtils.dip2px(activity, 10f);
                        linear = new LinearLayout(activity);
                        linear.setOrientation(LinearLayout.HORIZONTAL);
                        linear.setPadding(0,0,0,DpUtils.dip2px(activity, 10f));
                        linear.addView(tv);
                        histortLinear.addView(linear);
                    }
                }
            }
        }

        if(BuildConfig.DEBUG){
            Log.e("wdefer" , "pix == " + pix);
        }

    }

    private void initListener() {
        popup.setListener(new StockFilterPopup.Listener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    //公司
                    case StockFilterPopup.CLICK_TYPE_COMPANY:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "公司");
                        activity.startActivity(intent);
                        break;
                    }
                    //仓库
                    case StockFilterPopup.CLICK_TYPE_WAREHOUSE:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "仓库");
                        activity.startActivity(intent);
                        break;
                    }
                    //商品分类
                    case StockFilterPopup.CLICK_TYPE_CLASSIFICATION:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "商品分类");
                        activity.startActivity(intent);
                        break;
                    }
                    //品牌
                    case StockFilterPopup.CLICK_TYPE_BRAND:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "品牌");
                        activity.startActivity(intent);
                        break;
                    }
                    //型号
                    case StockFilterPopup.CLICK_TYPE_MODEL:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "型号");
                        activity.startActivity(intent);
                        break;
                    }
                    //重置
                    case StockFilterPopup.CLICK_TYPE_RESET:{
                        popup.reset();
                        break;
                    }
                    //确认
                    case StockFilterPopup.CLICK_TYPE_CONFIRM:{
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
        recyclerAdapter = new StockRecyclerAdapter(fragment, StockConfig.TYPE_STOCK_NUMBER);
        recycler.setAdapter(recyclerAdapter);

        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");

        recyclerAdapter.notifyData(arr);
    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }
}
