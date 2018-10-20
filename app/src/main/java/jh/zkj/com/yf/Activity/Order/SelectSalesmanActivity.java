package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.SelectSalesmanContract;
import jh.zkj.com.yf.Presenter.Order.SelectSalesmanPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use 选择业务员
 */


public class SelectSalesmanActivity extends MBaseActivity implements SelectSalesmanContract.ISelectSalesmanView {

    //返回按钮
    @BindView(R.id.search_left_img)
    ImageView back;
    //搜索
    @BindView(R.id.search_edit)
    EditText search;
    //recycler
    @BindView(R.id.select_salesman_recycler)
    RecyclerView recycler;
    private SelectSalesmanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_salesman);
        ButterKnife.bind(this);
        presenter = new SelectSalesmanPresenter(this);
    }

    @OnClick({R.id.search_left_img})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.search_left_img:
                finish();
                break;
        }
    }

    public RecyclerView getRecycler() {
        return recycler;
    }


}
