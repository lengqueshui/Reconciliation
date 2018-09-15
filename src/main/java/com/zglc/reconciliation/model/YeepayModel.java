package com.zglc.reconciliation.model;

import java.util.Date;

public class YeepayModel {

    private int index;
    private Date date;
    private String accountType;
    private String busiType;
    private String merTransId;
    private long amount;
    private long outAmount;
    private long fee;
    private long balance;
    private String fileName;
    private String remark;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getMerTransId() {
        return merTransId;
    }

    public void setMerTransId(String merTransId) {
        this.merTransId = merTransId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(long outAmount) {
        this.outAmount = outAmount;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "YeepayModel{" +
                "index=" + index +
                ", date=" + date +
                ", accountType='" + accountType + '\'' +
                ", busiType='" + busiType + '\'' +
                ", merTransId='" + merTransId + '\'' +
                ", amount=" + amount +
                ", outAmount=" + outAmount +
                ", fee=" + fee +
                ", balance=" + balance +
                ", fileName=" + fileName +
                ", remark='" + remark + '\'' +
                '}';
    }
}
