package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/10/24
 * use
 */
public class ClientInfoBean implements Serializable{


    /**
     * uuid : 1c7e6315b71d4eb399013677d84e177d
     * mobilePhone : 15395617228
     * identType : 身份证
     * identNo : 3424211991120551218
     * name : 李四
     * tel : 0564-2411591
     * sex : 0
     * birthday : null
     * cardNo : null
     * deviceUuid : null
     * remark : null
     * companyUuid : 123214214214
     */

    private String uuid;
    private String mobilePhone;
    private String identType;
    private String identNo;
    private String name;
    private String tel;
    private int sex;
    private String birthday;
    private String cardNo;
    private String deviceUuid;
    private String remark;
    private String companyUuid;
    private boolean isSelect;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdentType() {
        return identType;
    }

    public void setIdentType(String identType) {
        this.identType = identType;
    }

    public String getIdentNo() {
        return identNo;
    }

    public void setIdentNo(String identNo) {
        this.identNo = identNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
