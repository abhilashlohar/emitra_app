package com.phppoets.grievance.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by dharmaraj on 21/3/17.
 */
public class BaseClass
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
