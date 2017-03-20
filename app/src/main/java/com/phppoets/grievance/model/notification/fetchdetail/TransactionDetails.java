package com.phppoets.grievance.model.notification.fetchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class TransactionDetails
{

    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("officeID")
    @Expose
    private String officeID;
    @SerializedName("BillAmount")
    @Expose
    private String billAmount;
    @SerializedName("ConsumerName")
    @Expose
    private String consumerName;
    @SerializedName("consumerKeysValues")
    @Expose
    private String consumerKeysValues;
    @SerializedName("partPaymentAllow")
    @Expose
    private String partPaymentAllow;
    @SerializedName("partPaymentType")
    @Expose
    private String partPaymentType;
    @SerializedName("lookUpId")
    @Expose
    private String lookUpId;

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getOfficeID()
    {
        return officeID;
    }

    public void setOfficeID(String officeID)
    {
        this.officeID = officeID;
    }

    public String getBillAmount()
    {
        return billAmount;
    }

    public void setBillAmount(String billAmount)
    {
        this.billAmount = billAmount;
    }

    public String getConsumerName()
    {
        return consumerName;
    }

    public void setConsumerName(String consumerName)
    {
        this.consumerName = consumerName;
    }

    public String getConsumerKeysValues()
    {
        return consumerKeysValues;
    }

    public void setConsumerKeysValues(String consumerKeysValues)
    {
        this.consumerKeysValues = consumerKeysValues;
    }

    public String getPartPaymentAllow()
    {
        return partPaymentAllow;
    }

    public void setPartPaymentAllow(String partPaymentAllow)
    {
        this.partPaymentAllow = partPaymentAllow;
    }

    public String getPartPaymentType()
    {
        return partPaymentType;
    }

    public void setPartPaymentType(String partPaymentType)
    {
        this.partPaymentType = partPaymentType;
    }

    public String getLookUpId()
    {
        return lookUpId;
    }

    public void setLookUpId(String lookUpId)
    {
        this.lookUpId = lookUpId;
    }
}