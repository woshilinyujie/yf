package jh.zkj.com.yf.Presenter.Order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.Order.SelectClientActivity;
import jh.zkj.com.yf.Contract.Order.SelectClientContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use
 */
public class SelectClientPresenter implements SelectClientContract.ISelectClientPresenter {

    private SelectClientActivity activity;
    private RecyclerView recycler;
    private SelectClientAdapter adapter;

    public SelectClientPresenter(SelectClientActivity activity){
        this.activity = activity;

        initPresenter();
    }

    private void initPresenter() {
        initAdapter();
    }

    private void initAdapter() {
        recycler = activity.getRecycler();
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new SelectClientAdapter();
        recycler.setAdapter(adapter);

        ArrayList<Object> arr = new ArrayList<>();
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");
        arr.add("111111");

        adapter.notifyData(arr);
    }

    /**
     * 使用：
     */
    class SelectClientAdapter extends RecyclerView.Adapter<SelectClientAdapter.ViewHolder>{

        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_client, parent, false);
            return new ViewHolder(view);
        }

        public Object getItem(int position){
            if(mArr != null && mArr.size() > position){
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

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private View view;
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
