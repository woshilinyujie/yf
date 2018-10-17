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
 * Created by linyujie on 18/10/11.
 * 门店经营分析利润
 */

public class ShopAnalyseProfitFragment extends MBaseFragment {

    private View rootView;

    public static ShopAnalyseProfitFragment newInstance() {
        ShopAnalyseProfitFragment fragment = new ShopAnalyseProfitFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =View.inflate(getActivity(), R.layout.shop_analyse_profit_layout,null);
        return null;
    }
}
