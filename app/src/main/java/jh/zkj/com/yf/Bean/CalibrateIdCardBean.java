package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/1.
 */

public class CalibrateIdCardBean {

    /**
     * code : 200
     * message : 操作成功
     * data : {"validity":"20141223-20241223"}
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
         * validity : 20141223-20241223
         */

        private String validity;

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }
    }
}
