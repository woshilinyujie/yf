package jh.zkj.com.yf.Bean;

import java.io.Serializable;

/**
 * Created by wdefer
 * 2018/11/21
 * use
 */
public class PrintStyleBean implements Serializable {
    private String key;
    private boolean isOpen;

    public PrintStyleBean(){}

    public PrintStyleBean(String key, boolean isOpen) {
        this.key = key;
        this.isOpen = isOpen;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
