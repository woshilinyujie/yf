package jh.zkj.com.yf.Fragment.Stock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.Stock.CommodityPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use
 */
public class CommodityStockFragment extends MBaseFragment {

    //筛选
    @BindView(R.id.comm_stock_filter)
    ImageView mCommStockFilter;
    //清空
    @BindView(R.id.comm_stock_clear_img)
    ImageView mCommStockClearImg;
    //search
    @BindView(R.id.comm_stock_search)
    EditText search;
    //商店名
    @BindView(R.id.comm_stock_text)
    TextView commodity;
    //抽屉listview
    @BindView(R.id.comm_stock_list)
    ListView listView;
    //抽屉listview
    @BindView(R.id.comm_stock_refresh)
    TwinklingRefreshLayout refresh;
    //抽屉listview
    @BindView(R.id.comm_stock_title_layout)
    RelativeLayout titleLayout;

    @BindView(R.id.comm_stock_msg_layout)
    LinearLayout msgLayout;
    private Unbinder bind;
    private CommodityPresenter presenter;
    private View mainView;

    public static CommodityStockFragment newInstance() {
        return new CommodityStockFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_commodity_stock, null);
        bind = ButterKnife.bind(this, mainView);
        presenter = new CommodityPresenter(this);
        return mainView;
    }

    @OnClick({R.id.comm_stock_clear_img, R.id.comm_stock_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //清空edittext
            case R.id.comm_stock_clear_img: {
                setSearchText("");
                break;
            }

            case R.id.comm_stock_filter: {
                presenter.showFilterPopup();
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }


    public EditText getSearch() {
        return search;
    }

    public TextView getCommodity() {
        return commodity;
    }

    public ListView getListView() {
        return listView;
    }

    public LinearLayout getMsgLayout() {
        return msgLayout;
    }

    public View getMainView() {
        return mainView;
    }

    public void setSearchText(String s){
        search.setText(s);
    }

    public TwinklingRefreshLayout getRefresh() {
        return refresh;
    }

    public RelativeLayout getTitleLayout() {
        return titleLayout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
