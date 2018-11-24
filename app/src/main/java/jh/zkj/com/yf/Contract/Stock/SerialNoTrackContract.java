package jh.zkj.com.yf.Contract.Stock;

import android.content.Intent;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SerialNoTrackContract {
    public interface ISerialNoTrackiew{
    }

    public interface ISerialNoTrackPresenter{
        void clearFindEt();
        void showFilterPopup();
        void openScan();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
