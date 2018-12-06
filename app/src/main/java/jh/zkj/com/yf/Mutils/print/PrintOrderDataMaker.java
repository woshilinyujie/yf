package jh.zkj.com.yf.Mutils.print;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.R;


/**
 * 测试数据生成器
 * Created by liuguirong on 8/1/17.
 */

public class PrintOrderDataMaker implements PrintDataMaker {


    private String qr;
    private int width;
    private int height;
    Context btService;
    Intent intent;


    public PrintOrderDataMaker(Context btService, String qr, int width, int height, Intent intent) {
        this.qr = qr;
        this.width = width;
        this.height = height;
        this.btService = btService;
        this.intent = intent;
    }


    @Override
    public List<byte[]> getPrintData(int type) {
        ArrayList<byte[]> data = new ArrayList<>();
        String json = intent.getStringExtra("json");
        OrderDetailsBean bean = GsonUtils.GsonToBean(json, OrderDetailsBean.class);
        try {
            PrinterWriter printer;
            printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
            printer.setAlignCenter();
            data.add(printer.getDataAndReset());


            printer.setAlignLeft();
            printer.printLine();
            printer.printLineFeed();
            printer.setAlignCenter();
            printer.setFontSize(0);
            printer.print(bean.getCompanyName());
            printer.printLineFeed();
            printer.printLineFeed();

            if (!TextUtils.isEmpty(intent.getStringExtra("serialNo"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("单据号：" + bean.getBillNo());
                printer.printLineFeed();
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("createTime"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("单据日期：" + bean.getCreateTime());
                printer.printLineFeed();
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("client"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("客户名称：" + bean.getName());
                printer.printLineFeed();
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("phone"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("联系电话：" + bean.getMobilePhone());
                printer.printLineFeed();
            }

            printer.setFontSize(0);
            printer.setAlignCenter();
            printer.print("------------零售订单------------");
            printer.setAlignLeft();
            printer.printLineFeed();
            printer.print("商品名称");

            printer.printLineFeed();
            printer.setAlignCenter();
            printer.printInOneLine("单价", "数量", "金额", 0);

            for (int x = 0; x < bean.getDetailDTOList().size(); x++) {
                printer.printLineFeed();
                OrderDetailsBean.DetailDTOListBean detailDTOListBean = bean.getDetailDTOList().get(x);
                printer.setAlignLeft();
                printer.print(detailDTOListBean.getSkuFullName());
                printer.printLineFeed();
                printer.setAlignCenter();
                //单价
                double price = detailDTOListBean.getPrice();
                String priceS = String.valueOf(BigDecimalUtils.getBigDecimal(
                        String.valueOf(price), 2).doubleValue());
                //数量
                int qty = (int) (detailDTOListBean.getQty());
                //金额
                BigDecimal multiply = BigDecimalUtils.getBigDecimal(
                        String.valueOf(detailDTOListBean.getPrice()), 2).multiply(BigDecimalUtils.getBigDecimal(
                        String.valueOf(detailDTOListBean.getQty()), 2));
                String money = String.valueOf(multiply.doubleValue());


                if(intent.getBooleanExtra("money", false)&&intent.getBooleanExtra("price", false)){
                    printer.printInOneLine(priceS, qty+"", money, 0);
                }else if(!intent.getBooleanExtra("money", false)&&!intent.getBooleanExtra("price", false)){
                    printer.printInOneLine(" ", qty+"", " ", 0);
                }else if(!intent.getBooleanExtra("money", false)&&intent.getBooleanExtra("price", false)){
                    printer.printInOneLine(" ", qty+"", priceS, 0);
                }else{
                    printer.printInOneLine(money, qty+"", " ", 0);
                }
            }

            printer.setAlignCenter();
            printer.printLineFeed();
            printer.printLine();
            printer.printLineFeed();
            printer.printInOneLine("合计", String.valueOf((int) bean.getTotalQuantity()), String.valueOf(BigDecimalUtils.getBigDecimal(
                    String.valueOf(bean.getTotalAmount()), 2).doubleValue()), 0);
            printer.setAlignLeft();
            printer.printLineFeed();
            printer.printLine();
            if (bean.getBizSoOutCashierList() != null) {
                for (int x = 0; x < bean.getBizSoOutCashierList().size(); x++) {
                    printer.printLineFeed();
                    printer.print(bean.getBizSoOutCashierList().get(x).getCashierTypeName() + ": ");
                    double v = BigDecimalUtils.getBigDecimal(bean.getBizSoOutCashierList().get(x).getAmount(), 2).doubleValue();
                    printer.print(v + "");
                }
            }
            printer.printLineFeed();
            if (!TextUtils.isEmpty(intent.getStringExtra("salesman"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("业务员：" + bean.getClerkName());
                printer.printLineFeed();
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("createName"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("制单人：" + bean.getCreateUserName());
                printer.printLineFeed();
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("remake"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("备注：" + bean.getRemark());
                printer.printLineFeed();
            }
            if (!TextUtils.isEmpty(intent.getStringExtra("printtime"))) {
                printer.setFontSize(0);
                printer.setAlignLeft();
                printer.print("打印时间：" + intent.getStringExtra("printtime"));
                printer.printLineFeed();
            }

            printer.printLineFeed();

            printer.setAlignCenter();
            printer.print("骏杭科技提供技术支持");
            printer.printLineFeed();
            printer.printLineFeed();
            printer.feedPaperCutPartial();

            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            String s = e.toString();
            return new ArrayList<>();
        }
    }


}
