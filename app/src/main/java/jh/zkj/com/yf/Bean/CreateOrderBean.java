package jh.zkj.com.yf.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdefer
 * 2018/10/29
 * use  生成订单时使用 目前跟web相同
 */
public class CreateOrderBean {

    /**
     * name : cao
     * mobilePhone : 15196852478
     * remark : 测试
     * uuid : b0c67453-1d41-48da-98b1-c12258986e67
     * ascriptionCompanyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     * bizDate : 2018-10-17
     * memberUuid : 1001000
     * identNo : 339005199501286711
     * identType : 1
     * tel : 82668951
     * sex : 1
     * birthday : 1995-01-28
     * cardNo : 123
     * deviceUuid : 03a873de-228d-5014-b373-69da3e010web
     * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     * auditUserUuid : 2
     * auditTime : 2018-10-17 09:28:30
     * unauditTime : 2018-10-17 09:28:30
     * printTime : 2018-10-17 09:28:30
     * unauditUserUuid : 2
     * printUserUuid : 2
     * modDetailList : [{"skuUuid":"0993350991e547ca88c554b51ba648f2","qty":10,"price":10,"warehouseUuid":"16538b6c-06b2-4b57-adad-f7a1addad39b","serialNo":"3333333333","num":1,"clerk01Uuid":"3","clerk02Uuid":"","clerk03Uuid":"","clerk04Uuid":"","uuid":"c1501670-378f-4a73-8310-7307d6c9e954","bizSoUuid":"b0c67453-1d41-48da-98b1-c12258986e67","serialInfoUuid":"019fa02bf5b545e9ad90bc0f9d99c8cf","remark":"测试明细","bizType":"SO","rate":10,"deviceUuid":"03a873de-228d-5014-b373-69da3e010web","companyUuid":"1426d062-1b36-4faf-a414-a04f1c8f2c22","clerkUuid":"dc170b97-9fc4-4bed-b193-ed711785bff1","bizSoDetailUuid":"c1501670-378f-4a73-8310-7307d6c9e954","clerkRemark":"测试店员"}]
     */

    private String name;
    private String mobilePhone;
    private String remark;
    private String uuid;
    private String ascriptionCompanyUuid;
    private String bizDate = "";
    private String memberUuid;
    private String identNo;
    private String identType;
    private String tel;
    private int sex;
    private String birthday;
    private String cardNo;
    private String deviceUuid;
    private String companyUuid;
    private String auditUserUuid;
    private String auditTime;
    private String unauditTime;
    private String printTime;
    private String unauditUserUuid;
    private String printUserUuid;
    private List<DetailListBean> modDetailList = new ArrayList<>();
    private List<DetailListBean> newDetailList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAscriptionCompanyUuid() {
        return ascriptionCompanyUuid;
    }

