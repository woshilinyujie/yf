package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.R;

public class PhoneActivity extends MBaseActivity {

    @BindView(R.id.phone_black)
    TitleLayout phoneBlack;
    @BindView(R.id.phone_new)
    TextView phoneNew;
    @BindView(R.id.phone_input_phone)
    EditText phoneInputPhone;
    @BindView(R.id.phone_send_code)
    Button phoneSendCode;
    @BindView(R.id.phone_input_code)
    EditText phoneInputCode;
    @BindView(R.id.phone_save)
    Button phoneSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.phone_black, R.id.phone_send_code, R.id.phone_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_black:
                break;
            case R.id.phone_send_code:
                break;
            case R.id.phone_save:
                break;
        }
    }
}
