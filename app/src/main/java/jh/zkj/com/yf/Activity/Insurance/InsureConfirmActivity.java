package jh.zkj.com.yf.Activity.Insurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Insurance.InsureConfirmPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use 投保确认
 */

public class InsureConfirmActivity extends AppCompatActivity {

    @BindView(R.id.insure_confirm_title)
    TitleLayout title;
    @BindView(R.id.insure_confirm_recycle)
    RecyclerView recycle;
    @BindView(R.id.select_insurance_btn)
    TextView insuranceBtn;
    private InsureConfirmPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insure_confirm);
        ButterKnife.bind(this);
        presenter = new InsureConfirmPresenter(this);

    }

    public TitleLayout getTitleLayout() {
        return title;
    }

    public RecyclerView getRecycle() {
        return recycle;
    }

    public TextView getInsuranceBtn() {
        return insuranceBtn;
    }
}
