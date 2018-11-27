package jh.zkj.com.yf.API;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.HomeMenuBean;

/**
 * Created by wdefer
 * 2018/11/8
 * use
 */
public class HomeAPI {
    public final String API = APIConstant.API;
//    public final String TOKEN = "bearer 292f06ac-f530-4218-a991-b1440ebc3d17";
    public final String TOKEN = "bearer 73677450-0871-4b41-8059-c0e4aea6348d";



    public interface IResultMsg<T> {
        void Result(T bean);

        void Error(String json);
    }
}
