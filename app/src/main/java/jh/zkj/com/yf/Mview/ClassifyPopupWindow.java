package jh.zkj.com.yf.Mview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Listener.ClassifySelectListener;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 * 分类选着  popup
 */

public class ClassifyPopupWindow extends PopupWindow {
    private final View view;
    @BindView(R.id.classify_popup_tv1)
    TextView classifyPopupTv1;
    @BindView(R.id.classify_popup_tv2)
    TextView classifyPopupTv2;
    @BindView(R.id.classify_popup_tv3)
    TextView classifyPopupTv3;
    @BindView(R.id.classify_popup_tv4)
    TextView classifyPopupTv4;
    private Context context;
    private ClassifySelectListener listener;

    public ClassifyPopupWindow(Context context) {

        this.context = context;
        view = View.inflate(context, R.layout.classify_popup_layout, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        //设置宽与高
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置可以获取集点
        setFocusable(true);
        //可触摸
        setTouchable(true);
        //设置进出动画
        setAnimationStyle(R.style.select_Popup);
    }

    public void showPopup(View view) {
        //设置显示位置
        if (!isShowing()) {
            showAsDropDown(view);
        }
    }

    @OnClick({R.id.classify_popup_tv1, R.id.classify_popup_tv2, R.id.classify_popup_tv3, R.id.classify_popup_tv4})
    public void onViewClicked(View view) {
        if(listener!=null){
            switch (view.getId()) {
                case R.id.classify_popup_tv1://按品牌
                    setTextColor(classifyPopupTv1,classifyPopupTv2,classifyPopupTv3,classifyPopupTv4);
                    listener.onClassifySelect("按品牌","1");
                    dismiss();
                    break;
                case R.id.classify_popup_tv2://按分类
                    setTextColor(classifyPopupTv2,classifyPopupTv1,classifyPopupTv3,classifyPopupTv4);
                    listener.onClassifySelect("按分类","2");
                    dismiss();
                break;
                case R.id.classify_popup_tv3://按型号
                    setTextColor(classifyPopupTv3,classifyPopupTv2,classifyPopupTv1,classifyPopupTv4);
                    listener.onClassifySelect("按型号","3");
                    dismiss();
                break;
                case R.id.classify_popup_tv4://按商品全称
                    listener.onClassifySelect("按商品全称","4");
                    setTextColor(classifyPopupTv4,classifyPopupTv2,classifyPopupTv3,classifyPopupTv1);
                    dismiss();
                    break;
            }
        }
    }

    public void setTextColor(TextView view1,TextView view2,TextView view3,TextView view4) {
        view1.setTextColor(Color.parseColor("#6fb1fc"));
        view2.setTextColor(Color.parseColor("#ffffff"));
        view3.setTextColor(Color.parseColor("#ffffff"));
        view4.setTextColor(Color.parseColor("#ffffff"));
    }

    public void setClassifyListener(ClassifySelectListener listener){
        this.listener=listener;
    }
}
