package jh.zkj.com.yf.Fragment.Stock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.Stock.CommodityPresenter;
import jh.zkj.com.yf.Presenter.Stock.StockListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use
 */
public class CommodityStockFragment extends MBaseFragment{

    //二维码
    @BindView(R.id.stock_scan)
    ImageView scan;
    //二维码边上的空格
    @BindView(R.id.stock_space)
    View space;
    //筛选
    @BindView(R.id.stock_filter)
    ImageView filter;
    //X
    @BindView(R.id.stock_clear_img)
    ImageView clearImg;
    //搜索内容et
    @BindView(R.id.stock_find_et)
    EditText find;
    //用于填写串号  商店命等
    @BindView(R.id.stock_msg_0)
    TextView msg0;
    @BindView(R.id.stock_msg_1)
    TextView msg1;
    //列表
    @BindView(R.id.stock_recycler_frame_layout)
    FrameLayout frameLayout;
    //消息
    @BindView(R.id.stock_msg_layout)
    ConstraintLayout msgLayout;
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
        Bundle bundle = getArguments();
        if(bundle != null){
            presenter = new CommodityPresenter();
        }

        return mainView;
    }

    @OnClick({R.id.stock_clear_img, R.id.stock_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //清空edittext
            case R.id.stock_clear_img:{
                break;
            }

            case R.id.stock_filter:{
                break;
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    public ImageView getScan() {
        return scan;
    }

    public ImageView getFilter() {
        return filter;
    }

    public ImageView getClearImg() {
        return clearImg;
    }

    public EditText getFind() {
        return find;
    }

    public TextView getMsg0() {
        return msg0;
    }

    public TextView getMsg1() {
        return msg1;
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public View getSpace() {
        return space;
    }

    public View getMain() {
        return mainView;
    }

    public ConstraintLayout getMsgLayout() {
        return msgLayout;
    }

    public void setFindEt(String s) {
        find.setText(s);
    }
}
