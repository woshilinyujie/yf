package jh.zkj.com.yf.Contract.My;

import android.widget.ImageView;

/**
 * Created by linyujie on 18/10/29.
 */

public class SelectCompanyActivityContract {
    public interface SelectCompanyActivityView{

    }
    public interface SelectCompanyActivityPresente{
        void initPager();
        void selectItem(int position, ImageView imageView);//判断默认选择的公司
    }
}
