package jh.zkj.com.yf.Presenter.My;

import android.text.TextUtils;

import jh.zkj.com.yf.Activity.My.UserNameActivity;
import jh.zkj.com.yf.Contract.My.UserNameActivityContract;

/**
 * Created by linyujie on 18/9/25.
 */

public class UserNameActivityPresenter implements UserNameActivityContract.IUserNameActivityPresenter {
    UserNameActivity activity;

    public UserNameActivityPresenter(UserNameActivity activity) {
        this.activity = activity;
        initListener();
    }


    @Override
    public void initListener() {

    }

    @Override
    public void deleteName() {
        if (!TextUtils.isEmpty(activity.getUsernameName().getText().toString())) {
            activity.deleteName();
        }
    }
}
