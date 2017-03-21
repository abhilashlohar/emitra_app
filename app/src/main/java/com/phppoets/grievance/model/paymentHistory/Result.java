package com.phppoets.grievance.model.paymentHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("prn")
    @Expose
    private String prn;
    @SerializedName("rpptxnid")
    @Expose
    private String rpptxnid;
    @SerializedName("responce")
    @Expose
    private String responce;
    @SerializedName("payment_time")
    @Expose
    private String paymentTime;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getRpptxnid() {
        return rpptxnid;
    }

    public void setRpptxnid(String rpptxnid) {
        this.rpptxnid = rpptxnid;
    }

    public String getResponce() {
        return responce;
    }

    public void setResponce(String responce) {
        this.responce = responce;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
