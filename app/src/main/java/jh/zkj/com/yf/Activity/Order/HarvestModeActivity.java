package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.HarvestModeContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Order.HarvestModePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/31
 * use 选择收款方式
 */

public class HarvestModeActivity extends MBaseActivity implements HarvestModeContract.IHarvestModeView {

    @BindView(R.id.harvest_mode_money)
    TextView money;
    @BindView(R.id.harvest_mode_recycler)
    RecyclerView recycler;
    @BindView(R.id.harvest_mode_money_et)
    EditText etMoney;
    @BindView(R.id.harvest_mode_title)
    TitleLayout title;
    private HarvestModePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_mode);
        ButterKnife.bind(this);
        presenter = new HarvestModePresenter(this);
    }

    public void setMoney(String s) {
        money.setText(s);
    }

    public void setEtMoney(String s) {
        etMoney.setText(s);
    }

    public RecyclerView getRecyclerView() {
        return recycler;
    }

    public TextView getTitleRightView() {
        return title.getRightText();
    }

    public TitleLayout getTitleLayout() {
        return title;
    }
}
