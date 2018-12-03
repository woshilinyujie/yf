package jh.zkj.com.yf.Fragment.Analyse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.MeasureListView;
import jh.zkj.com.yf.Presenter.Analyse.ShopAnalyseSalseProfitFragmentPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/11.
 * 门店经营分析利润
 */

public class ShopAnalyseProfitFragment extends MBaseFragment {


    @BindView(R.id.sales_profit_all)
    TextView salesProfitAll;
    @BindView(R.id.sales_profit_chart)
    LineChart salesProfitChart;
    @BindView(R.id.sales_profit_pie_chart)
    PieChart salesProfitPieChart;
    @BindView(R.id.sales_profit_table_list)
    MeasureListView salesProfitTableList;
    Unbinder unbinder;
    private View rootView;
    private ShopAnalyseSalseProfitFragmentPresenter present;
    private String endData;
    private String startData;
    private String conpanyCode;
    private String shopName;
    private String salesProfitAllTx;
    private String CompanyUuid;

    public static ShopAnalyseProfitFragment newInstance(String shopName, String startData, String endData, String conpanyCode, String CompanyUuid) {
        ShopAnalyseProfitFragment fragment = new ShopAnalyseProfitFragment();
        Bundle bundle = new Bundle();
        bundle.putString("endData", endData);
        bundle.putString("startData", startData);
        bundle.putString("conpanyCode", conpanyCode);
        bundle.putString("shopName", shopName);
        bundle.putString("CompanyUuid", CompanyUuid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        endData = getArguments().getString("endData");
        startData = getArguments().getString("startData");
        conpanyCode = getArguments().getString("conpanyCode");
        shopName = getArguments().getString("shopName");
        CompanyUuid = getArguments().getString("CompanyUuid");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.shop_analyse_profit_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        present = new ShopAnalyseSalseProfitFragmentPresenter(this);
        present.getLinCharData(shopName, conpanyCode, startData, endData, "", "", "", CompanyUuid, "store");
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public LineChart getSalesProfitChart() {
        return salesProfitChart;
    }

    public PieChart getSalesProfitPieChart() {
        return salesProfitPieChart;
    }

    public MeasureListView getSalesProfitTableList() {
        return salesProfitTableList;
    }

    public void setSalesProfitAllTx(String salesProfitAllTx) {
        salesProfitAll.setText(salesProfitAllTx);
    }

    public ShopAnalyseSalseProfitFragmentPresenter getPresent() {
        return present;
    }
}
