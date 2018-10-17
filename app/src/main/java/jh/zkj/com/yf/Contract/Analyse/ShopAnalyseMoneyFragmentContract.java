package jh.zkj.com.yf.Contract.Analyse;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalyseMoneyFragmentContract {
    public  interface ShopAnalyseMoneyFragmentView{
    }
    public  interface ShopAnalyseMoneyFragmentPresent{
        void initChart();
        LineData getLineData(int count, float range);//线图初始化
        void showChart(LineChart lineChart, LineData lineData, int color);//线图配置
    }
}
