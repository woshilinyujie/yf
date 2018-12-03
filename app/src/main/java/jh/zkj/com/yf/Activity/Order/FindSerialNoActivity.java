package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Mview.EmptyView;
import jh.zkj.com.yf.Presenter.Order.FindSerialNoPresenter;
import jh.zkj.com.yf.R;

public class FindSerialNoActivity extends AppCompatActivity {

    @BindView(R.id.find_serial_on_edit)
    EditText search;
    @BindView(R.id.find_serial_on_empty)
    EmptyView empty;
    @BindView(R.id.find_serial_on_recycler)
    RecyclerView recycler;
//    @BindView(R.id.find_serial_on_refresh)
//    TwinklingRefreshLayout refresh;

    private FindSerialNoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_serial_no);
        ButterKnife.bind(this);
        presenter = new FindSerialNoPresenter(this);
    }

    @OnClick({R.id.find_serial_on_back})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.find_serial_on_back:{
                finish();
                break;
            }
        }
    }

    public EditText getSearch() {
        return search;
    }

    public EmptyView getEmpty() {
        return empty;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

//    public TwinklingRefreshLayout getRefresh() {
//        return refresh;
//    }

    public FindSerialNoPresenter getPresenter() {
        return presenter;
    }
}
