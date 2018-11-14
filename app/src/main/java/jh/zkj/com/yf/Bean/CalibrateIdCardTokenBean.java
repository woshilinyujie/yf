package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/14.
 */

public class CalibrateIdCardTokenBean {

    /**
     * code : 200
     * message : 操作成功
     * data : {"request_id":"4fa6852c32f14d109370c4794961df68","token":"eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNoky0EKgCAQQNG7zLqFM06mXSasFIyiUIsguntK28f_Dyw5QA_srdItTZI88ozCyE5M3Bk2CmevNDSQzrGEx2qz3-NWIKRUwIah2pBcvFysbDP02DIhSkLdgLuPH0grUSHuq6srwvsBAAD__w.hRwLu3ANbLOoie3P9F1xpJKnYwBbeaOMBBrTalVrHd5sisebuFrEidfZsYN_0CFQUr4LpZshpPTKNyqXYAxp7Q"}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * request_id : 4fa6852c32f14d109370c4794961df68
         * token : eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNoky0EKgCAQQNG7zLqFM06mXSasFIyiUIsguntK28f_Dyw5QA_srdItTZI88ozCyE5M3Bk2CmevNDSQzrGEx2qz3-NWIKRUwIah2pBcvFysbDP02DIhSkLdgLuPH0grUSHuq6srwvsBAAD__w.hRwLu3ANbLOoie3P9F1xpJKnYwBbeaOMBBrTalVrHd5sisebuFrEidfZsYN_0CFQUr4LpZshpPTKNyqXYAxp7Q
         */

        private String request_id;
        private String token;

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
