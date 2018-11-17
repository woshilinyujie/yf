package jh.zkj.com.yf.Activity.My;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Bean.RegisterBean;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/14
 * use 添加新企业成功
 */
public class CreateEnterpriseSuccessActivity extends MBaseActivity {
    @BindView(R.id.create_enterprise_success_code)
    TextView code;
    private RegisterBean registerBean;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_enterprise_success);
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();

    }

    @OnClick({R.id.create_enterprise_success_login, R.id.create_enterprise_success_break})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.create_enterprise_success_login:{
                if(registerBean != null){
                    Intent intent=new Intent(this, LoginActivity.class);
                    intent.putExtra("uuid",registerBean.getData().getUuid());
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    EventBus.getDefault().post("RegisterFinish");
//                    setResult(Activity.RESULT_FIRST_USER);
//                    finish();
                }
                break;
            }
            case R.id.create_enterprise_success_break:{
                setResult(Activity.RESULT_OK);
                finish();
                break;
            }
        }
    }

    private void initView() {}

    private void initData() {
        registerBean = (RegisterBean)getIntent().getSerializableExtra("RegisterBean");
        phone = getIntent().getStringExtra("phone");

        if(registerBean != null){
            code.setText(registerBean.getData().getCode());
        }
    }

    private void initListener() {}
}
