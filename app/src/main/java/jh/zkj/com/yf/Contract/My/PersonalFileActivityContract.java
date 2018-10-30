package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/10/30.
 */

public class PersonalFileActivityContract {
    public interface PersonalFileActivityView{

    }

    public interface PersonalFileActivityPresente{
        void selectSexMan();
        void selectSexWomanMan();
        void selectAddress();
        void initJsonDate();
    }
}
