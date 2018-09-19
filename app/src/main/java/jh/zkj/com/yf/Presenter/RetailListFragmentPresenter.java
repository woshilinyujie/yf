package jh.zkj.com.yf.Presenter;

import jh.zkj.com.yf.Contract.RetailListFragmentContract;
import jh.zkj.com.yf.Fragment.RetailList.RetailListFragment;

/**
 * Created by wdefer
 * on 2018/9/19
 */
public class RetailListFragmentPresenter implements RetailListFragmentContract.IRetailListPresenter{

    private final RetailListFragment fragment;

    public RetailListFragmentPresenter(RetailListFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void initView() {

    }
}
