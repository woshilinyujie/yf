package jh.zkj.com.yf.Presenter;

import android.view.View;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Contract.MainContract;

/**
 * Created by linyujie on 18/9/17.
 */

public class MainPresenter implements MainContract.IMainPresenter {
    MainActivity activity;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }
}
