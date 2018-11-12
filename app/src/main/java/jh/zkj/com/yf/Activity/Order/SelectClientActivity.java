package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.SelectClientContract;
import jh.zkj.com.yf.Presenter.Order.SelectClientPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use 选择客户
 */
public class SelectClientActivity extends MBaseActivity implements SelectClientContract.ISelectClientView {

    @BindView(R.id.select_client_recycler)
    RecyclerView recycler;
    @BindView(R.id.select_client_refresh)
    TwinklingRefreshLayout refresh;
    @BindView(R.id.search_edit)
    EditText search;
    private SelectClientPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_client);
        ButterKnife.bind(this);

        presenter = new SelectClientPresenter(this);
    }

    @OnClick({R.id.search_left_img_layout})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.search_left_img_layout:
                presenter.finishActivity();
                break;
        }
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public EditText getSearch(){
        return search;
    }

    public TwinklingRefreshLayout getRefreshLayout(){
        return refresh;
    }
}
