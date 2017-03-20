package com.phppoets.grievance.model.addForm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("grievance_id")
    @Expose
    private Integer grievanceId;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getGrievanceId() {
        return grievanceId;
    }

    public void setGrievanceId(Integer grievanceId) {
        this.grievanceId = grievanceId;
    }

}
