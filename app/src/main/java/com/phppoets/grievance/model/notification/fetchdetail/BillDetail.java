package com.phppoets.grievance.model.notification.fetchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BillDetail
{

    @SerializedName("LableName")
    @Expose
    private String lableName;
    @SerializedName("LableValue")
    @Expose
    private String lableValue;

    public String getLableName()
    {
        return lableName;
    }

    public void setLableName(String lableName)
    {
        this.lableName = lableName;
    }

    public String getLableValue()
    {
        return lableValue;
    }

    public void setLableValue(String lableValue)
    {
        this.lableValue = lableValue;
    }
}