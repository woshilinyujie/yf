package jh.zkj.com.yf.Fragment.Stock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Contract.Stock.SkuStockContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.EmptyView;
import jh.zkj.com.yf.Presenter.Stock.SerialNoTrackPresenter;
import jh.zkj.com.yf.Presenter.Stock.SkuStockPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SkuStockFragment extends MBaseFragment implements SkuStockContract.ISkuStockView{
    //清空
    @BindView(R.id.sku_stock_clear_img)
    ImageView mCommStockClearImg;
    //search
    @BindView(R.id.sku_stock_search)
    EditText search;
    //recycler
    @BindView(R.id.sku_stock_recycler)
    RecyclerView recycler;
    //扫码
    @BindView(R.id.sku_stock_scan)
    ImageView scan;
    @BindView(R.id.sku_stock_title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.sku_stock_empty)
    EmptyView empty;
    private Unbinder bind;
    private SkuStockPresenter presenter;
    private View mainView;

    public static SkuStockFragment newInstance() {
        return new SkuStockFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_sku_stock, null);
        bind = ButterKnife.bind(this, mainView);
        presenter = new SkuStockPresenter(this);
        return mainView;
    }

    @OnClick({R.id.sku_stock_clear_img, R.id.sku_stock_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //清空edittext
            case R.id.sku_stock_clear_img: {
                setSearchText("");
                break;
            }

            case R.id.sku_stock_filter: {
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

    public ImageView getmCommStockClearImg() {
        return mCommStockClearImg;
    }

    public EditText getSearch() {
        return search;
    }

    public RecyclerView getRecyclerView() {
        return recycler;
    }

    public View getMainView() {
        return mainView;
    }

    public void setSearchText(String s){
        search.setText(s);
    }

    public RelativeLayout getTitleLayout(){
        return titleLayout;
    }

    public EmptyView getEmpty() {
        return empty;
    }
}
