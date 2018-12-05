package jh.zkj.com.yf.Activity.Insurance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Insurance.SelectInsPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use 选购保险
 */
public class SelectInsActivity extends MBaseActivity {

    @BindView(R.id.select_insurance_title)
    TitleLayout title;
    @BindView(R.id.select_insurance_recycle)
    RecyclerView recycle;

    private SelectInsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_insurance);
        ButterKnife.bind(this);
        presenter = new SelectInsPresenter(this);
    }

    public TitleLayout getTitleLayout() {
        return title;
    }

    public RecyclerView getRecycle() {
        return recycle;
    }
}
