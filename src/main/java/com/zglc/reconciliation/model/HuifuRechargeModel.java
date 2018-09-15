package com.zglc.reconciliation.model;

import java.util.Date;

public class HuifuRechargeModel {

    private String merTransId;
    private Date operDate;
    private long amount;
    private String merUserId;
    private String merUserName;
    private String bankName;
    private String type;
    private long fee;
    private String merFeeId;
    private long actualAmount;
    private String status;
    private String statusRemark;
    private String finDate;
    private String fileName;

    public String getMerTransId() {
        return merTransId;
    }

    public void setMerTransId(String merTransId) {
        this.merTransId = merTransId;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getMerUserId() {
        return merUserId;
    }

    public void setMerUserId(String merUserId) {
        this.merUserId = merUserId;
    }

    public String getMerUserName() {
        return merUserName;
    }

    public void setMerUserName(String merUserName) {
        this.merUserName = merUserName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getMerFeeId() {
        return merFeeId;
    }

    public void setMerFeeId(String merFeeId) {
        this.merFeeId = merFeeId;
    }

    public long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
    }

    public String getFinDate() {
        return finDate;
    }

    public void setFinDate(String finDate) {
        this.finDate = finDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "HuifuRechargeModel{" +
                "merTransId='" + merTransId + '\'' +
                ", operDate=" + operDate +
                ", amount=" + amount +
                ", merUserId='" + merUserId + '\'' +
                ", merUserName='" + merUserName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", type='" + type + '\'' +
                ", fee=" + fee +
                ", merFeeId='" + merFeeId + '\'' +
                ", actualAmount=" + actualAmount +
                ", status='" + status + '\'' +
                ", statusRemark='" + statusRemark + '\'' +
                ", fileName=" + fileName +
                ", finDate='" + finDate + '\'' +
                '}';
    }
}
