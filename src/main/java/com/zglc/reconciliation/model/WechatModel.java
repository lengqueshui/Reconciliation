package com.zglc.reconciliation.model;

import java.util.Date;

public class WechatModel {

    //交易时间	公众账号ID	商户号	子商户号	设备号
    // 微信订单号 商户订单号 	用户标识	交易类型	交易状态
    // 付款银行	货币种类	总金额 企业红包金额	微信退款单号
    // 商户退款单号	退款金额 企业红包退款金额	退款类型	退款状态
    // 商品名称	商户数据包	手续费	费率
    private Date date;
    private String appId;
    private String merId;
    private String childMerId;
    private String deviceId;

    private String wxTransId;
    private String merTransId;
    private String wxUserNo;//用户标识
    private String type;
    private String status;

    private String bankNo;
    private String ccyNo;
    private long amount;
    private long hongbaoAmount;
    private String wxRefundTransId;

    private String merRefundTransId;
    private long refundAmount;
    private long refundHongbaoAmount;
    private String refundType;
    private String refundStatus;

    private String productName;
    private String merDataPackage;
    private long fee;
    private String feePercent;
    private String fileName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        merId = merId;
    }

    public String getChildMerId() {
        return childMerId;
    }

    public void setChildMerId(String childMerId) {
        this.childMerId = childMerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getWxTransId() {
        return wxTransId;
    }

    public void setWxTransId(String wxTransId) {
        this.wxTransId = wxTransId;
    }

    public String getMerTransId() {
        return merTransId;
    }

    public void setMerTransId(String merTransId) {
        this.merTransId = merTransId;
    }

    public String getWxUserNo() {
        return wxUserNo;
    }

    public void setWxUserNo(String wxUserNo) {
        this.wxUserNo = wxUserNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getCcyNo() {
        return ccyNo;
    }

    public void setCcyNo(String ccyNo) {
        this.ccyNo = ccyNo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getHongbaoAmount() {
        return hongbaoAmount;
    }

    public void setHongbaoAmount(long hongbaoAmount) {
        this.hongbaoAmount = hongbaoAmount;
    }

    public String getWxRefundTransId() {
        return wxRefundTransId;
    }

    public void setWxRefundTransId(String wxRefundTransId) {
        this.wxRefundTransId = wxRefundTransId;
    }

    public String getMerRefundTransId() {
        return merRefundTransId;
    }

    public void setMerRefundTransId(String merRefundTransId) {
        this.merRefundTransId = merRefundTransId;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public long getRefundHongbaoAmount() {
        return refundHongbaoAmount;
    }

    public void setRefundHongbaoAmount(long refundHongbaoAmount) {
        this.refundHongbaoAmount = refundHongbaoAmount;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerDataPackage() {
        return merDataPackage;
    }

    public void setMerDataPackage(String merDataPackage) {
        this.merDataPackage = merDataPackage;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(String feePercent) {
        this.feePercent = feePercent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "WechatModel{" +
                "date=" + date +
                ", appId='" + appId + '\'' +
                ", merId='" + merId + '\'' +
                ", childMerId='" + childMerId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", wxTransId='" + wxTransId + '\'' +
                ", merTransId='" + merTransId + '\'' +
                ", wxUserNo='" + wxUserNo + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", ccyNo='" + ccyNo + '\'' +
                ", amount=" + amount +
                ", hongbaoAmount=" + hongbaoAmount +
                ", wxRefundTransId='" + wxRefundTransId + '\'' +
                ", merRefundTransId='" + merRefundTransId + '\'' +
                ", refundAmount=" + refundAmount +
                ", refundHongbaoAmount=" + refundHongbaoAmount +
                ", refundType='" + refundType + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", productName='" + productName + '\'' +
                ", merDataPackage='" + merDataPackage + '\'' +
                ", fee=" + fee +
                ", feePercent='" + feePercent + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
