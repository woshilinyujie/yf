package jh.zkj.com.yf.Presenter.Analyse;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

import jh.zkj.com.yf.Bean.TestBean;
import jh.zkj.com.yf.Contract.Analyse.ShopAnalyseMoneyFragmentContract;
import jh.zkj.com.yf.Fragment.Analyse.ShopAnalyseSalseMoneyFragment;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.MyMarkerView;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalyseSalseMoneyFragmentPresenter implements ShopAnalyseMoneyFragmentContract.ShopAnalyseMoneyFragmentPresent {
    private final FragmentActivity context;
    ShopAnalyseSalseMoneyFragment fragment;
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


    public ShopAnalyseSalseMoneyFragmentPresenter(ShopAnalyseSalseMoneyFragment fragment) {
        this.fragment = fragment;
        context =fragment.getActivity();
        initChart();
        initPieChar();
        initTable();
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
        ShopAnalyseSalseMoneyFragmentAdapter adapter = new ShopAnalyseSalseMoneyFragmentAdapter();
        fragment.getSalesMoneyTableList().setAdapter(adapter);
    }

    private void initPieChar() {
        PieChart pieChart = fragment.getSalesMoneyPieChart();
        //模拟数据
        HashMap dataMap = new HashMap();
        dataMap.put("A", "300");
        dataMap.put("B", "600");
        dataMap.put("C", "500");
        dataMap.put("D", "800");
        setPieChart(pieChart, dataMap, "数据", true);
    }

    @Override
    public void initChart() {
        mLineChart = fragment.getSalesMoneyChart();
        LineData mLineData = getLineData(30, 100);
        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));
    }

    @Override
    public LineData getLineData(int count, float range) {
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues.add(new Entry(i, value));
        }

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "");
        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1); // 线宽
        lineDataSet.setCircleSize(3.5f);// 显示的圆形大小
        lineDataSet.setCircleHoleColor(Color.parseColor("#657ff6"));
        lineDataSet.setColor(Color.parseColor("#657ff6"));// 显示颜色
        lineDataSet.setCircleColor(Color.parseColor("#657ff6"));// 圆形的颜色
        lineDataSet.setHighLightColor(Color.parseColor("#d9d9d9")); // 高亮的线的颜色
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets
        LineData lineData = new LineData(lineDataSets);
        XAxis xl = mLineChart.getXAxis();
        YAxis yRight = mLineChart.getAxisRight();
        yRight.setEnabled(false);
        YAxis yLeft = mLineChart.getAxisLeft();
        yLeft.setDrawAxisLine(false);
        yLeft.setGridColor(Color.parseColor("#e2e2e2"));//网格线颜色
        yLeft.setTextColor(Color.parseColor("#a6a6a6"));
        xl.setEnabled(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawGridLines(false);
        xl.setDrawAxisLine(false);
        xl.setTextColor(Color.parseColor("#a6a6a6"));
//        final String[] valueArry = {"10.1", "10.2", "10.3", "10.4", "10.5","10.6", "10.7"};
//        final Map<Integer, String> xMap = new HashMap<>();
//        for (int i = 0; i < yValues.size(); i++) {
//            xMap.put((int) yValues.get(i).getX(), valueArry[i]);
//        }
//
//        xl.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xMap.get((int)value);
//            }
//        });
        MyMarkerView mv = new MyMarkerView(fragment.getActivity(),
                R.layout.custom_marker_view);
        mv.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(mv);
        Description description = new Description();
        description.setText("");
        mLineChart.setDescription(description);
        return lineData;
    }

    @Override
    public void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false); //是否在折线图上添加边框
        lineChart.setDrawGridBackground(true); // 是否显示表格颜色
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放
        lineChart.setPinchZoom(false);//
        lineChart.setBackgroundColor(Color.parseColor("#ffffff"));// 设置背景
        lineChart.setData(lineData); // 设置数据
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(0);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
        mLineChart.getViewPortHandler().getMatrixTouch().postScale(9.8f, 1f);//默认缩放
        lineChart.setGridBackgroundColor(Color.parseColor("#f6f7fb"));
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
//        dataSet.setValueLinePart1OffsetPercentage(60f);//数据连接线距图形片内部边界的距离，为百分数
//        dataSet.setValueLinePart1Length(0.3f);
//        dataSet.setValueLinePart2Length(0.8f);
//        dataSet.setValueLineColor(Color.parseColor("#ea6330"));//设置连接线的颜色
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(0f);

        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


    class ShopAnalyseSalseMoneyFragmentAdapter extends BaseAdapter {

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
            if (getItemViewType(position) == 0) {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item1, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                id.setText(bean.getId() + "");
                company.setText(bean.getCompany());
                sales.setText(bean.getSales());
            } else {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item2, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                id.setText(bean.getId() + "");
                company.setText(bean.getCompany());
                sales.setText(bean.getSales());
            }
            return convertView;
        }
    }
}
