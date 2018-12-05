package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.ReceivableDetailContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Order.ReceivableDetailPresenter;
import jh.zkj.com.yf.R;

public class ReceivableDetailActivity extends MBaseActivity implements ReceivableDetailContract.IReceivableDetailView {

    @BindView(R.id.receivable_detail_recycler)
    RecyclerView recycler;
    @BindView(R.id.receivable_detail_remake)
    TextView remake;
    @BindView(R.id.receivable_detail_title)
    TitleLayout titleLayout;
    private ReceivableDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivable_detail);
        ButterKnife.bind(this);
        presenter = new ReceivableDetailPresenter(this);
    }

    public void setRemake(String remake){
        this.remake.setText(remake);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TitleLayout getTitleLayout() {
        return titleLayout;
    }
}
