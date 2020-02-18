package com.example.apiseemore.model.seemore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot___ {

    @SerializedName("diary_id")
    @Expose
    private Integer diaryId;
    @SerializedName("transporter_id")
    @Expose
    private Integer transporterId;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

}