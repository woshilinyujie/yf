package jh.zkj.com.yf.Presenter.Retail;

import jh.zkj.com.yf.Activity.RetailOrderActivity;
import jh.zkj.com.yf.Contract.Retail.RetailOrderContract;
import jh.zkj.com.yf.Fragment.Retail.RetailOrderFragment;

/**
 * Created by wdefer
 * on 2018.9.19
 */
public class RetailOrderPresenter implements RetailOrderContract.IRetailOrderPresenter{

    private final RetailOrderActivity activity;
    private RetailOrderFragment fragment;

    public RetailOrderPresenter(RetailOrderActivity activity){
        this.activity = activity;
    }

    @Override
    public void initFragment(int frameId) {
        android.support.v4.app.FragmentTransaction transaction =
                activity.getSupportFragmentManager().beginTransaction();
        if (fragment == null) {
            fragment = RetailOrderFragment.newInstance();
            transaction.add(frameId, fragment);
        }
        transaction.show(fragment);
        transaction.commit();
    }
}