package jh.zkj.com.yf.Contract.Order;

/**
 * Created by wdefer
 * 2018/10/10
 * use
 */
public class SelectCommodityContract {
    public interface ISelectCommodityView{
        //设置公司名字
        void setStoreName(String s);
        //控制购物车显示隐藏
        void setComCarVisibility(int visibility);
    }

    public interface ISelectCommodityPresenter{
        //显示购物车
        void showComCar();
    }
}
