package jh.zkj.com.yf.Bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wdefer
 * 2018/10/29
 * use
 */
public class OrderDetailsBean implements Serializable{

    /**
     * uuid : e4a012f8-e161-4b85-ad44-4d8e94c94679
     * bizType : SO
     * bizDate : 2018-10-17
     * billNo : SO00144
     * memberUuid : 1001000
     * mobilePhone : 15196852478
     * identNo : 339005199501286711
     * identType : 1
     * name : cao
     * tel : 82668951
     * sex : 1
     * birthday : 1995-01-28
     * cardNo : 123
     * statusFlag : 1
     * deviceUuid : 03a873de-228d-5014-b373-69da3e010web
     * remark : 测试
     * auditUserUuid : 2
     * auditTime : 2018-10-17 09:28:30
     * unauditUserUuid : 2
     * unauditTime : 2018-10-17 09:28:30
     * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
     * companyName : 浙江公司test
     * createUserName : 吴
     * topCompanyCode : 00001
     * printUserUuid : 2
     * printTime : 2018-10-17 09:28:30
     * totalAmount : 200.0
     * totalQuantity : 20.0
     * detailDTOList : []
     * createTime : 2018-10-17 10:58:46
     * updateTime : 2018-10-17 11:02:12
     * updateUserName : 吴
     * cashierName : 可是
     * cashierTime : 2018-10-23 15:13:01
     */

    private String bizSoUuid;
    private String uuid;
    private String bizType;
    private String bizDate;
    private String billNo;
    private String memberUuid;
    private String mobilePhone;
    private String identNo;
    private String identType;
    private String name;
    private String tel;
    private int sex;
    private String birthday;
    private String cardNo;
    private int statusFlag;
    private String deviceUuid;
    private String remark;
    private String auditUserUuid;
    private String auditTime;
    private String unauditUserUuid;
    private String unauditTime;
    private String companyUuid;
    private String companyName;
    private String createUserName;
    private String topCompanyCode;
    private String printUserUuid;
    private String printTime;
    private double totalAmount;
    private double totalQuantity;
    private String createTime;
    private String updateTime;
    private String updateUserName;
    private String cashierName;
    private String cashierTime;
    private String clerkName;
    private String reason;
    //付款时传入的uuid
    private String bizSoOutUuid;
    //付款详情备注
    private String bizSoOutRemark;
    private String bizSoOutBillNo;
    private String ascriptionCompanyUuid;
    private MemberDTOBean memberDTO = new MemberDTOBean();
    private ArrayList<DetailDTOListBean> detailDTOList;
    private ArrayList<HarvestModeBean> newCashierList;
    //后期加入的
    private ArrayList<HarvestModeBean> bizSoOutCashierList;

    public String getBizSoUuid() {
        return bizSoUuid;
    }

