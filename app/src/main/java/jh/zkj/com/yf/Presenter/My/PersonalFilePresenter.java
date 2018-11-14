package jh.zkj.com.yf.Presenter.My;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.util.ArrayList;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.PersonalFileActivity;
import jh.zkj.com.yf.Bean.CalibrateIdCardBean;
import jh.zkj.com.yf.Bean.CalibrateIdCardTokenBean;
import jh.zkj.com.yf.Bean.JsonBean;
import jh.zkj.com.yf.Contract.My.PersonalFileActivityContract;
import jh.zkj.com.yf.Mutils.GetJsonDataUtil;
import jh.zkj.com.yf.Mview.CancleDialog;
import jh.zkj.com.yf.Mview.PhotoPopupWindow;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/30.
 */

public class PersonalFilePresenter implements PersonalFileActivityContract.PersonalFileActivityPresente {
    PersonalFileActivity activity;
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
    private PhotoPopupWindow popupWindow;
    private MyAPI myAPI;
    private MyAPI.IResultMsgTwo<CalibrateIdCardBean> calibrateIResultMsg;
    private String password;
    private String phone;
    private String sex = "1";
    private String identImgFront="";
    private String identImgBack="";

    public PersonalFilePresenter(PersonalFileActivity activity) {
        this.activity = activity;
        initData();
        initListener();
    }

    @Override
    public void initData() {
        password = activity.getIntent().getStringExtra("password");
        phone = activity.getIntent().getStringExtra("phone");
        activity.setPhoneS(phone);
        myAPI = new MyAPI();
        //身份证校验网络回调  flag 0=view1    flag1=view2
        calibrateIResultMsg = new MyAPI.IResultMsgTwo<CalibrateIdCardBean>() {
            @Override
            public void Result(CalibrateIdCardBean bean, int flag, String path) {

                if (flag == 0) {
                    activity.setFrontIdBg(path);
                    identImgFront = bean.getData().getValidity();
                } else {
                    activity.setBackIdBg(path);
                    identImgBack = bean.getData().getValidity();
                }
            }

            @Override
            public void Error(String json, int flag) {

            }
        };
    }

    @Override
    public void initListener() {
        activity.getPersonalFileTitle().getRigthText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击保存
                String code = activity.getPersonalCompanyCode().getText().toString();
                String name = activity.getPersonalFileName().getText().toString();
                if (TextUtils.isEmpty(activity.getPersonalCompanyCode().getText().toString())) {
                    activity.showToast("请填写企业代码");
                    return;
                }
                if (TextUtils.isEmpty(activity.getPersonalFileName().getText().toString())) {
                    activity.showToast("请填写姓名");
                    return;
                }
                String userName = activity.getPersonalFileLoginName().getText().toString();
                String id = activity.getPersonalFileId().getText().toString();
                String regionFullName = activity.getPersonalFileAddress().toString();
                String identAddress = activity.getPersonalFileNameDetailedAddress().getText().toString();
                myAPI.joinCompanySave(activity, phone, password, password, code, name, userName, id, sex, regionFullName, identAddress, identImgFront, identImgBack, new MyAPI.IResultMsg() {
                    @Override
                    public void Result(Object bean) {
                        final CancleDialog dialog = new CancleDialog(activity);
                        dialog.show();
                        dialog.getCancle().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.getSure().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EventBus.getDefault().post("joinCompanyFinish");
                            }
                        });
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
            }
        });

    }


    @Override
    public void selectSexMan() {
        activity.getPersonalFileSexMan().setChecked(true);
        activity.getPersonalFileSexWoman().setChecked(false);
        sex = "1";
    }

    @Override
    public void selectSexWomanMan() {
        activity.getPersonalFileSexMan().setChecked(false);
        activity.getPersonalFileSexWoman().setChecked(true);
        sex = "2";
    }

    public void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
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
    public void selectIdFront() {

    }

    @Override
    public void selectIdBack() {

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


    @Override
    public void ClickPhoto(View view) {
        if (popupWindow == null)
            popupWindow = new PhotoPopupWindow(activity);
        popupWindow.showPopup();
        ClickTakePhoto(view);
        ClickPhotoSelect(view);
        ClickPhotoCancle();
    }


    @Override
    public void ClickTakePhoto(final View view) {
        popupWindow.getTake().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.initTakePhoto(activity.getFrameTakePhoto(), view);
                popupWindow.Dismiss();
            }
        });
    }

    @Override
    public void ClickPhotoSelect(final View view) {
        popupWindow.getSelect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.initSelect(activity.getFrameTakePhoto(), view);
                popupWindow.Dismiss();
            }
        });
    }

    @Override
    public void ClickPhotoCancle() {
        popupWindow.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.Dismiss();
            }
        });
    }

    @Override
    public void clickFrontX() {
        activity.setFrontIdBg(R.mipmap.id_front);
    }

    @Override
    public void clickBackX() {
        activity.setBackIdBg(R.mipmap.id_back);
    }

    @Override
    public void CalibrateIdCard(String fileCategory, String path,String token) {
        myAPI.CalibrateIdCard(activity, fileCategory, path,token, calibrateIResultMsg);
    }

    @Override
    public void CalibrateIdCardToken(final int flag, final String path) {
        myAPI.CalibrateIdCardToke(activity, new MyAPI.IResultMsg<CalibrateIdCardTokenBean>() {
            @Override
            public void Result(CalibrateIdCardTokenBean bean) {
                String token = bean.getData().getToken();
                if(flag==1){
                    CalibrateIdCard("F", path,token);
                }else{
                    CalibrateIdCard("R", path,token);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }


}
