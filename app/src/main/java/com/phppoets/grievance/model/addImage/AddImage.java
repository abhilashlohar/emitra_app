package com.phppoets.grievance.model.addImage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddImage {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}