    public void setAscriptionCompanyUuid(String ascriptionCompanyUuid) {
        this.ascriptionCompanyUuid = ascriptionCompanyUuid;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public String getIdentNo() {
        return identNo;
    }

    public void setIdentNo(String identNo) {
        this.identNo = identNo;
    }

    public String getIdentType() {
        return identType;
    }

    public void setIdentType(String identType) {
        this.identType = identType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public String getAuditUserUuid() {
        return auditUserUuid;
    }

    public void setAuditUserUuid(String auditUserUuid) {
        this.auditUserUuid = auditUserUuid;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getUnauditTime() {
        return unauditTime;
    }

    public void setUnauditTime(String unauditTime) {
        this.unauditTime = unauditTime;
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public String getUnauditUserUuid() {
        return unauditUserUuid;
    }

    public void setUnauditUserUuid(String unauditUserUuid) {
        this.unauditUserUuid = unauditUserUuid;
    }

    public String getPrintUserUuid() {
        return printUserUuid;
    }

    public void setPrintUserUuid(String printUserUuid) {
        this.printUserUuid = printUserUuid;
    }

    public List<DetailListBean> getModDetailList() {
        return modDetailList;
    }

    public void setModDetailList(List<DetailListBean> modDetailList) {
        this.modDetailList = modDetailList;
    }

    public List<DetailListBean> getNewDetailList() {
        return newDetailList;
    }

    public void setNewDetailList(List<DetailListBean> newDetailList) {
        this.newDetailList = newDetailList;
    }

    public int createtModDeta(){
        DetailListBean modDetailListBean = new DetailListBean();
        modDetailList.add(modDetailListBean);
        return modDetailList.size() - 1;
    }

    public int createtNewDeta(){
        DetailListBean newDetailListBean = new DetailListBean();
        newDetailList.add(newDetailListBean);
        return newDetailList.size() - 1;
    }

    public class DetailListBean {

        /**
         * skuUuid : 0993350991e547ca88c554b51ba648f2
         * qty : 10
         * price : 10
         * warehouseUuid : 16538b6c-06b2-4b57-adad-f7a1addad39b
         * serialNo : 3333333333
         * num : 1
         * clerk01Uuid : 3
         * clerk02Uuid :
         * clerk03Uuid :
         * clerk04Uuid :
         * uuid : c1501670-378f-4a73-8310-7307d6c9e954
         * bizSoUuid : b0c67453-1d41-48da-98b1-c12258986e67
         * serialInfoUuid : 019fa02bf5b545e9ad90bc0f9d99c8cf
         * remark : 测试明细
         * bizType : SO
         * rate : 10
         * deviceUuid : 03a873de-228d-5014-b373-69da3e010web
         * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
         * clerkUuid : dc170b97-9fc4-4bed-b193-ed711785bff1
         * bizSoDetailUuid : c1501670-378f-4a73-8310-7307d6c9e954
         * clerkRemark : 测试店员
         */

        private String skuUuid;
        private int qty;
        private String price;
        private String warehouseUuid;
        private String serialNo;
        private int num;
        private String clerk01Uuid;
        private String clerk02Uuid;
        private String clerk03Uuid;
        private String clerk04Uuid;
        private String clerk05Uuid;
        private String clerk06Uuid;
        private String clerk07Uuid;
        private String clerk08Uuid;
        private String clerk09Uuid;
        private String clerk10Uuid;
//        private String uuid;
//        private String bizSoUuid;
//        private String serialInfoUuid;
//        private String remark;
//        private String bizType;
//        private int rate;
//        private String deviceUuid;
//        private String companyUuid;
//        private String clerkUuid;
//        private String bizSoDetailUuid;
//        private String clerkRemark;

        public String getSkuUuid() {
            return skuUuid;
        }

        public void setSkuUuid(String skuUuid) {
            this.skuUuid = skuUuid;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getWarehouseUuid() {
            return warehouseUuid;
        }

        public void setWarehouseUuid(String warehouseUuid) {
            this.warehouseUuid = warehouseUuid;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getUuid() {
            return uuid;
        }

       /* public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getBizSoUuid() {
            return bizSoUuid;
        }

        public void setBizSoUuid(String bizSoUuid) {
            this.bizSoUuid = bizSoUuid;
        }

        public String getSerialInfoUuid() {
            return serialInfoUuid;
        }

        public void setSerialInfoUuid(String serialInfoUuid) {
            this.serialInfoUuid = serialInfoUuid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getDeviceUuid() {
            return deviceUuid;
        }

        public void setDeviceUuid(String deviceUuid) {
            this.deviceUuid = deviceUuid;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public void setCompanyUuid(String companyUuid) {
            this.companyUuid = companyUuid;
        }

        public String getBizSoDetailUuid() {
            return bizSoDetailUuid;
        }

        public void setBizSoDetailUuid(String bizSoDetailUuid) {
            this.bizSoDetailUuid = bizSoDetailUuid;
        }

        public String getClerkRemark() {
            return clerkRemark;
        }

        public void setClerkRemark(String clerkRemark) {
            this.clerkRemark = clerkRemark;
        }*/

        public String getClerk01Uuid() {
            return clerk01Uuid;
        }

        public String getClerk02Uuid() {
            return clerk02Uuid;
        }

        public String getClerk03Uuid() {
            return clerk03Uuid;
        }

        public String getClerk04Uuid() {
            return clerk04Uuid;
        }

        public String getClerk05Uuid() {
            return clerk05Uuid;
        }

        public String getClerk06Uuid() {
            return clerk06Uuid;
        }

        public String getClerk07Uuid() {
            return clerk07Uuid;
        }

        public String getClerk08Uuid() {
            return clerk08Uuid;
        }

        public String getClerk09Uuid() {
            return clerk09Uuid;
        }

        public String getClerk10Uuid() {
            return clerk10Uuid;
        }

        public void setClerk01Uuid(String clerk01Uuid) {
            this.clerk01Uuid = clerk01Uuid;
        }

        public void setClerk02Uuid(String clerk02Uuid) {
            this.clerk02Uuid = clerk02Uuid;
        }

        public void setClerk03Uuid(String clerk03Uuid) {
            this.clerk03Uuid = clerk03Uuid;
        }

        public void setClerk04Uuid(String clerk04Uuid) {
            this.clerk04Uuid = clerk04Uuid;
        }

        public void setClerk05Uuid(String clerk05Uuid) {
            this.clerk05Uuid = clerk05Uuid;
        }

        public void setClerk06Uuid(String clerk06Uuid) {
            this.clerk06Uuid = clerk06Uuid;
        }

        public void setClerk07Uuid(String clerk07Uuid) {
            this.clerk07Uuid = clerk07Uuid;
        }

        public void setClerk08Uuid(String clerk08Uuid) {
            this.clerk08Uuid = clerk08Uuid;
        }

        public void setClerk09Uuid(String clerk09Uuid) {
            this.clerk09Uuid = clerk09Uuid;
        }

        public void setClerk10Uuid(String clerk10Uuid) {
            this.clerk10Uuid = clerk10Uuid;
        }

        public void addClerkUuid(String clerkUuid, int position) {
            switch (position){
                case 0 :{
                    setClerk01Uuid(clerkUuid);
                    break;
                }
                case 1 :{
                    setClerk02Uuid(clerkUuid);
                    break;
                }
                case 2 :{
                    setClerk03Uuid(clerkUuid);
                    break;
                }
                case 3 :{
                    setClerk04Uuid(clerkUuid);
                    break;
                }
                case 4 :{
                    setClerk05Uuid(clerkUuid);
                    break;
                }
                case 5 :{
                    setClerk06Uuid(clerkUuid);
                    break;
                }
                case 6 :{
                    setClerk07Uuid(clerkUuid);
                    break;
                }
                case 7 :{
                    setClerk08Uuid(clerkUuid);
                    break;
                }
                case 8 :{
                    setClerk09Uuid(clerkUuid);
                    break;
                }
                case 9 :{
                    setClerk10Uuid(clerkUuid);
                    break;
                }
            }
        }
    }
}
