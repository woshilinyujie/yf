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
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.ClassifyPopupWindow;
import jh.zkj.com.yf.Mview.MeasureListView;
import jh.zkj.com.yf.Presenter.Analyse.SalesAnalyseSalseFragmentPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 * 商品销量分析  销量
 */

public class SalesAnalyseSalseFragment extends MBaseFragment {
    @BindView(R.id.sales_sales_pie_chart)
    PieChart salesSalesPieChart;
    @BindView(R.id.sales_sales_table_list)
    MeasureListView salesSalesTableList;
    @BindView(R.id.sales_sales_select_tv)
    TextView salesSalesSelectTv;
    private View rootView;
    private Unbinder unbinder;
    private SalesAnalyseSalseFragmentPresenter present;
    private ClassifyPopupWindow classifyPopupWindow;

    public static SalesAnalyseSalseFragment newInstance() {
        SalesAnalyseSalseFragment fragment = new SalesAnalyseSalseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.salse_analyse_salse_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        present = new SalesAnalyseSalseFragmentPresenter(this);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public PieChart getSalesSalesPieChart() {
        return salesSalesPieChart;
    }

    public MeasureListView getSalesSalesTableList() {
        return salesSalesTableList;
    }

    @OnClick(R.id.sales_sales_select_tv)
    public void onViewClicked() {
        present.initPopup();
    }

    public void showPopup(ClassifyPopupWindow classifyPopupWindow){
        classifyPopupWindow.showPopup(salesSalesSelectTv);
    }
}
