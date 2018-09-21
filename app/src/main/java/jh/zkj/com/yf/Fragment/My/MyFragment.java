package jh.zkj.com.yf.Fragment.My;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/19.
 */

public class MyFragment extends MBaseFragment {
    Activity activity;
    private View rootView;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity=getActivity();
        rootView = View.inflate(activity, R.layout.my_fragment_layout, null);
        return rootView;
    }
}
