package jh.zkj.com.yf.Fragment.Analyse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Activity.Analyse.SalesAnalyseActivity;
import jh.zkj.com.yf.Activity.Analyse.ShopAnalyseActivity;
import jh.zkj.com.yf.Activity.Analyse.ShopManAnalyseActivity;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/15.
 * 分析
 */

public class AnalyseFragment extends MBaseFragment {
    @BindView(R.id.analyse_shop_rl)
    RelativeLayout analyseShopRl;
    @BindView(R.id.analyse_shop_man_rl)
    RelativeLayout analyseShopManRl;
    @BindView(R.id.analyse_sales_rl)
    RelativeLayout analyseSalesRl;
    Unbinder unbinder;
    private View rootView;

    public static AnalyseFragment newInstance() {
        AnalyseFragment fragment = new AnalyseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.analyse_fragment_layout, null);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.analyse_shop_rl, R.id.analyse_shop_man_rl, R.id.analyse_sales_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.analyse_shop_rl://门店经营分析
                Intent intent = new Intent(getActivity(), ShopAnalyseActivity.class);
                startActivity(intent);
                break;
            case R.id.analyse_shop_man_rl://店员业绩分析
                Intent intent1 = new Intent(getActivity(), ShopManAnalyseActivity.class);
                startActivity(intent1);
                break;
            case R.id.analyse_sales_rl://商品销量分析
                Intent intent2 = new Intent(getActivity(), SalesAnalyseActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
