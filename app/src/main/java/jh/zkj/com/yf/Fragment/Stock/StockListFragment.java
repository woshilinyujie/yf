package jh.zkj.com.yf.Fragment.Stock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jh.zkj.com.yf.Contract.Stock.StockListContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.Stock.StockListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/11
 * use 库存列表  （商品库存， 库存串号， 串号跟踪， 分仓库存）
 * ps 如果这块以后的逻辑变得非常复杂可能会考虑分成4个fragment  现在先这么用
 */
public class StockListFragment extends MBaseFragment implements StockListContract.IStockListView{

    //商品库存
    public static final int TYPE_COMMODITY_STOCKS = 0x00000001;
    //库存串号
    public static final int TYPE_STOCK_NUMBER = 0x00000002;
    //串号跟踪
    public static final int TYPE_NUMBER_TRACK = 0x00000003;
    //分仓库存
    public static final int TYPE_CHILD_WAREHOUSE_STOCKS = 0x00000004;

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

    public static StockListFragment newInstance(int flag) {
        StockListFragment fragment = new StockListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page_type", flag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list, null);
        bind = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if(bundle != null){
            StockListPresenter presenter = new StockListPresenter(this, bundle.getInt("page_type")) ;
        }

        return view;
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

    public ConstraintLayout getMsgLayout() {
        return msgLayout;
    }
}
