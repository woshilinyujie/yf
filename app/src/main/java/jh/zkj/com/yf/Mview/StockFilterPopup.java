package jh.zkj.com.yf.Mview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Bean.StockFilterBean;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/17
 * use  库存页面的筛选popup
 */
public class StockFilterPopup extends PopupWindow {

    //重置
    public static final int CLICK_TYPE_RESET = 0;
    //确认
    public static final int CLICK_TYPE_CONFIRM = 10;

    @BindView(R.id.stock_filter_com_text)
    TextView comText;
    @BindView(R.id.stock_filter_warehouse_text)
    TextView warehouseText;
    @BindView(R.id.stock_filter_classification_text)
    TextView classificationText;
    @BindView(R.id.stock_filter_brand_text)
    TextView brandText;
    @BindView(R.id.stock_filter_model_text)
    TextView modelText;

    private View view;
    private Listener listener;
    private StockFilterBean filterBean;

    public StockFilterPopup(Context context) {
        view = View.inflate(context, R.layout.popup_stock_filter, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x66000000));
        setAnimationStyle(R.style.select_Popup);
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
            , R.id.stock_filter_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //公司
            case R.id.stock_filter_company: {
                if (listener != null) {
                    listener.onItemClick(StockConfig.STATUS_TYPE_COMPANY);
                }
                break;
            }
            //仓库
            case R.id.stock_filter_warehouse: {
                if (listener != null) {
                    listener.onItemClick(StockConfig.STATUS_TYPE_WAREHOUSE);
                }
                break;
            }
            //商品分类
            case R.id.stock_filter_classification: {
                if (listener != null) {
                    listener.onItemClick(StockConfig.STATUS_TYPE_CLASSIFICATION);
                }
                break;
            }
            //品牌
            case R.id.stock_filter_brand: {
                if (listener != null) {
                    listener.onItemClick(StockConfig.STATUS_TYPE_BRAND);
                }
                break;
            }
            //型号
            case R.id.stock_filter_model: {
                if (listener != null) {
                    listener.onItemClick(StockConfig.STATUS_TYPE_MODEL);
                }
                break;
            }
            //重置
            case R.id.stock_filter_reset: {
                if (listener != null) {
                    listener.onItemClick(CLICK_TYPE_RESET);
                }
                break;
            }
            //确认
            case R.id.stock_filter_confirm: {
                if (listener != null) {
                    listener.onItemClick(CLICK_TYPE_CONFIRM);
                }
                break;
            }
        }
    }

    public void setWarehouseHide(int hide) {
        view.findViewById(R.id.stock_filter_warehouse).setVisibility(hide);
    }

    public void setData(StockFilterBean filterBean){
        this.filterBean = filterBean;
        comText.setText(filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getName());
        warehouseText.setText(filterBean.isEmptyWarehouseBean() ? "" : filterBean.getWarehouseBean().getName());
        classificationText.setText(filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getName());
        brandText.setText(filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getName());
        modelText.setText(filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getName());
    }

    //重置
    public void reset() {
        comText.setText("");
        warehouseText.setText("");
        classificationText.setText("");
        brandText.setText("");
        modelText.setText("");
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onItemClick(int position);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h - yoff);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }
}