    public void setBizSoUuid(String bizSoUuid) {
        this.bizSoUuid = bizSoUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUnauditUserUuid() {
        return unauditUserUuid;
    }

    public void setUnauditUserUuid(String unauditUserUuid) {
        this.unauditUserUuid = unauditUserUuid;
    }

    public String getUnauditTime() {
        return unauditTime;
    }

    public void setUnauditTime(String unauditTime) {
        this.unauditTime = unauditTime;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getTopCompanyCode() {
        return topCompanyCode;
    }

    public void setTopCompanyCode(String topCompanyCode) {
        this.topCompanyCode = topCompanyCode;
    }

    public String getPrintUserUuid() {
        return printUserUuid;
    }

    public void setPrintUserUuid(String printUserUuid) {
        this.printUserUuid = printUserUuid;
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getCashierTime() {
        return cashierTime;
    }

    public void setCashierTime(String cashierTime) {
        this.cashierTime = cashierTime;
    }

    public ArrayList<DetailDTOListBean> getDetailDTOList() {
        return detailDTOList;
    }

    public void setDetailDTOList(ArrayList<DetailDTOListBean> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<HarvestModeBean> getNewCashierList() {
        return newCashierList;
    }

    public void setNewCashierList(ArrayList<HarvestModeBean> newCashierList) {
        this.newCashierList = newCashierList;
    }

    public MemberDTOBean getMemberDTO() {
        return memberDTO;
    }

    public void setMemberDTO(MemberDTOBean memberDTO) {
        this.memberDTO = memberDTO;
    }

    public String getBizSoOutUuid() {
        return bizSoOutUuid;
    }

    public void setBizSoOutUuid(String bizSoOutUuid) {
        this.bizSoOutUuid = bizSoOutUuid;
    }

    public String getBizSoOutRemark() {
        return bizSoOutRemark;
    }

    public void setBizSoOutRemark(String bizSoOutRemark) {
        this.bizSoOutRemark = bizSoOutRemark;
    }

    public ArrayList<HarvestModeBean> getBizSoOutCashierList() {
        return bizSoOutCashierList;
    }

    public void setBizSoOutCashierList(ArrayList<HarvestModeBean> bizSoOutCashierList) {
        this.bizSoOutCashierList = bizSoOutCashierList;
    }

    public String getBizSoOutBillNo() {
        return bizSoOutBillNo;
    }

    public void setBizSoOutBillNo(String bizSoOutBillNo) {
        this.bizSoOutBillNo = bizSoOutBillNo;
    }

    public String getAscriptionCompanyUuid() {
        return ascriptionCompanyUuid;
    }

    public void setAscriptionCompanyUuid(String ascriptionCompanyUuid) {
        this.ascriptionCompanyUuid = ascriptionCompanyUuid;
    }

    public static class DetailDTOListBean implements Serializable{
        /**
         * uuid : 0529fe5f-3a42-438b-aa9b-daf811ae99cd
         * bizSoUuid : e4a012f8-e161-4b85-ad44-4d8e94c94679
         * bizType : SO
         * skuUuid : 0993350991e547ca88c554b51ba648f2
         * serialNo : 3333333333
         * serialInfoUuid : 019fa02bf5b545e9ad90bc0f9d99c8cf
         * batchNo : SO00101
         * qty : 10.0
         * price : 10.0
         * sumPrice : 100.0
         * rate : 10.0
         * deviceUuid : 03a873de-228d-5014-b373-69da3e010web
         * remark : 测试明细
         * companyUuid : 1426d062-1b36-4faf-a414-a04f1c8f2c22
         * companyName : 浙江公司test
         * topCompanyCode : 00001
         * skuCode : 000001
         * skuFullName : 666
         * skuMnemonicCode : 658757
         * warehouseUuid : 16538b6c-06b2-4b57-adad-f7a1addad39b
         * warehouseName : 仓库02
         * clerkUuid : 5e27e104-e642-4b36-83a9-894b7700f70c
         * num : 1
         * clerk01Uuid : 1
         * clerkRemark : 测试店员
         */

        private String uuid;
        private String bizSoUuid;
        private String bizType;
        private String skuUuid;
        private String serialNo;
        private String serialInfoUuid;
        private String batchNo;
        private double qty;
        private double price;
        private double sumPrice;
        private double rate;
        private String deviceUuid;
        private String remark;
        private String companyUuid;
        private String companyName;
        private String topCompanyCode;
        private String skuCode;
        private String skuFullName;
        private String skuMnemonicCode;
        private String warehouseUuid;
        private String warehouseName;
        private String clerkUuid;
        private int num;
        private String clerk01Uuid;
        private String clerkRemark;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getBizSoUuid() {
            return bizSoUuid;
        }

        public void setBizSoUuid(String bizSoUuid) {
            this.bizSoUuid = bizSoUuid;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }

        public String getSkuUuid() {
            return skuUuid;
        }

        public void setSkuUuid(String skuUuid) {
            this.skuUuid = skuUuid;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getSerialInfoUuid() {
            return serialInfoUuid;
        }

        public void setSerialInfoUuid(String serialInfoUuid) {
            this.serialInfoUuid = serialInfoUuid;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(double sumPrice) {
            this.sumPrice = sumPrice;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getDeviceUuid() {
            return deviceUuid;
        }

        public void setDeviceUuid(String deviceUuid) {
            this.deviceUuid = deviceUuid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public void setCompanyUuid(String companyUuid) {
            this.companyUuid = companyUuid;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getTopCompanyCode() {
            return topCompanyCode;
        }

        public void setTopCompanyCode(String topCompanyCode) {
            this.topCompanyCode = topCompanyCode;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getSkuFullName() {
            return skuFullName;
        }

        public void setSkuFullName(String skuFullName) {
            this.skuFullName = skuFullName;
        }

        public String getSkuMnemonicCode() {
            return skuMnemonicCode;
        }

        public void setSkuMnemonicCode(String skuMnemonicCode) {
            this.skuMnemonicCode = skuMnemonicCode;
        }

        public String getWarehouseUuid() {
            return warehouseUuid;
        }

        public void setWarehouseUuid(String warehouseUuid) {
            this.warehouseUuid = warehouseUuid;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getClerkUuid() {
            return clerkUuid;
        }

        public void setClerkUuid(String clerkUuid) {
            this.clerkUuid = clerkUuid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getClerk01Uuid() {
            return clerk01Uuid;
        }

        public void setClerk01Uuid(String clerk01Uuid) {
            this.clerk01Uuid = clerk01Uuid;
        }

        public String getClerkRemark() {
            return clerkRemark;
        }

        public void setClerkRemark(String clerkRemark) {
            this.clerkRemark = clerkRemark;
        }
    }


    public class MemberDTOBean implements Serializable{

        /**
         * memberUuid : 1341114adaggaf
         * mobilePhone : 15395617328
         * identType : 身份证
         * identNo : 342421199112055578
         * companyUuid : 123214214214
         * name : 可是
         * tel : 0564-2411591
         * sex : 0
         */

        private String memberUuid;
        private String mobilePhone;
        private String identType;
        private String identNo;
        private String companyUuid;
        private String name;
        private String tel;
        private int sex;

        public String getMemberUuid() {
            return memberUuid;
        }

        public void setMemberUuid(String memberUuid) {
            this.memberUuid = memberUuid;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getIdentType() {
            return identType;
        }

        public void setIdentType(String identType) {
            this.identType = identType;
        }

        public String getIdentNo() {
            return identNo;
        }

        public void setIdentNo(String identNo) {
            this.identNo = identNo;
        }

        public String getCompanyUuid() {
            return companyUuid;
        }

        public void setCompanyUuid(String companyUuid) {
            this.companyUuid = companyUuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
