package jh.zkj.com.yf.Presenter.Insurance;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Insurance.InsOrderListActivity;
import jh.zkj.com.yf.Fragment.Insurance.InsOrderListFragment;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use
 */
public class InsOrderListFragmentPresenter {

    private final InsOrderListFragment fragment;
    private RecyclerView recycler;
    private TwinklingRefreshLayout refresh;
    private InsOrderListActivity activity;
    private Adapter adapter;

    public InsOrderListFragmentPresenter(InsOrderListFragment fragment) {
        this.fragment = fragment;
        initView();
        initData();
        initListener();

    }

    private void initView() {
        recycler = fragment.getRecycler();
        refresh = fragment.getRefresh();
        activity = (InsOrderListActivity) fragment.getActivity();
    }

    private void initData() {
        initAdapter();
    }

    private void initListener() {

    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);

        ArrayList<Object> arr = new ArrayList<>();
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        adapter.notifyData(arr);
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ins_order_list, parent, false);
            return new ViewHolder(view);
        }

        public Object getItem(int position) {
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

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.ins_order_list_space)
            View space;
            //保险类型
            @BindView(R.id.ins_order_list_type)
            TextView type;
            @BindView(R.id.ins_order_list_status)
            TextView status;
            @BindView(R.id.ins_order_list_title)
            TextView title;
            @BindView(R.id.ins_order_list_mei)
            TextView MEI;
            @BindView(R.id.ins_order_list_date)
            TextView date;
            @BindView(R.id.ins_order_list_money)
            TextView money;
            @BindView(R.id.ins_order_list_cancel)
            TextView cancel;
            @BindView(R.id.ins_order_list_pay)
            TextView pay;
            @BindView(R.id.ins_order_list_no_more)
            TextView noMore;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }
}
