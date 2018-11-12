package jh.zkj.com.yf.Mview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/6
 * use 取消订单dialog
 */
public class OrderCancelPopup extends PopupWindow {
    private final String orderId;
    @BindView(R.id.order_cancel_title)
    TextView title;
    @BindView(R.id.order_cancel_remark)
    EditText remark;
    @BindView(R.id.order_cancel_remark_num)
    TextView remarkNum;
    @BindView(R.id.order_cancel_success)
    TextView success;
    @BindView(R.id.order_cancel_close)
    ImageView close;
    private LayoutInflater inflater;
    private Listener listener;
    private Context context;

    public OrderCancelPopup(@NonNull Context context, String orderId) {
        super(context);
        inflater = LayoutInflater.from(context);
        this.orderId = orderId;
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = inflater.inflate(R.layout.dialog_order_cancel, null);
        ButterKnife.bind(this, view);
        setContentView(view);

        title.setText("确定要取消订单：" + orderId + "吗？");
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x66000000));
        setAnimationStyle(R.style.default_popup_animation);
        setOutsideTouchable(true);
        setFocusable(true);
        setClippingEnabled(false);

        setListener();
    }

    private void setListener() {
        remark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                remarkNum.setText(remark.getText().toString().length() + "/50");
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    if(TextUtils.isEmpty(remark.getText().toString())){
                        MToast.makeText(context, "前输入取消原因", MToast.LENGTH_LONG).show();
                    } else {
                        listener.onSuccessClick(orderId, remark.getText().toString());
                    }
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setOnSuccessClickListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onSuccessClick(String orderId, String reason);
    }
}
