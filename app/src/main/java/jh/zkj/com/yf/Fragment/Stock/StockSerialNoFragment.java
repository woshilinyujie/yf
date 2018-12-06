package jh.zkj.com.yf.Fragment.Stock;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Contract.Stock.SerialNoContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.Stock.CommodityPresenter;
import jh.zkj.com.yf.Presenter.Stock.SerialNoPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use 库存序列号
 */
public class StockSerialNoFragment extends MBaseFragment implements SerialNoContract.ISerialNoView {
    //筛选
    @BindView(R.id.serial_on_stoke_filter)
    ImageView mCommStockFilter;
    //清空
    @BindView(R.id.serial_on_stoke_clear_img)
    ImageView mCommStockClearImg;
    //search
    @BindView(R.id.serial_on_stoke_search)
    EditText search;
    //商店名
    @BindView(R.id.serial_on_stoke_text)
    TextView commodity;
    //recycler
    @BindView(R.id.serial_on_stoke_recycler)
    RecyclerView recycler;
    //扫码
    @BindView(R.id.serial_on_stoke_scan)
    ImageView scan;
    //扫码
    @BindView(R.id.serial_on_stoke_refresh)
    TwinklingRefreshLayout refresh;

    //历史记录
    @BindView(R.id.serial_on_stoke_history_layout)
    LinearLayout historyLayout;
    //历史记录
    @BindView(R.id.serial_on_stoke_history)
    LinearLayout history;
    //历史记录
    @BindView(R.id.serial_on_stoke_title_layout)
    RelativeLayout titleLayout;

    @BindView(R.id.serial_on_stoke_msg_layout)
    LinearLayout msgLayout;
    private Unbinder bind;
    private SerialNoPresenter presenter;
    private View mainView;

    public static StockSerialNoFragment newInstance() {
        return new StockSerialNoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_serial_no_stock, null);
        bind = ButterKnife.bind(this, mainView);
        presenter = new SerialNoPresenter(this);
        return mainView;
    }

    @OnClick({R.id.serial_on_stoke_scan, R.id.serial_on_stoke_clear_img
            , R.id.serial_on_stoke_filter, R.id.serial_on_stoke_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //扫码
            case R.id.serial_on_stoke_scan: {
                presenter.openScan();
                break;
            }

            //清空edittext
            case R.id.serial_on_stoke_clear_img: {
                presenter.clearFindEt();
                break;
            }

            case R.id.serial_on_stoke_filter: {
                presenter.showFilterPopup();
                break;
            }
            //删除按钮
            case R.id.serial_on_stoke_delete: {
                presenter.deleteHistory();
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    public ImageView getmCommStockFilter() {
        return mCommStockFilter;
    }

    public ImageView getmCommStockClearImg() {
        return mCommStockClearImg;
    }

    public EditText getSearch() {
        return search;
    }

    public TextView getCommodity() {
        return commodity;
    }

    public RecyclerView getRecyclerView() {
        return recycler;
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

    public LinearLayout getHistoryLayout(){
        return historyLayout;
    }
    public LinearLayout getHistory(){
        return history;
    }

    public RelativeLayout getTitleLayout(){
        return titleLayout;
    }

    public TwinklingRefreshLayout getRefresh() {
        return refresh;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
