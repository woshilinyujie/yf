package jh.zkj.com.yf.Mview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/17
 * use  库存页面的筛选popup
 */
public class StockFilterPopup extends PopupWindow {

    //公司
    public static final int CLICK_TYPE_COMPANY = 1;
    //仓库
    public static final int CLICK_TYPE_WAREHOUSE = 2;
    //商品分类
    public static final int CLICK_TYPE_CLASSIFICATION = 3;
    //品牌
    public static final int CLICK_TYPE_BRAND = 4;
    //型号
    public static final int CLICK_TYPE_MODEL = 5;
    //重置
    public static final int CLICK_TYPE_RESET = 0;
    //确认
    public static final int CLICK_TYPE_CONFIRM = 10;

    private View view;
    private Listener listener;

    public StockFilterPopup(Context context) {
        view = View.inflate(context, R.layout.popup_stock_filter, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x66000000));
        setAnimationStyle(R.style.default_popup_animation);
        setOutsideTouchable(true);
        setFocusable(true);
        setClippingEnabled(false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
    @OnClick({R.id.stock_filter_company, R.id.stock_filter_warehouse
            , R.id.stock_filter_classification, R.id.stock_filter_brand
            , R.id.stock_filter_model, R.id.stock_filter_reset
            , R.id.stock_filter_confirm, R.id.stock_filter_close})
    public void onViewClicked(View view){
        switch (view.getId()){
            //公司
            case R.id.stock_filter_company:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_COMPANY);
                }
                break;
            }
            //仓库
            case R.id.stock_filter_warehouse:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_WAREHOUSE);
                }
                break;
            }
            //商品分类
            case R.id.stock_filter_classification:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_CLASSIFICATION);
                }
                break;
            }
            //品牌
            case R.id.stock_filter_brand:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_BRAND);
                }
                break;
            }
            //型号
            case R.id.stock_filter_model:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_MODEL);
                }
                break;
            }
            //重置
            case R.id.stock_filter_reset:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_RESET);
                }
                break;
            }
            //确认
            case R.id.stock_filter_confirm:{
                if(listener != null){
                    listener.onItemClick(CLICK_TYPE_CONFIRM);
                }
                break;
            }
            //关闭
            case R.id.stock_filter_close:{
                dismiss();
                break;
            }
        }
    }

    //重置
    public void reset() {

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onItemClick(int position);
    }
}
