package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/10/29.
 */

public class LoginActivityContract {
    public interface LoginActivityView{
        void setPhone(String s);
        void setPassWord(String s);
        void setEnterBg(int resoues);

    }
    public interface LoginActivityPresente{
        void initListener();
    }
}
