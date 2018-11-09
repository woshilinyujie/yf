package jh.zkj.com.yf.Bean;

/**
 * Created by linyujie on 18/11/2.
 */

public class LoginCRMBean {

    /**
     * access_token : a572b166-3556-4f33-a2d6-58b33ac7f855
     * token_type : bearer
     * expires_in : 36837
     * scope : server
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;

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
}
