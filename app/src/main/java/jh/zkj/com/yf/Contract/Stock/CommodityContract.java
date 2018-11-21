package jh.zkj.com.yf.Contract.Stock;

import android.content.Intent;

/**
 * Created by wdefer
 * 2018/11/15
 * use
 */
public class CommodityContract {
    public interface ICommodityView{
    }

    public interface ICommodityPresenter{
        void clearFindEt();
        void showFilterPopup();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
