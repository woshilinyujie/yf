package jh.zkj.com.yf.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Fragment.My.MyFragment;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/20.
 * 等待收款
 */

public class HomeWaitReceivablesFragment extends MBaseFragment{

    private View view;

    public static HomeWaitReceivablesFragment newInstance() {
        HomeWaitReceivablesFragment fragment = new HomeWaitReceivablesFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.wait_receivables_layout,null);
        return view;
    }

    @Override
    public View getView() {
        return view;
    }
}
