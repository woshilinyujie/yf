package jh.zkj.com.yf.Bean;

import java.util.List;

/**
 * Created by linyujie on 18/10/26.
 */

public class OrderListBean {

    private DataBean data;
    private String msg;
    private String code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean {

        private int total;
        private int size;
        private int current;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {

            private String uuid;
            private String bizType;
            private String bizDate;
            private String billNo;
            private String ascriptionCompanyUuid;
            private String statusFlag;
            private String remark;
            private String companyUuid;
            private String createUserName;
            private String updateUserName;
            private String topCompanyCode;
            private String mobilePhone;
            private String identNo;
            private String identType;
            private String name;
            private String tel;
            private int sex;
            private String birthday;
            private String cardNo;
            private String skuUuid;
            private String serialNo;
            private String batchNo;
            private double qty;
            private double price;
            private String skuCode;
            private String skuFullName;
            private String skuMnemonicCode;
            private String clerk01Uuid;
            private String clerk02Uuid;
            private String clerk03Uuid;
            private String auditUserUuid;
            private String auditTime;
            private String unauditUserUuid;
            private String unauditTime;
            private String printUserUuid;
            private String printTime;
            private String memberUuid;
            private String serialInfoUuid;
            private double rate;
            private int num;
            private String soOutBillNo;
            private String soOutCreateTime;
            private String soOutCreateUserUuid;
            private String soOutCreateUserName;
            private String createTime;
            private String totalAmount;
            private double totalQuantity;
            private List<BizSoDetailBean> bizSoDetail;
            private List<BizSoClerkBean> bizSoClerk;

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

            public String getAscriptionCompanyUuid() {
                return ascriptionCompanyUuid;
            }

            public void setAscriptionCompanyUuid(String ascriptionCompanyUuid) {
                this.ascriptionCompanyUuid = ascriptionCompanyUuid;
            }

            public String getStatusFlag() {
                return statusFlag;
            }

            public void setStatusFlag(String statusFlag) {
                this.statusFlag = statusFlag;
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

            public String getCreateUserName() {
                return createUserName;
            }

            public void setCreateUserName(String createUserName) {
                this.createUserName = createUserName;
            }

            public String getUpdateUserName() {
                return updateUserName;
            }

            public void setUpdateUserName(String updateUserName) {
                this.updateUserName = updateUserName;
            }

            public String getTopCompanyCode() {
                return topCompanyCode;
            }

            public void setTopCompanyCode(String topCompanyCode) {
                this.topCompanyCode = topCompanyCode;
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

            public String getClerk01Uuid() {
                return clerk01Uuid;
            }

            public void setClerk01Uuid(String clerk01Uuid) {
                this.clerk01Uuid = clerk01Uuid;
            }

            public String getClerk02Uuid() {
                return clerk02Uuid;
            }

            public void setClerk02Uuid(String clerk02Uuid) {
                this.clerk02Uuid = clerk02Uuid;
            }

            public String getClerk03Uuid() {
                return clerk03Uuid;
            }

            public void setClerk03Uuid(String clerk03Uuid) {
                this.clerk03Uuid = clerk03Uuid;
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

            public String getMemberUuid() {
                return memberUuid;
            }

            public void setMemberUuid(String memberUuid) {
                this.memberUuid = memberUuid;
            }

            public String getSerialInfoUuid() {
                return serialInfoUuid;
            }

            public void setSerialInfoUuid(String serialInfoUuid) {
                this.serialInfoUuid = serialInfoUuid;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getSoOutBillNo() {
                return soOutBillNo;
            }

            public void setSoOutBillNo(String soOutBillNo) {
                this.soOutBillNo = soOutBillNo;
            }

            public String getSoOutCreateTime() {
                return soOutCreateTime;
            }

            public void setSoOutCreateTime(String soOutCreateTime) {
                this.soOutCreateTime = soOutCreateTime;
            }

            public String getSoOutCreateUserUuid() {
                return soOutCreateUserUuid;
            }

            public void setSoOutCreateUserUuid(String soOutCreateUserUuid) {
                this.soOutCreateUserUuid = soOutCreateUserUuid;
            }

            public String getSoOutCreateUserName() {
                return soOutCreateUserName;
            }

            public void setSoOutCreateUserName(String soOutCreateUserName) {
                this.soOutCreateUserName = soOutCreateUserName;
            }

            public List<BizSoDetailBean> getBizSoDetail() {
                return bizSoDetail;
            }

            public void setBizSoDetail(List<BizSoDetailBean> bizSoDetail) {
                this.bizSoDetail = bizSoDetail;
            }

            public List<BizSoClerkBean> getBizSoClerk() {
                return bizSoClerk;
            }

            public void setBizSoClerk(List<BizSoClerkBean> bizSoClerk) {
                this.bizSoClerk = bizSoClerk;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public double getTotalQuantity() {
                return totalQuantity;
            }

            public void setTotalQuantity(double totalQuantity) {
                this.totalQuantity = totalQuantity;
            }

            public static class BizSoDetailBean {

                private String bizSoUuid;
                private String skuUuid;
                private String serialNo;
                private String batchNo;
                private double qty;
                private double price;
                private String skuCode;
                private String skuFullName;
                private String skuMnemonicCode;

                public String getBizSoUuid() {
                    return bizSoUuid;
                }

                public void setBizSoUuid(String bizSoUuid) {
                    this.bizSoUuid = bizSoUuid;
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
            }

            public static class BizSoClerkBean {

                private Object topCompanyCode;
                private Object createTime;
                private Object createUserUuid;
                private Object updateTime;
                private Object updateUserUuid;
                private Object sysRemark;
                private Object validFlag;
                private Object version;
                private Object uuid;
                private Object bizSoDetailUuid;
                private Object bizType;
                private Object bizTypeClassify;
                private Object num;
                private String clerk01Uuid;
                private String clerk02Uuid;
                private String clerk03Uuid;
                private Object clerk04Uuid;
                private Object clerk05Uuid;
                private Object clerk06Uuid;
                private Object clerk07Uuid;
                private Object clerk08Uuid;
                private Object clerk09Uuid;
                private Object clerk10Uuid;
                private Object remark;
                private Object companyUuid;
                private Object id;

                public Object getTopCompanyCode() {
                    return topCompanyCode;
                }

                public void setTopCompanyCode(Object topCompanyCode) {
                    this.topCompanyCode = topCompanyCode;
                }

                public Object getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Object createTime) {
                    this.createTime = createTime;
                }

                public Object getCreateUserUuid() {
                    return createUserUuid;
                }

                public void setCreateUserUuid(Object createUserUuid) {
                    this.createUserUuid = createUserUuid;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public Object getUpdateUserUuid() {
                    return updateUserUuid;
                }

                public void setUpdateUserUuid(Object updateUserUuid) {
                    this.updateUserUuid = updateUserUuid;
                }

                public Object getSysRemark() {
                    return sysRemark;
                }

                public void setSysRemark(Object sysRemark) {
                    this.sysRemark = sysRemark;
                }

                public Object getValidFlag() {
                    return validFlag;
                }

                public void setValidFlag(Object validFlag) {
                    this.validFlag = validFlag;
                }

                public Object getVersion() {
                    return version;
                }

                public void setVersion(Object version) {
                    this.version = version;
                }

                public Object getUuid() {
                    return uuid;
                }

                public void setUuid(Object uuid) {
                    this.uuid = uuid;
                }

                public Object getBizSoDetailUuid() {
                    return bizSoDetailUuid;
                }

                public void setBizSoDetailUuid(Object bizSoDetailUuid) {
                    this.bizSoDetailUuid = bizSoDetailUuid;
                }

                public Object getBizType() {
                    return bizType;
                }

                public void setBizType(Object bizType) {
                    this.bizType = bizType;
                }

                public Object getBizTypeClassify() {
                    return bizTypeClassify;
                }

                public void setBizTypeClassify(Object bizTypeClassify) {
                    this.bizTypeClassify = bizTypeClassify;
                }

                public Object getNum() {
                    return num;
                }

                public void setNum(Object num) {
                    this.num = num;
                }

                public String getClerk01Uuid() {
                    return clerk01Uuid;
                }

                public void setClerk01Uuid(String clerk01Uuid) {
                    this.clerk01Uuid = clerk01Uuid;
                }

                public String getClerk02Uuid() {
                    return clerk02Uuid;
                }

                public void setClerk02Uuid(String clerk02Uuid) {
                    this.clerk02Uuid = clerk02Uuid;
                }

                public String getClerk03Uuid() {
                    return clerk03Uuid;
                }

                public void setClerk03Uuid(String clerk03Uuid) {
                    this.clerk03Uuid = clerk03Uuid;
                }

                public Object getClerk04Uuid() {
                    return clerk04Uuid;
                }

                public void setClerk04Uuid(Object clerk04Uuid) {
                    this.clerk04Uuid = clerk04Uuid;
                }

                public Object getClerk05Uuid() {
                    return clerk05Uuid;
                }

                public void setClerk05Uuid(Object clerk05Uuid) {
                    this.clerk05Uuid = clerk05Uuid;
                }

                public Object getClerk06Uuid() {
                    return clerk06Uuid;
                }

                public void setClerk06Uuid(Object clerk06Uuid) {
                    this.clerk06Uuid = clerk06Uuid;
                }

                public Object getClerk07Uuid() {
                    return clerk07Uuid;
                }

                public void setClerk07Uuid(Object clerk07Uuid) {
                    this.clerk07Uuid = clerk07Uuid;
                }

                public Object getClerk08Uuid() {
                    return clerk08Uuid;
                }

                public void setClerk08Uuid(Object clerk08Uuid) {
                    this.clerk08Uuid = clerk08Uuid;
                }

                public Object getClerk09Uuid() {
                    return clerk09Uuid;
                }

                public void setClerk09Uuid(Object clerk09Uuid) {
                    this.clerk09Uuid = clerk09Uuid;
                }

                public Object getClerk10Uuid() {
                    return clerk10Uuid;
                }

                public void setClerk10Uuid(Object clerk10Uuid) {
                    this.clerk10Uuid = clerk10Uuid;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }

                public Object getCompanyUuid() {
                    return companyUuid;
                }

                public void setCompanyUuid(Object companyUuid) {
                    this.companyUuid = companyUuid;
                }

                public Object getId() {
                    return id;
                }

                public void setId(Object id) {
                    this.id = id;
                }
            }
        }
    }
}
