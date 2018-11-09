package jh.zkj.com.yf.Activity.Analyse;

import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Analyse.ClassifiedContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Analyse.ClassifiedPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/8.
 */

public class ClassifiedActivity extends MBaseActivity implements ClassifiedContract.ClassifiedView {
    @BindView(R.id.title)
    TitleLayout title;
    @BindView(R.id.list)
    ListView list;
    private ClassifiedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classified);
        ButterKnife.bind(this);
        presenter = new ClassifiedPresenter(this);
    }



    public ListView getList() {
        return list;
    }
    public void setTitle(String s){
        title.setTitle(s);
    }
}
