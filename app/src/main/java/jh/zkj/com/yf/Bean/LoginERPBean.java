package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/2.
 */

public class LoginERPBean {

    /**
     * access_token : 2239acf9-40b2-41e7-be36-67115fa4ebaa
     * token_type : bearer
     * expires_in : 2591999
     * scope : server
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;

    /**
     * msg : 短信验证码失效
     * code : 20005
     */

    private String msg;
    private int code;


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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
