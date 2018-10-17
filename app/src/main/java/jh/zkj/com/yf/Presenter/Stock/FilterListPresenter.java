package jh.zkj.com.yf.Presenter.Stock;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Contract.Stock.StockContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/17
 * use
 */
public class FilterListPresenter implements StockContract.IStockPresenter {

    private FilterListActivity activity;
    private RecyclerView recycler;
    private FilterListAdapter adapter;

    public FilterListPresenter(FilterListActivity activity) {
        this.activity = activity;
        recycler = activity.getRecycler();
        initPresenter();
    }

    private void initPresenter() {
        initAdapter();
        activity.setTitle(activity.getIntent().getStringExtra("title"));

        activity.getTitleLayout().getLetfImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new FilterListAdapter();
        recycler.setAdapter(adapter);

        ArrayList<Object> arr = new ArrayList<>();
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());

        adapter.notifyData(arr);
    }


    /**
     * 使用：
     */
    class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_list, parent, false);
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
        public void onBindViewHolder(ViewHolder holder, int position) {

            if(position == 2){
                holder.img.setVisibility(View.VISIBLE);
                holder.msg.setTextColor(0xff6fb1fc);
            }else{
                holder.img.setVisibility(View.GONE);
                holder.msg.setTextColor(0xff333333);
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.filter_list_msg)
            TextView msg;
            @BindView(R.id.filter_list_img)
            ImageView img;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
