package com.phppoets.grievance.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Result
{

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("user_id")
    @Expose
    private String userId;

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}