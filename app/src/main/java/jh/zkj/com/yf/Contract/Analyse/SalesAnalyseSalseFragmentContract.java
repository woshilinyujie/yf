package jh.zkj.com.yf.Contract.Analyse;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

/**
 * Created by linyujie on 18/10/16.
 */

public class SalesAnalyseSalseFragmentContract {
    public  interface SalesAnalyseSalseFragmentView{
    }
    public  interface SalesAnalyseSalseFragmentPresent{
        void initListener();
        void initPopup();
        void getLinData();
    }
}
