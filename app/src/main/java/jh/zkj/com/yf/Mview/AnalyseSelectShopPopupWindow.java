package jh.zkj.com.yf.Mview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Bean.AnalyseShopBean;
import jh.zkj.com.yf.Presenter.My.ShopAnalysePresent;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/13.
 * 数据分析店铺选择
 */

public class AnalyseSelectShopPopupWindow extends PopupWindow {

    private final View view;
    Context context;
    @BindView(R.id.select_shop_list_view)
    ListView selectShopListView;
    @BindView(R.id.select_shop_gb)
    View selectShopGb;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
            }
        }
    };
    private List<AnalyseShopBean> data;
    private AnalyseSelectShopPopupWindowAdapter adapter;
    ShopAnalysePresent.selectShopListener selectShopListener;

    public AnalyseSelectShopPopupWindow(final Context context) {
        super(context);
        this.context = context;
        view = View.inflate(context, R.layout.shop_select_shop_layout, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置可以获取集点
        setFocusable(true);
        //可触摸
        setTouchable(true);
        //初始化数据
        initData();
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
        handler.sendEmptyMessageDelayed(0, 250);
    }

    private void initData() {
        if (data == null) {
            data = new ArrayList<AnalyseShopBean>();
            for (int x = 0; x < 10; x++) {
                AnalyseShopBean analyseShopBean = new AnalyseShopBean();
                analyseShopBean.setShopName("店铺名称" + x);
                data.add(analyseShopBean);
            }
        }
        if(adapter==null)
        adapter = new AnalyseSelectShopPopupWindowAdapter();
        selectShopListView.setAdapter(adapter);
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


    class AnalyseSelectShopPopupWindowAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final AnalyseShopBean analyseShopBean = data.get(position);
            PopupWindowHolder holder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.analyse_select_shop_popup_item, null);
                holder = new PopupWindowHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (PopupWindowHolder) convertView.getTag();
            }
            holder.shopName.setText(analyseShopBean.getShopName());
            if (analyseShopBean.isSelect) {
                holder.shopName.setTextColor(Color.parseColor("#6fb1fc"));
                holder.bgIv.setVisibility(View.VISIBLE);
            } else {
                holder.shopName.setTextColor(Color.parseColor("#333333"));
                holder.bgIv.setVisibility(View.GONE);
            }
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (analyseShopBean.isSelect) {//点击已经是选中条目
                        dismiss();
                        return;
                    }
                    for (AnalyseShopBean bean : data) {
                        if (bean.isSelect) {
                            bean.isSelect = false;
                        }
                    }
                    analyseShopBean.isSelect = true;
                    if(selectShopListener!=null)
                        selectShopListener.SelectShop(analyseShopBean.getShopName());
                    dismiss();
                }
            });
            return convertView;
        }
    }

    class PopupWindowHolder {
        public TextView shopName;
        public ImageView bgIv;
        public RelativeLayout rl;

        public PopupWindowHolder(View view) {
            shopName = view.findViewById(R.id.shop_select_name);
            bgIv = view.findViewById(R.id.shop_select_iv);
            rl = view.findViewById(R.id.shop_select_rl);
        }
    }

    public void setSelectShopListener(ShopAnalysePresent.selectShopListener selectShopListener){
        this.selectShopListener=selectShopListener;
    }
}
