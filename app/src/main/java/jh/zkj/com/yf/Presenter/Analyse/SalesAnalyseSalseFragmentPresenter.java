package jh.zkj.com.yf.Presenter.Analyse;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bin.david.form.data.column.Column;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jh.zkj.com.yf.API.AnalyseAPI;
import jh.zkj.com.yf.Activity.Analyse.ShopAnalyseActivity;
import jh.zkj.com.yf.Bean.TestBean;
import jh.zkj.com.yf.Contract.Analyse.SalesAnalyseSalseFragmentContract;
import jh.zkj.com.yf.Fragment.Analyse.SalesAnalyseSalseFragment;
import jh.zkj.com.yf.Listener.ClassifySelectListener;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.ClassifyPopupWindow;
import jh.zkj.com.yf.Mview.MyMarkerView;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 */

public class SalesAnalyseSalseFragmentPresenter implements SalesAnalyseSalseFragmentContract.SalesAnalyseSalseFragmentPresent {
    private final FragmentActivity context;
    SalesAnalyseSalseFragment fragment;
    private LineChart mLineChart;
    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };
    private List<PieEntry> entries;
    private Column<Object> name;
    private Column<Object> age;
    private Column<Object> id;
    private List<TestBean> list;
    private ClassifyPopupWindow classifyPopupWindow;
    private final AnalyseAPI analyseAPI;


    public SalesAnalyseSalseFragmentPresenter(SalesAnalyseSalseFragment fragment) {
        this.fragment = fragment;
        context =fragment.getActivity();
        analyseAPI = new AnalyseAPI();
        initPieChar();
        initTable();
        getLinData();
    }

    //初始化popup 并且选择后  回调选择数据
    public void initPopup() {
        if(classifyPopupWindow ==null)
            classifyPopupWindow = new ClassifyPopupWindow(fragment.getActivity());
        fragment.showPopup(classifyPopupWindow);
        classifyPopupWindow.setClassifyListener(new ClassifySelectListener() {
            @Override
            public void onClassifySelect(String classify, String flag) {//选择分类后回调
                MToast.makeText(fragment.getActivity(),flag, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getLinData() {
//        analyseAPI.LineDate(context,"00001",);
    }

    private void initTable() {
        list = new ArrayList<TestBean>();
        TestBean bean = new TestBean();
        TestBean bean1 = new TestBean();
        TestBean bean2 = new TestBean();
        bean.setCompany("中科金");
        bean1.setCompany("中科金1");
        bean2.setCompany("中科金2");
        bean.setId(1);
        bean1.setId(2);
        bean2.setId(3);
        bean.setSales("200");
        bean1.setSales("300");
        bean2.setSales("400");
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
        SalesAnalyseSalseFragmentAdapter adapter = new SalesAnalyseSalseFragmentAdapter();
        fragment.getSalesSalesTableList().setAdapter(adapter);
    }

    private void initPieChar() {
        PieChart pieChart = fragment.getSalesSalesPieChart();
        //模拟数据
        HashMap dataMap = new HashMap();
        dataMap.put("A", "300");
        dataMap.put("B", "600");
        dataMap.put("C", "500");
        dataMap.put("D", "800");
        setPieChart(pieChart, dataMap, "数据", true);
    }



    @Override
    public void initListener() {

    }




    public void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, boolean showLegend) {
        pieChart.setUsePercentValues(true);//设置使用百分比（后续有详细介绍）
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.setExtraOffsets(0,
                0,
                DpUtils.dip2px(context,20),
                15); //设置边距
        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置摩擦系数（值越小摩擦系数越大）
        pieChart.setRotationEnabled(false);//是否可以旋转
        pieChart.setHighlightPerTapEnabled(false);//点击是否放大
        pieChart.setRotationAngle(0f);//设置旋转角度
        pieChart.setTransparentCircleRadius(0);//设置半透明圆环的半径,看着就有一种立体的感觉
        //这个方法为true就是环形图，为false就是饼图
        pieChart.setDrawHoleEnabled(true);
        //设置环形中间空白颜色是白色
        pieChart.setHoleColor(Color.WHITE);
        //设置半透明圆环的颜色
        pieChart.setTransparentCircleColor(Color.WHITE);
        //设置半透明圆环的透明度
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setDrawCenterText(false);
        pieChart.setCenterTextSize(0);
        pieChart.setScaleX(0.9f);
        pieChart.setScaleY(0.9f);
        pieChart.setCenterTextSizePixels(0);
        pieChart.setEntryLabelTextSize(0);
        //图例设置
        Legend legend = pieChart.getLegend();
        legend.setXOffset(DpUtils.dip2px(fragment.getActivity(),55));
        legend.setYOffset(38);
        legend.setFormSize(15);
        legend.setTextSize(15);
        legend.setYEntrySpace(25);//legend间距
        legend.setEnabled(true);//是否显示图例
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//图例相对于图表横向的位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例相对于图表纵向的位置
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);//图例显示的方向
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //设置饼图数据
        setPieChartData(pieChart, pieValues);

        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);

    }


    //设置饼图数据
    private void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {
        entries = new ArrayList<PieEntry>();
        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), "5000，50%/s"+"华为荣耀/s"+
                    DpUtils.dip2px(fragment.getActivity(),18)+"/s"+
                    DpUtils.dip2px(fragment.getActivity(),2)
                    ,entry.getKey().toString()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(0f);//设置饼块之间的间隔
        dataSet.setSelectionShift(0f);//设置饼块选中时偏离饼图中心的距离
        dataSet.setColors(PIE_COLORS);//设置饼块的颜色

        //设置数据显示方式有见图
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(0f);

        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


    class SalesAnalyseSalseFragmentAdapter extends BaseAdapter {


        @Override
        public int getItemViewType(int position) {
            if (position + 1 != list.size()) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TestBean bean = list.get(position);
            TextView inspect ;
            if (getItemViewType(position) == 0) {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item3, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                inspect = convertView.findViewById(R.id.shop_analyse_sales_item3_inspect);
                id.setText(bean.getId() + "");
                company.setText(bean.getCompany());
                sales.setText(bean.getSales());
            } else {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item4, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                inspect = convertView.findViewById(R.id.shop_analyse_sales_item3_inspect);
                id.setText(bean.getId() + "");
                company.setText(bean.getCompany());
                sales.setText(bean.getSales());
            }
            inspect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.getActivity().startActivity(new Intent(fragment.getActivity(), ShopAnalyseActivity.class));
                    fragment.getActivity().finish();
                }
            });
            return convertView;
        }
    }
}
