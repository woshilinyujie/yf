package jh.zkj.com.yf.Contract.Order;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018/9/20
 * use
 */
public class OrderDetailsContract {
    public interface IRetailOrderView{
        //去收款隐藏显示
        void setReceivablesVisibility(int visibility);
        //scrollview滑动开关
        void setNestedScrollingEnabled(boolean b);
    }

    public interface IRetailOrderPresenter{
        void toReceivables();
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }
}
