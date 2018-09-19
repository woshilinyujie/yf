package jh.zkj.com.yf.Presenter;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import jh.zkj.com.yf.Contract.RetailContract;
import jh.zkj.com.yf.Fragment.Retail.RetailFragment;

/**
 * Created by wdefer
 * on 2018/9/19
 */
public class RetailPresenter implements RetailContract.IRetailPresenter{

    private final RetailFragment fragment;
    private String[] titles;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public RetailPresenter(RetailFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void initView() {
        titles = new String[]{"全部订单", "未收款","已收款"};

    }
}
