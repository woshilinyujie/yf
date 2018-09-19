package jh.zkj.com.yf.Activity;

import android.os.Bundle;
import jh.zkj.com.yf.Contract.MainContract;
import jh.zkj.com.yf.R;

public class MainActivity extends MBaseActivity implements MainContract.IMainView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
