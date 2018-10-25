package jh.zkj.com.yf.Contract.Order;

import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by wdefer
 * 2018/10/10
 * use
 */
public class SelectCommodityContract {
    public interface ISelectCommodityView{
        //设置公司名字
        void setStoreName(String s);
    }

    public interface ISelectCommodityPresenter{
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }
}
