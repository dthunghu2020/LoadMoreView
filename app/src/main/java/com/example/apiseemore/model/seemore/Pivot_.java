package com.example.apiseemore.model.seemore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot_ {

    @SerializedName("diary_id")
    @Expose
    private Integer diaryId;
    @SerializedName("fertilizer_id")
    @Expose
    private Integer fertilizerId;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getFertilizerId() {
        return fertilizerId;
    }

    public void setFertilizerId(Integer fertilizerId) {
        this.fertilizerId = fertilizerId;
    }

}