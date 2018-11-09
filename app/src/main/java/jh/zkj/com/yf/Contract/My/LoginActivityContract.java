package jh.zkj.com.yf.Contract.My;

import android.content.Context;

/**
 * Created by linyujie on 18/10/29.
 */

public class LoginActivityContract {
    public interface LoginActivityView {
        void setPhone(String s);

        void setPassWord(String s);

        void setEnterBg(int resoues);
        void showToast(String msg);

    }

    public interface LoginActivityPresente {
        void initListener();
        void checkLogin();

        void loginCRM();

        void getCRMInfo(Context activity, String toke);

        void loginERP(Context context, String usernameType, String username, String password);
        void finish();
    }
}

