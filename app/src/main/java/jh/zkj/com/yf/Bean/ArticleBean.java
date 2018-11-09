package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/11/8.
 */

public class ArticleBean {

    /**
     * data : [{"uuid":"ef73844556b446c5bb40ef3bce03435c","parentUuid":null,"level":"1","name":"商品分类测试-手机1","enableFlag":1,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":2,"levelFullName":"商品分类测试-手机1"},{"uuid":"e66428b492774686859ea109dd56340b","parentUuid":"ef73844556b446c5bb40ef3bce03435c","level":"1.1","name":"手机-华为系列asdasdasdasd12","enableFlag":0,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":4,"levelFullName":"商品分类测试-手机1.手机-华为系列asdasdasdasd12"},{"uuid":"153ef770-c486-11e8-8224-d3cb98f121d1","parentUuid":"ef73844556b446c5bb40ef3bce03435c","level":"1.2","name":"11111111111","enableFlag":0,"remark":"222222","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":3,"levelFullName":"商品分类测试-手机1.11111111111"},{"uuid":"1c16acf0-c486-11e8-8224-d3cb98f121d1","parentUuid":"153ef770-c486-11e8-8224-d3cb98f121d1","level":"1.2.1","name":"11","enableFlag":1,"remark":"22","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":4,"levelFullName":"商品分类测试-手机1.11111111111.11"},{"uuid":"c91a62a135f5433aa3726a8128360daf","parentUuid":"1c16acf0-c486-11e8-8224-d3cb98f121d1","level":"1.2.1.1","name":"Mac系列","enableFlag":1,"remark":null,"companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":5,"levelFullName":"商品分类测试-手机1.11111111111.11.Mac系列"},{"uuid":"2152dae0-c486-11e8-8224-d3cb98f121d1","parentUuid":"c91a62a135f5433aa3726a8128360daf","level":"1.2.1.1.1","name":"dd","enableFlag":1,"remark":"a232","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":7,"levelFullName":"商品分类测试-手机1.11111111111.11.Mac系列.dd"},{"uuid":"102879f0-c486-11e8-8224-d3cb98f121d1","parentUuid":"c91a62a135f5433aa3726a8128360daf","level":"1.2.1.1.2","name":"asdasdasd","enableFlag":1,"remark":"aaaaaa","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":10,"levelFullName":"商品分类测试-手机1.11111111111.11.Mac系列.asdasdasd"},{"uuid":"1916b360-c486-11e8-8224-d3cb98f121d1","parentUuid":"ef73844556b446c5bb40ef3bce03435c","level":"1.3","name":"33333333333","enableFlag":0,"remark":"2222","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":3,"levelFullName":"商品分类测试-手机1.33333333333"},{"uuid":"43951e50e0a211e8bd421fae63853c3c","parentUuid":"1916b360-c486-11e8-8224-d3cb98f121d1","level":"1.3.1","name":"666","enableFlag":0,"remark":"777","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":3,"levelFullName":"商品分类测试-手机1.33333333333.666"},{"uuid":"f68ee0d0e0a311e8a50ccd29887793be","parentUuid":"43951e50e0a211e8bd421fae63853c3c","level":"1.3.1.1","name":"aaa","enableFlag":1,"remark":"sss","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":2,"levelFullName":"商品分类测试-手机1.33333333333.666.aaa"},{"uuid":"aa4423c0e0a811e8aa4061ed89d54478","parentUuid":null,"level":"2","name":"asd","enableFlag":1,"remark":"ger","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":1,"levelFullName":"asd"},{"uuid":"4c0df430e26411e88c40a3bacb11e761","parentUuid":"aa4423c0e0a811e8aa4061ed89d54478","level":"2.1","name":"32","enableFlag":0,"remark":"er","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","version":1,"levelFullName":"asd.32"}]
     * msg : success
     * code : 0
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uuid : ef73844556b446c5bb40ef3bce03435c
         * parentUuid : null
         * level : 1
         * name : 商品分类测试-手机1
         * enableFlag : 1
         * remark : null
         * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
         * version : 2
         * levelFullName : 商品分类测试-手机1
         */

        private String uuid;
        private Object parentUuid;
        private String level;
        private String name;
        private int enableFlag;
        private Object remark;
        private String companyUuid;
        private int version;
        private String levelFullName;
        public boolean isSelect;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Object getParentUuid() {
            return parentUuid;
        }

        public void setParentUuid(Object parentUuid) {
            this.parentUuid = parentUuid;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getEnableFlag() {
            return enableFlag;
        }

        public void setEnableFlag(int enableFlag) {
            this.enableFlag = enableFlag;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public void setCompanyUuid(String companyUuid) {
            this.companyUuid = companyUuid;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLevelFullName() {
            return levelFullName;
        }

        public void setLevelFullName(String levelFullName) {
            this.levelFullName = levelFullName;
        }
    }
}
