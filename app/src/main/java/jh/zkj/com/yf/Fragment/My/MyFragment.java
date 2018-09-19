package jh.zkj.com.yf.Fragment.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import jh.zkj.com.yf.Fragment.MBaseFragment;

/**
 * Created by linyujie on 18/9/19.
 */

public class MyFragment extends MBaseFragment {
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
