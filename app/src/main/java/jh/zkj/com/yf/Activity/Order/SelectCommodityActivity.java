package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Contract.Order.SelectCommodityContract;
import jh.zkj.com.yf.Presenter.Order.SelectCommodityPresenter;
import jh.zkj.com.yf.R;

public class SelectCommodityActivity extends AppCompatActivity implements SelectCommodityContract.ISelectCommodityView {

    //公司名字
    @BindView(R.id.select_commodity_store_name)
    TextView storeName;
    @BindView(R.id.select_commodity_recycler)
    RecyclerView recycler;
    //title
    @BindView(R.id.select_commodity_title)
    RelativeLayout title;
    //已选商品总数量
    @BindView(R.id.select_commodity_num_layout)
    LinearLayout numLayout;
    //购物车
    @BindView(R.id.select_commodity_car)
    ImageView car;
    //购物车清空
    @BindView(R.id.commodity_car_clear)
    TextView carClear;
    //购物车列表
    @BindView(R.id.commodity_car_recycler)
    RecyclerView carRecycler;
    //购物车
    @BindView(R.id.commodity_car_layout)
    RelativeLayout comCarLayout;
    private SelectCommodityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_commodity);
        ButterKnife.bind(this);
        presenter = new SelectCommodityPresenter(this);
    }

    @OnClick({R.id.select_commodity_car, R.id.commodity_car_layout
            , R.id.commodity_car_title_layout/*这个点击事件不用管*/
            , R.id.search_left_img_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_commodity_car: {
                presenter.showComCar();
                break;
            }
            case R.id.commodity_car_layout: {
                setComCarVisibility(View.GONE);
                break;
            }
            case R.id.search_left_img_layout: {
                finish();
                break;
            }
        }
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public RelativeLayout getTitleLayout() {
        return title;
    }

    public RecyclerView getCarRecycler() {
        return carRecycler;
    }

    public RelativeLayout getComCarLayout() {
        return comCarLayout;
    }

    @Override
    public void setComCarVisibility(int visibility) {
        comCarLayout.setVisibility(visibility);
    }

    @Override
    public void setStoreName(String s) {
        storeName.setText(s);
    }
}
