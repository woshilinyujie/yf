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
import jh.zkj.com.yf.Presenter.Analyse.ShopManAnalyseSalseFragmentPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 * 店员业绩分析  销量
 */

public class ShopManAnalyseSalseFragment extends MBaseFragment {

    @BindView(R.id.shop_man_sales_chart)
    LineChart shopManSalesChart;
    @BindView(R.id.shop_man_sales_pie_chart)
    PieChart shopManSalesPieChart;
    @BindView(R.id.shop_man_sales_table_list)
    MeasureListView shopManSalesTableList;
    @BindView(R.id.shop_man_sales_all)
    TextView shopManSalesAll;
    private View rootView;
    private Unbinder unbinder;
    private ShopManAnalyseSalseFragmentPresenter present;
    private String endData;
    private String startData;
    private String conpanyCode;
    private String shopName;
    private String CompanyUuid;

    public static ShopManAnalyseSalseFragment newInstance(String shopName, String startData, String endData, String conpanyCode,String CompanyUuid) {
        ShopManAnalyseSalseFragment fragment = new ShopManAnalyseSalseFragment();
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
        rootView = View.inflate(getActivity(), R.layout.shop_man_analyse_salse_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        present = new ShopManAnalyseSalseFragmentPresenter(this);
        present.getLinCharData(shopName, conpanyCode, startData, endData, "", "", "",CompanyUuid,"clerk");
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public LineChart getShopManSalesChart() {
        return shopManSalesChart;
    }

    public PieChart getShopManSalesPieChart() {
        return shopManSalesPieChart;
    }

    public MeasureListView getShopManSalesTableList() {
        return shopManSalesTableList;
    }

    public ShopManAnalyseSalseFragmentPresenter getPresent() {
        return present;
    }
    public void setShopManSalesAll(String s){
        shopManSalesAll.setText(s);
    }
}
