package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/14.
 */

public class CalibrateIdCardTokeupBean  {

    /**
     * app_key : platform
     * application_type : 101
     * app_secret : 123456
     * apply_no : 2816c1948b634a24a448ba3f16c94c3e
     * data : {}
     */

    private String app_key;
    private String application_type;
    private String app_secret;
    private String apply_no;
    private DataBean data;

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getApplication_type() {
        return application_type;
    }

    public void setApplication_type(String application_type) {
        this.application_type = application_type;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
