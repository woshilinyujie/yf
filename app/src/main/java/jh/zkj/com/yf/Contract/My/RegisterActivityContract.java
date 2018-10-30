package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/10/30.
 */

public class RegisterActivityContract  {
    public interface RegisterActivityView{
        void setNextBg(int Resourse);
        void setSendCodeText(String s);
        void setSendCodeColor(String color);
        void showToast(String s);
    }
    public interface RegisterActivityPresente{
        void initListener();
        void initDate();
        void sendCode();//发送验证码
        void Next();//下一步
    }
}
