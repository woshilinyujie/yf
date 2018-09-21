package jh.zkj.com.yf.Contract.Retail;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018.9.19
 */

public class RetailOrderContract {

    public interface IRetailOrderView{
        //获取fragment显示的layout
        public FrameLayout getFragmentLayout();
        public EditText getUserName();
        public EditText getUserPhone();
        public RecyclerView getRecyclerView();
        public TitleLayout getTitleLayout();

    }

    public interface IRetailOrderPresenter{
        void activityFinish();
    }
}
