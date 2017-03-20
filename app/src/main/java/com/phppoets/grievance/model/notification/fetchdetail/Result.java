package com.phppoets.grievance.model.notification.fetchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Result
{

    @SerializedName("FetchDetails")
    @Expose
    private FetchDetails fetchDetails;

    public FetchDetails getFetchDetails()
    {
        return fetchDetails;
    }

    public void setFetchDetails(FetchDetails fetchDetails)
    {
        this.fetchDetails = fetchDetails;
    }
}