package jh.zkj.com.yf.Activity.Order;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Bean.HarvestModeBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Bean.PrintStyleBean;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
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
    private Adapter adapter;
    private OrderDetailsBean orderDetailsBean;

    private boolean isShowPrice = true;
    private boolean isShowMoney = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initView() {

    }

    private void initData() {

        controller = new BluetoothController();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }
        EventBus.getDefault().register(this);

        String print_setting = PrefUtils.getString(this, "print_setting", "");
        printStyles = (ArrayList<PrintStyleBean>) JSONObject.parseArray(print_setting, PrintStyleBean.class);
        if (printStyles == null || printStyles.size() == 0) {
            printStyles = OrderConfig.getDefaultPrintStyle();
        }
        setPrintPaper();

        initAdapter();

        String orderId = getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER);
        api = new OrderAPI(this);
        getQueryOrder(orderId);


    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
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
                        if(orderDetailsBean==null){
                            MToast.makeText(this, "等待网络请求", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), BtServiceOne.class);

                            MToast.makeText(this, "打印测试...", Toast.LENGTH_SHORT).show();
                            intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                            startService(intent);
                        }
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
            if (resultCode == Activity.RESULT_OK) {
                printStyles.clear();
                String print_setting = PrefUtils.getString(this, "print_setting", "");
                ArrayList<PrintStyleBean> prints = (ArrayList<PrintStyleBean>) JSONObject.parseArray(print_setting, PrintStyleBean.class);
                if (prints != null) {
                    printStyles.addAll(prints);
                    setPrintPaper();
                }
            }
        }
    }

    public void setPrintPaper() {
        for (int i = 0; i < printStyles.size(); i++) {
            if ("客户".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){}
                client.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("联系电话".equals(printStyles.get(i).getKey())) {
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
            if ("业务员".equals(printStyles.get(i).getKey())) {
                salesman.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("制单人".equals(printStyles.get(i).getKey())) {
                createName.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("单据号".equals(printStyles.get(i).getKey())) {
                serialNo.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("单据日期".equals(printStyles.get(i).getKey())) {
                createTime.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("单价".equals(printStyles.get(i).getKey())) {
                isShowPrice = printStyles.get(i).isOpen();
                continue;
            }
            if ("金额".equals(printStyles.get(i).getKey())) {
                isShowMoney = printStyles.get(i).isOpen();
                continue;
            }
            if ("结算方式".equals(printStyles.get(i).getKey())) {
                hravestModel.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("备注".equals(printStyles.get(i).getKey())) {
                remake.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("打印时间".equals(printStyles.get(i).getKey())) {
                time.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
            }
        }
    }
    public void setPrintPaper(OrderDetailsBean bean,Intent intent) {
        for (int i = 0; i < printStyles.size(); i++) {
            if ("客户".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){
                    intent.putExtra("client",bean.getName());
                }
                continue;
            }
            if ("联系电话".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){
                    intent.putExtra("phone",bean.getMobilePhone());
                }
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
            if ("业务员".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){
                    intent.putExtra("salesman",bean.getClerkName());
                }
                continue;
            }
            if ("制单人".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){
                    intent.putExtra("createName",bean.getCreateUserName());
                }
                continue;
            }
            if ("单据号".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){
                    intent.putExtra("serialNo", bean.getBillNo());
                }
                continue;
            }
            if ("单据日期".equals(printStyles.get(i).getKey())) {
                if(printStyles.get(i).isOpen()){
                    intent.putExtra("createTime", bean.getCreateTime());
                }
                continue;
            }
            if ("单价".equals(printStyles.get(i).getKey())) {

                continue;
            }
            if ("金额".equals(printStyles.get(i).getKey())) {

                continue;
            }
            if ("结算方式".equals(printStyles.get(i).getKey())) {
                hravestModel.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("备注".equals(printStyles.get(i).getKey())) {
                remake.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
                continue;
            }
            if ("打印时间".equals(printStyles.get(i).getKey())) {
                time.setVisibility(printStyles.get(i).isOpen() ? View.VISIBLE : View.GONE);
            }
        }
    }


    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<OrderDetailsBean.DetailDTOListBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<OrderDetailsBean.DetailDTOListBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_print, parent, false);
            return new ViewHolder(view);
        }

        public OrderDetailsBean.DetailDTOListBean getItem(int position) {
            if (mArr != null && mArr.size() > position) {
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
            OrderDetailsBean.DetailDTOListBean item = getItem(position);
            if (item != null) {
                if(position == 0){
                    holder.space.setVisibility(View.GONE);
                }else{
                    holder.space.setVisibility(View.VISIBLE);
                }

                holder.fullName.setText(item.getSkuFullName());

                if(isShowPrice){
                    holder.price.setVisibility(View.VISIBLE);
                    holder.price.setText(String.valueOf(BigDecimalUtils.getBigDecimal(
                            String.valueOf(item.getPrice()), 2).doubleValue()));
                }else{
                    holder.price.setVisibility(View.GONE);
                }


                holder.count.setText(String.valueOf((int)item.getQty()));

                if(isShowMoney){
                    holder.totalPrice.setVisibility(View.VISIBLE);
                    BigDecimal multiply = BigDecimalUtils.getBigDecimal(
                            String.valueOf(item.getPrice()), 2).multiply(BigDecimalUtils.getBigDecimal(
                            String.valueOf(item.getQty()), 2));
                    holder.totalPrice.setText(String.valueOf(multiply.doubleValue()));
                }else{
                    holder.totalPrice.setVisibility(View.GONE);
                }


            }
        }

        @Override
        public int getItemCount() {
            if(mArr == null){
                return 0;
            }else if(mArr.size() < 3){
                return mArr.size();
            }else{
                return 2;
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.print_space)
            View space;
            @BindView(R.id.print_count)
            TextView count;
            @BindView(R.id.print_price)
            TextView price;
            @BindView(R.id.print_total_price)
            TextView totalPrice;
            @BindView(R.id.print_full_name)
            TextView fullName;

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
                if (bean != null) {
                    orderDetailsBean = bean;
                    companyName.setText(bean.getCompanyName());
                    serialNo.setText("单据号：" + bean.getBillNo());
                    createTime.setText("单据日期：" + bean.getCreateTime());
                    client.setText("客户名称：" + bean.getName());
                    phone.setText("联系电话：" + bean.getMobilePhone());
                    totalCount.setText(String.valueOf((int) bean.getTotalQuantity()));
                    totalPrice.setText(String.valueOf(BigDecimalUtils.getBigDecimal(
                            String.valueOf(bean.getTotalAmount()), 2).doubleValue()));
                    salesman.setText("业务员：" + bean.getClerkName());
                    createName.setText("制单人：" + bean.getCreateUserName());
                    remake.setText("备注：" + bean.getBizSoOutRemark());

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    time.setText("打印时间：" + simpleDateFormat.format(date));

                    //付款方式
                    ArrayList<HarvestModeBean> cashierList = bean.getBizSoOutCashierList();
                    if(cashierList != null){
                        for (int i = 0; i < cashierList.size(); i++) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(cashierList.get(i).getCashierTypeName());
                            sb.append("：");
                            sb.append(BigDecimalUtils.getBigDecimal(cashierList.get(i).getAmount(), 2).doubleValue());
                            TextView tv = new TextView(PrintActivity.this);
                            tv.setText(sb.toString());
                            tv.setTextSize(12);
                            tv.setTextColor(0xff333333);
                            tv.setIncludeFontPadding(false);
                            tv.setPadding(0, DpUtils.dip2px(PrintActivity.this, 10f), 0, 0);
                            hravestModel.addView(tv);
                        }
                    }

                    //商品list
                    ArrayList<OrderDetailsBean.DetailDTOListBean> detailDTOList = bean.getDetailDTOList();
                    adapter.notifyData(detailDTOList);

                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
