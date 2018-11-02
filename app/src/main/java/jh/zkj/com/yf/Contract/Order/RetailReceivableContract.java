package jh.zkj.com.yf.Contract.Order;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018/9/21
 * use
 */
public class RetailReceivableContract {
    public interface IRetailOrderView{

    }

    public interface IRetailOrderPresenter{
        //添加或修改支付方式
        void harvestMode();
        //
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }
}
