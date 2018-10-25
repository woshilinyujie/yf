package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wdefer
 * 2018/10/23
 * use
 */
public class BaseBean<T> implements Serializable{
    private T data;
    private String msg;
    private int code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
