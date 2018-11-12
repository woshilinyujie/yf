package jh.zkj.com.yf.Fragment.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 */

public class LoginOneFragment extends MBaseFragment{
    private LoginActivity activity;
    private View rootView;

    public static LoginOneFragment newInstance() {
        LoginOneFragment fragment = new LoginOneFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (LoginActivity) getActivity();
        rootView = View.inflate(activity, R.layout.fragment_login_one, null);
        return rootView;
    }
}
