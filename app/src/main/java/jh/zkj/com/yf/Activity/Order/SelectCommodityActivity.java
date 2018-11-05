package jh.zkj.com.yf.Activity.Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

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
    @BindView(R.id.select_commodity_count)
    TextView comCount;
    //刷新控件
    @BindView(R.id.select_commodity_refresh)
    TwinklingRefreshLayout refresh;
    //搜索
    @BindView(R.id.search_edit)
    EditText search;
    private SelectCommodityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_commodity);
        ButterKnife.bind(this);
        presenter = new SelectCommodityPresenter(this);
    }

    @OnClick({R.id.search_left_img_layout, R.id.select_commodity_success})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_left_img_layout: {
                finish();
                break;
            }
            case R.id.select_commodity_success: {
                presenter.successGoBack();
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

    public TwinklingRefreshLayout getRefreshLayout() {
        return refresh;
    }

    public void setComCount(String s) {
        comCount.setText(s);
    }

    public EditText getSearch() {
        return search;
    }

    @Override
    public void setStoreName(String s) {
        storeName.setText(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
