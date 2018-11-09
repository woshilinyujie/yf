package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/5.
 */

public class RegisterNextBean {

    /**
     * data : true
     * msg : success
     * code : 0
     */

    private boolean data;
    private String msg;
    private int code;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
