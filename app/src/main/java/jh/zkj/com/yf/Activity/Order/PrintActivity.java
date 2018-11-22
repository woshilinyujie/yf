package jh.zkj.com.yf.Activity.Order;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Bean.PrintStyleBean;
import jh.zkj.com.yf.Mutils.print.AppInfo;
import jh.zkj.com.yf.Mutils.print.BluetoothActivity;
import jh.zkj.com.yf.Mutils.print.BluetoothController;
import jh.zkj.com.yf.Mutils.print.BtServiceOne;
import jh.zkj.com.yf.Mutils.print.PrintMsgEvent;
import jh.zkj.com.yf.Mutils.print.PrintUtil;
import jh.zkj.com.yf.Mutils.print.PrinterMsgType;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/19.
 */

public class PrintActivity extends BluetoothActivity {
    private final int REQUEST_PRINT_SETTING = 1;

    @BindView(R.id.print_name)
    TextView printName;
    @BindView(R.id.print_select)
    TextView printSelect;
    @BindView(R.id.print_sure)
    TextView printSure;
    public boolean mBtEnable = true;
    @BindView(R.id.print_title)
    TitleLayout printTitle;
    //店名
    @BindView(R.id.print_company_name)
    TextView companyName;
    //单据号
    @BindView(R.id.print_serial_no)
    TextView serialNo;
    //单据日期
    @BindView(R.id.print_create_time)
    TextView createTime;
    //客户姓名
    @BindView(R.id.print_client)
    TextView client;
    //联系号码
    @BindView(R.id.print_phone)
    TextView phone;
    //recycler
    @BindView(R.id.print_recycler)
    RecyclerView recycler;
    //总数量
    @BindView(R.id.print_total_count)
    TextView totalCount;
    //总金额
    @BindView(R.id.print_total_price)
    TextView totalPrice;
    //结算方式
    @BindView(R.id.print_harvest_model)
    LinearLayout hravestModel;
    //业务员
    @BindView(R.id.print_salesman)
    TextView salesman;
    //制单人
    @BindView(R.id.print_create_name)
    TextView createName;
    //备注
    @BindView(R.id.print_remake)
    TextView remake;
    //打印时间
    @BindView(R.id.print_time)
    TextView time;
    //xxx技术支持这种
    @BindView(R.id.print_bottom_tv)
    TextView bottomTv;
    private BluetoothController controller;
    int PERMISSION_REQUEST_COARSE_LOCATION = 2;
    private ArrayList<PrintStyleBean> printStyles = new ArrayList<>();
    private OrderAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        ButterKnife.bind(this);
        controller = new BluetoothController();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }
        EventBus.getDefault().register(this);

        String orderId = getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER);
        api = new OrderAPI(this);
        getQueryOrder(orderId);

        initListener();
    }

    private void initListener() {
        printTitle.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrintActivity.this, PrintSettingActivity.class);
                startActivityForResult(intent, REQUEST_PRINT_SETTING);
            }
        });
    }

    @OnClick({R.id.print_select, R.id.print_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.print_select:
                startActivity(new Intent(this, SearchBluetoothActivity.class));
                break;
            case R.id.print_sure:
                if (TextUtils.isEmpty(AppInfo.btAddress)) {
                    MToast.makeText(this, "请连接蓝牙...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, SearchBluetoothActivity.class));
                } else {
                    if (controller.getmAdapter().getState() == BluetoothAdapter.STATE_OFF) {//蓝牙被关闭时强制打开
                        controller.getmAdapter().enable();
                        MToast.makeText(this, "蓝牙被关闭请打开...", Toast.LENGTH_SHORT).show();
                    } else {
                        MToast.makeText(this, "打印测试...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BtServiceOne.class);
                        intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                        startService(intent);
                    }

                }
                break;
        }
    }

    public TextView getPrintName() {
        return printName;
    }

    public TextView getPrintSelect() {
        return printSelect;
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.init(this);
    }


    public void btStatusChanged(Intent intent) {
        super.btStatusChanged(intent);
        controller.init(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(PrintMsgEvent event) {
        if (event.type == PrinterMsgType.MESSAGE_TOAST) {
            MToast.makeText(this, event.msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PRINT_SETTING) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                printStyles.clear();
                ArrayList<PrintStyleBean> printOpen = (ArrayList<PrintStyleBean>) data.getSerializableExtra("print_open");
                if(printOpen != null){
                    printStyles.addAll(printOpen);
                    setPrintPaper();
                }
            }
        }
    }

    public void setPrintPaper(){
        for (int i = 0 ; i < printStyles.size(); i++){
            if ("客户".equals(printStyles.get(i).getKey())) {
                client.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("联系电话".equals(printStyles.get(i).getKey())){
                phone.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
//            if("商品编号".equals(printStyles.get(i).getKey())){
//
//                continue;
//            }
//            if("商品名称".equals(printStyles.get(i).getKey())){
//
//                continue;
//            }
            if("业务员".equals(printStyles.get(i).getKey())){
                salesman.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("制单人".equals(printStyles.get(i).getKey())){
                createName.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("单据号".equals(printStyles.get(i).getKey())){
                serialNo.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("单据日期".equals(printStyles.get(i).getKey())){
                createTime.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("单价".equals(printStyles.get(i).getKey())){
//                serialNo.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("金额".equals(printStyles.get(i).getKey())){

                continue;
            }
            if("结算方式".equals(printStyles.get(i).getKey())){
                hravestModel.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("备注".equals(printStyles.get(i).getKey())){
                remake.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if("打印时间".equals(printStyles.get(i).getKey())){
                time.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
            }
        }
    }


    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            if(arr != null){
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enterprise_detail, parent, false);
            return new ViewHolder(view);
        }

        public Object getItem(int position){
            if(mArr != null && mArr.size() > position){
                return mArr.get(position);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    public void getQueryOrder(String orderId) {
        api.getQueryOrder("/" + orderId, new OrderAPI.IResultMsg<OrderDetailsBean>() {
            @Override
            public void Result(OrderDetailsBean bean) {
                if(bean != null){

                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
