package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.FindSerialNoActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.Bean.FilterCompanyBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Mview.EmptyView;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/1
 * use
 */
public class FindSerialNoPresenter {

    private final FindSerialNoActivity activity;
    private EditText search;
    private RecyclerView recycler;
    private TwinklingRefreshLayout refresh;
    private EmptyView empty;
    private OrderAPI api;
    private Adapter adapter;
    private FilterCompanyBean companyBean;

    public FindSerialNoPresenter(FindSerialNoActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        search = activity.getSearch();
        recycler = activity.getRecycler();
        refresh = activity.getRefresh();
        empty = activity.getEmpty();
    }

    private void initData() {
        api = new OrderAPI(activity);
        companyBean = (FilterCompanyBean) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_COMPANY_BEAN);
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    private void initListener() {
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    getSerialInfoList(companyBean.getUuid(), search.getText().toString(), 1, 50);
                }
                return true;
            }
        });
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<CommodityInfoBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<CommodityInfoBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_serial_no, parent, false);
            return new ViewHolder(view);
        }

        public CommodityInfoBean getItem(int position) {
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
            final CommodityInfoBean item = getItem(position);
            if (item != null) {
                holder.serialOn.setText(item.getSerialNo());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCommodityList(item.getSerialNo(), 1, 1);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.find_serial_on)
            TextView serialOn;
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    //**********************************************************************************************
    private void getSerialInfoList(String companyUuid, String keywords, int page, int size) {
        api.getSerialInfoList(companyUuid, keywords, page, size, new OrderAPI.IResultMsg<CommodityBean>() {
            @Override
            public void Result(CommodityBean bean) {
                if (bean != null) {
                    if (bean.getRecords() != null || bean.getRecords().size() > 0) {
                        adapter.notifyData(bean.getRecords());
                    }
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }


    //获取商品列表
    private void getCommodityList(String keyWord, int page, int size){
        api.getSearchCommodity(companyBean == null ? "" : companyBean.getUuid()
                , keyWord, page, size, new OrderAPI.IResultMsg<CommodityBean>() {
                    @Override
                    public void Result(CommodityBean data) {
                        if(data != null && data.getRecords() != null && data.getRecords().size() > 0){
                            Intent intent = new Intent();
                            data.getRecords().get(0).setCount(1);
                            intent.putExtra(OrderConfig.TYPE_STRING_ORDER_SCAN, data.getRecords().get(0));
                            activity.setResult(Activity.RESULT_OK, intent);
                            activity.finish();
                        }else{
                            MToast.makeText(activity,"该商品不存在", MToast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
    }
}
