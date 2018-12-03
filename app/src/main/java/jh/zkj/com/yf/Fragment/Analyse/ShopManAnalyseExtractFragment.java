package jh.zkj.com.yf.Fragment.Analyse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 * 店员业绩分析 提成
 */

public class ShopManAnalyseExtractFragment extends MBaseFragment {
    public static ShopManAnalyseExtractFragment newInstance() {
        ShopManAnalyseExtractFragment fragment = new ShopManAnalyseExtractFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.sales_analyse_profit_layout, null);
        return inflate;
    }
}
