package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.My.EnterpriseDetailPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/14
 * use
 */
public class EnterpriseDetailActivity extends MBaseActivity {
    @BindView(R.id.enterprise_detail_recycler)
    RecyclerView recycler;
    @BindView(R.id.enterprise_detail_license)
    ImageView license;
    private EnterpriseDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_detail);
        ButterKnife.bind(this);
        presenter = new EnterpriseDetailPresenter(this);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }
}
