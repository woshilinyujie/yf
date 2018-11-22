package jh.zkj.com.yf.Presenter.My;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.util.ArrayList;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.CompanyFilesActivity;
import jh.zkj.com.yf.Activity.My.CreateEnterpriseSuccessActivity;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.JsonBean;
import jh.zkj.com.yf.Bean.RegisterBean;
import jh.zkj.com.yf.Contract.My.CompanyFilesActivityContract;
import jh.zkj.com.yf.Mutils.GetJsonDataUtil;

/**
 * Created by linyujie on 18/11/1.
 */

public class CompanyFilesActivityPresenter implements CompanyFilesActivityContract.CompanyFilesActivityPresente {
    private final int REQUEST_CREATE_ENTERPRISE_SUCCESS = 1;
    CompanyFilesActivity activity;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showPickerView();
                    break;
            }
        }
    };
    private String companyDescription;
    private String businessCode;
    private String regionFullName;
    private String address;
    private String legalPerson;
    private String contactPerson;
    private String contactPhone;
    private String zipCode;
    private final MyAPI myAPI;
    private String password;
    private String phone;

    public CompanyFilesActivityPresenter(final CompanyFilesActivity activity) {
        this.activity = activity;
        myAPI = new MyAPI();
        save();
        activity.getBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }


    @Override
    public void getDate() {
        password = activity.getIntent().getStringExtra("password");
        phone = activity.getIntent().getStringExtra("phone");
        companyDescription = activity.getCompanyNameEt().getText().toString();
        businessCode = activity.getCompanyCodeEt().getText().toString();
        businessCode = activity.getCompanyCodeEt().getText().toString();
        regionFullName = activity.getCompanyAddress().getText().toString();
        address = activity.getCompanyDetailAddressEt().getText().toString();
        legalPerson = activity.getCompanyLegalEntityEt().getText().toString();
        contactPerson = activity.getCompanyConnectionNameEt().getText().toString();
        contactPhone = activity.getCompanyPhoneEt().getText().toString();
        zipCode = activity.getCompanyZipCodeEt().getText().toString();
    }

    @Override
    public void save() {
        activity.getCompanyTitle().getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
                if (calibrate()) {
                    myAPI.Register(activity, businessCode, companyDescription, address,
                            legalPerson, contactPerson, contactPhone, zipCode, "", "jh-erp-3c"
                            , regionFullName, password, phone, new MyAPI.IResultMsg<RegisterBean>() {
                                @Override
                                public void Result(RegisterBean bean) {
                                    if("success".equals(bean.getMsg())){
                                        activity.showToast("注册成功");
                                        Intent intent=new Intent(activity, CreateEnterpriseSuccessActivity.class);
                                        intent.putExtra("RegisterBean",bean);
                                        intent.putExtra("phone",phone);
                                        activity.startActivityForResult(intent, REQUEST_CREATE_ENTERPRISE_SUCCESS);
//                                        EventBus.getDefault().post("RegisterFinish");
//                                        activity.finish();
                                    }
                                }

                                @Override
                                public void Error(String json) {

                                }
                            });
                }
            }
        });
    }

    @Override
    public boolean calibrate() {
        if (TextUtils.isEmpty(companyDescription)) {
            activity.showToast("请输入公司名称");
            return false;
        } else if (regionFullName.equals("请选择地区")) {
            activity.showToast("请选择所在地区");
            return false;
        } else if (TextUtils.isEmpty(address)) {
            activity.showToast("请输入详细地址");
            return false;
        }
        return true;
    }


    @Override
    public void selectAddress() {

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
                 * 关键逻辑在于循环体
                 *
                 * */
                String JsonData = new GetJsonDataUtil().getJson(activity, "province.json");//获取assets目录下的json文件数据

                ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

                /**
                 * 添加省份数据
                 *
                 * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
                 * PickerView会通过getPickerViewText方法获取字符串显示出来。
                 */
                options1Items = jsonBean;

                for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                    for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                        String CityName = jsonBean.get(i).getCityList().get(c).getName();
                        CityList.add(CityName);//添加城市
                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                        if (jsonBean.get(i).getCityList().get(c).getArea() == null
                                || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                            City_AreaList.add("");
                        } else {
                            City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    }

                    /**
                     * 添加城市数据
                     */
                    options2Items.add(CityList);

                    /**
                     * 添加地区数据
                     */
                    options3Items.add(Province_AreaList);
                }
                handler.sendEmptyMessage(0);
            }
        }).start();
    }


    //Gson 解析
    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
        }
        return detail;
    }

    public void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + "," +
                        options2Items.get(options1).get(options2) + "," +
                        options3Items.get(options1).get(options2).get(options3);
                activity.setAddress(tx);
                activity.setAddressTextColor(Color.parseColor("#333333"));

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CREATE_ENTERPRISE_SUCCESS){
            if(resultCode == Activity.RESULT_OK){
                activity.setResult(Activity.RESULT_OK);
                activity.finish();
            }
        }
    }
}
