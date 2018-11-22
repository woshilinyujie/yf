package jh.zkj.com.yf.Activity.Order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.ielse.view.SwitchView;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Bean.PrintStyleBean;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/19.
 */

public class PrintSettingActivity extends MBaseActivity {
    //客户
    @BindView(R.id.print_setting_client)
    SwitchView client;
    //联系电话
    @BindView(R.id.print_setting_phone)
    SwitchView phone;
    //商品编号
    @BindView(R.id.print_setting_com_num)
    SwitchView comNum;
    //商品名称
//    @BindView(R.id.print_setting_com_name)
//    SwitchView comName;
    //业务员
    @BindView(R.id.print_setting_salesman)
    SwitchView salesman;
    //制单人
    @BindView(R.id.print_setting_create_name)
    SwitchView createName;
    //单据号
    @BindView(R.id.print_setting_serial_no)
    SwitchView serialNo;
    //单据日期
    @BindView(R.id.print_setting_time)
    SwitchView time;
    //单价
    @BindView(R.id.print_setting_price)
    SwitchView price;
    //金额
    @BindView(R.id.print_setting_money)
    SwitchView money;
    //结算账户
    @BindView(R.id.print_setting_harvest)
    SwitchView harvest;
    //备注
    @BindView(R.id.print_setting_remark)
    SwitchView remark;
    //打印时间
    @BindView(R.id.print_setting_print_time)
    SwitchView printTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_setting);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back(){
        ArrayList<PrintStyleBean> arr = new ArrayList<>();
        arr.add(new PrintStyleBean("客户", client.isOpened()));
        arr.add(new PrintStyleBean("联系电话", phone.isOpened()));
//        arr.add(new PrintStyleBean("商品编号", comNum.isOpened()));
//        arr.add(new PrintStyleBean("商品名称", comName.isOpened()));
        arr.add(new PrintStyleBean("业务员", salesman.isOpened()));
        arr.add(new PrintStyleBean("制单人", createName.isOpened()));
        arr.add(new PrintStyleBean("单据号", serialNo.isOpened()));
        arr.add(new PrintStyleBean("单据日期", time.isOpened()));
        arr.add(new PrintStyleBean("单价", price.isOpened()));
        arr.add(new PrintStyleBean("金额", money.isOpened()));
        arr.add(new PrintStyleBean("结算方式", harvest.isOpened()));
        arr.add(new PrintStyleBean("备注", remark.isOpened()));
        arr.add(new PrintStyleBean("打印时间", printTime.isOpened()));
        Intent intent = new Intent();
        intent.putExtra("print_open", arr);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
