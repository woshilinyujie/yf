package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/9/25.
 */

public class UserNameActivityContract  {
    public  interface IUserNameActivityView{
        void deleteName();
    }
    public  interface IUserNameActivityPresenter{
        void initListener();
        void deleteName();
    }
}
