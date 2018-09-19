package jh.zkj.com.yf.Fragment.Retail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jh.zkj.com.yf.Contract.RetailListContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/19
 * use 零售单中的列表
 */
public class RetailListFragment extends MBaseFragment implements RetailListContract.IRetailView{

    public static RetailListFragment newInstance() {
        RetailListFragment fragment = new RetailListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retail_list, null);
        ButterKnife.bind(view);
        return view;
    }
}
