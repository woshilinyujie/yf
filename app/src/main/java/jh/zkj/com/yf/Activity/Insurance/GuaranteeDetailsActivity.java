package jh.zkj.com.yf.Activity.Insurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Insurance.GuaranteeDetailsPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/5
 * use 保障详情
 */

public class GuaranteeDetailsActivity extends AppCompatActivity {

    @BindView(R.id.select_insurance_title)
    TitleLayout titleLayout;
    @BindView(R.id.guarantee_detail_head)
    ImageView headImg;
    private GuaranteeDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarantee_details);
        ButterKnife.bind(this);
        presenter = new GuaranteeDetailsPresenter(this);
    }

    public TitleLayout getTitleLayout() {
        return titleLayout;
    }

    public ImageView getHeadImg() {
        return headImg;
    }
}
