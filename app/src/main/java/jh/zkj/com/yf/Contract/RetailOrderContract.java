package jh.zkj.com.yf.Contract;

import android.widget.FrameLayout;

/**
 * Created by wdefer
 * on 2018.9.19
 */

public class RetailOrderContract {

    public interface IRetailOrderView{
        //获取fragment显示的layout
        public FrameLayout getFragmentLayout();
    }

    public interface IRetailOrderPresenter{
        //初始化fragment
        public void initFragment(int frameId);
    }
}
