package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/13.
 */

public class SendCodeBean {

    /**
     * data : null
     * msg : 发送成功
     * code : 0
     */

    private Object data;
    private String msg;
    private int code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
