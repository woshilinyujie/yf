package jh.zkj.com.yf.Presenter.Insurance;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Insurance.SelectInsActivity;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use
 */
public class SelectInsPresenter {

    private final SelectInsActivity activity;
    private RecyclerView recycle;
    private TitleLayout titleLayout;

    public SelectInsPresenter(SelectInsActivity activity){
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        recycle = activity.getRecycle();
        titleLayout = activity.getTitleLayout();
    }

    private void initData() {
        initAdapter();
    }

    private void initListener() {
        
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycle.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter();
        recycle.setAdapter(adapter);
    }



    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            if(arr != null){
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_insurance, parent, false);
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
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }
}
