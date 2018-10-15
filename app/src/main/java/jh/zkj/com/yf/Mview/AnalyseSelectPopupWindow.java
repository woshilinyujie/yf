package jh.zkj.com.yf.Mview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Mutils.DateUtil;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.ShopAnalysePresent;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/13.
 * 数据分析店铺和和时间选择
 */

public class AnalyseSelectPopupWindow extends PopupWindow {

    private final View view;
    Context context;
    @BindView(R.id.shop_select_today)
    Button shopSelectToday;
    @BindView(R.id.shop_select_week)
    Button shopSelectWeek;
    @BindView(R.id.shop_select_month)
    Button shopSelectMonth;
    @BindView(R.id.shop_select_begin)
    TextView shopSelectBegin;
    @BindView(R.id.shop_select_end)
    TextView shopSelectEnd;
    @BindView(R.id.shop_select_classified)
    TextView shopSelectClassified;
    @BindView(R.id.shop_select_brand)
    TextView shopSelectBrand;
    @BindView(R.id.shop_select_model)
    TextView shopSelectModel;
    @BindView(R.id.shop_select_replace)
    Button shopSelectReplace;
    @BindView(R.id.shop_select_sure)
    Button shopSelectSure;
    @BindView(R.id.shop_select_bg)
    View shopSelectBg;
    DataSelect dataSelect;
    DataSelect dataSelect1;
    DataSelect dataSelect2;
    ShopAnalysePresent.SelectShopDateListener selectShopDateListener;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
            }
        }
    };
    private TimePickerView pvTime;

    public AnalyseSelectPopupWindow(final Context context) {
        super(context);
        this.context = context;
        view = View.inflate(context, R.layout.shop_select_layout, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置可以获取集点
        setFocusable(true);
        //可触摸
        setTouchable(true);
        //设置进出动画
        setAnimationStyle(R.style.select_Popup);
        /**
         * 实例化一个ColorDrawable颜色为半透明
         * 设置SelectPicPopupWindow弹出窗体的背景
         */
        ColorDrawable dw = new ColorDrawable(0x00000000);
        setBackgroundDrawable(dw);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
//                backgroundAlpha((Activity) context, 1.0f);
            }
        });
        initPickerView();
    }

    public void showPopup(View view) {
        //设置显示位置
        if (!isShowing()) {
            showAsDropDown(view);
//            backgroundAlpha((Activity)context , 0.5f);//0.0-1.0
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    //日期选择初始化
    public void initPickerView() {
        //默认日期
        shopSelectBegin.setText(DateUtil.getInstance().getDayOrMonthOrYear(System.currentTimeMillis()));
        shopSelectEnd.setText(DateUtil.getInstance().getDayOrMonthOrYear(System.currentTimeMillis()));
        pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选着时间成功后回调
                dataSelect2.DataSelectSuccess(date);
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }


    @OnClick({R.id.shop_select_today, R.id.shop_select_week, R.id.shop_select_month, R.id.shop_select_begin, R.id.shop_select_end, R.id.shop_select_classified, R.id.shop_select_brand, R.id.shop_select_model, R.id.shop_select_replace, R.id.shop_select_sure, R.id.shop_select_bg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_select_today://今日
                selectColor(shopSelectToday);
                unSelectColor(shopSelectWeek);
                unSelectColor(shopSelectMonth);
                shopSelectBegin.setText(DateUtil.getInstance().getDayOrMonthOrYear(System.currentTimeMillis()));
                shopSelectEnd.setText(DateUtil.getInstance().getDayOrMonthOrYear(System.currentTimeMillis()));
                break;
            case R.id.shop_select_week://本周
                selectColor(shopSelectWeek);
                unSelectColor(shopSelectToday);
                unSelectColor(shopSelectMonth);
                shopSelectBegin.setText(DateUtil.getInstance().getDayOrMonthOrYear(DateUtil.getInstance().getBeginDayOfWeek().getTime()));
                shopSelectEnd.setText(DateUtil.getInstance().getDayOrMonthOrYear(System.currentTimeMillis()));
                break;
            case R.id.shop_select_month://本月
                selectColor(shopSelectMonth);
                unSelectColor(shopSelectToday);
                unSelectColor(shopSelectWeek);
                shopSelectBegin.setText(DateUtil.getInstance().getDayOrMonthOrYear(DateUtil.getInstance().getBeginDayOfMonth().getTime()));
                shopSelectEnd.setText(DateUtil.getInstance().getDayOrMonthOrYear(System.currentTimeMillis()));
                break;
            case R.id.shop_select_begin://开始日期
                if (dataSelect == null)
                    dataSelect = new DataSelect() { //选着时间成功后处理
                        @Override
                        public void DataSelectSuccess(Date date) {
                            shopSelectBegin.setText(DateUtil.getInstance().getDayOrMonthOrYear(date.getTime()));
                            unSelectColor(shopSelectToday);
                            unSelectColor(shopSelectWeek);
                            unSelectColor(shopSelectMonth);

                        }
                    };
                pvTime.show(shopSelectBegin);
                dataSelect2 = dataSelect;
                break;
            case R.id.shop_select_end://结束日期
                if (dataSelect1 == null)
                    dataSelect1 = new DataSelect() {
                        @Override
                        public void DataSelectSuccess(Date date) { //选着时间成功后处理
                            String startTimeS = shopSelectBegin.getText().toString();
                            try {
                                Date startTime = DateUtil.getInstance().stringToDate(startTimeS, "yyyy-MM-dd");
                                if (date.getTime() >= startTime.getTime()) {
                                    shopSelectEnd.setText(DateUtil.getInstance().getDayOrMonthOrYear(date.getTime()));
                                    unSelectColor(shopSelectToday);
                                    unSelectColor(shopSelectWeek);
                                    unSelectColor(shopSelectMonth);
                                } else {
                                    MToast.makeText(context, "结束时间必须大于等于开始时间", Toast.LENGTH_SHORT).show();

                                }
                            } catch (Exception e) {
                                Log.e("时间格式转换错误: ", e.toString());
                            }
                        }
                    };
                pvTime.show(shopSelectEnd);
                dataSelect2 = dataSelect1;
                break;
            case R.id.shop_select_classified://商品分类
                break;
            case R.id.shop_select_brand://商品品牌
                break;
            case R.id.shop_select_model://商品型号
                break;
            case R.id.shop_select_replace://重置
                break;
            case R.id.shop_select_sure://确定
                if (true) {
                    if (selectShopDateListener != null) {
                        selectShopDateListener.SelectShopDate(shopSelectBegin.getText().toString(), shopSelectEnd.getText().toString()
                                , shopSelectClassified.getText().toString(), shopSelectBrand.getText().toString(), shopSelectModel.getText().toString());
                    }
                    dismiss();
                }
                break;
            case R.id.shop_select_bg://
                dismiss();
                break;
        }
    }

    public void selectColor(Button view) {
        view.setTextColor(Color.parseColor("#6fb1fc"));
        view.setBackgroundResource(R.drawable.shape_radius_4_e6f0fc);
    }

    public void unSelectColor(Button view) {
        view.setTextColor(Color.parseColor("#333333"));
        view.setBackgroundResource(R.drawable.shape_radius_4_f6f7fb);
    }

    public boolean judge() {
        if (shopSelectClassified.getText().equals("请选择")) {
            MToast.makeText(context, "请选择商品分类", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (shopSelectBrand.getText().equals("请选择")) {
            MToast.makeText(context, "请选择商品品牌", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (shopSelectModel.getText().equals("请选择")) {
            MToast.makeText(context, "请选择商品型号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    interface DataSelect {
        public void DataSelectSuccess(Date date);


    }

    public void setSelectDateListener(ShopAnalysePresent.SelectShopDateListener selectShopDateListener) {
        this.selectShopDateListener = selectShopDateListener;
    }

}
