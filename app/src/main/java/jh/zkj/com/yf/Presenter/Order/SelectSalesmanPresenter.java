package jh.zkj.com.yf.Presenter.Order;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Order.SelectSalesmanActivity;
import jh.zkj.com.yf.Contract.Order.SelectSalesmanContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use
 */

public class SelectSalesmanPresenter implements SelectSalesmanContract.ISelectSalesmanPresenter {

    private SelectSalesmanActivity activity;
    private RecyclerView recycler;
    private SelectSalesmanAdapter adapter;

    public SelectSalesmanPresenter(SelectSalesmanActivity activity) {
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
        adapter = new SelectSalesmanAdapter();
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
    class SelectSalesmanAdapter extends RecyclerView.Adapter<SelectSalesmanAdapter.ViewHolder> {
        private Bitmap select;
        private Bitmap unSelect;

        private ArrayList<Object> mArr = new ArrayList<>();

        public SelectSalesmanAdapter(){
            Resources res= activity.getResources();

            select = BitmapFactory.decodeResource(res, R.mipmap.select_blue);
            unSelect = BitmapFactory.decodeResource(res, R.mipmap.select_gray);
        }

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_salesman, parent, false);
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
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.selectImg.setImageBitmap(select);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private View view;
            @BindView(R.id.select_salesman_img)
            ImageView selectImg;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
