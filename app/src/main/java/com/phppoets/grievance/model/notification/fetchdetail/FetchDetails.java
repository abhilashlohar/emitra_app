package com.phppoets.grievance.model.notification.fetchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class FetchDetails
{

    @SerializedName("TransactionDetails")
    @Expose
    private TransactionDetails transactionDetails;
    @SerializedName("BillDetails")
    @Expose
    private List<BillDetail> billDetails = null;

    public TransactionDetails getTransactionDetails()
    {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails)
    {
        this.transactionDetails = transactionDetails;
    }

    public List<BillDetail> getBillDetails()
    {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails)
    {
        this.billDetails = billDetails;
    }
}