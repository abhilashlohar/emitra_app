package com.phppoets.grievance.model.notification.fetchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by dharmaraj on 20/3/17.
 */
public class FetchDetailResult
{
    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult()
    {
        return result;
    }

    public void setResult(Result result)
    {
        this.result = result;
    }
}
