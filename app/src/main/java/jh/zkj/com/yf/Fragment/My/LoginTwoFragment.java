package jh.zkj.com.yf.Fragment.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 */

public class LoginTwoFragment extends MBaseFragment{

    private LoginActivity activity;
    private View rootView;

    public static LoginTwoFragment newInstance() {
        LoginTwoFragment fragment = new LoginTwoFragment();
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
        rootView = View.inflate(activity, R.layout.fragment_login_two, null);
        return rootView;
    }
}
