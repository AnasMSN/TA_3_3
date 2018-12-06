package com.apap.tugas_akhir_farmasi.data_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EntityResponse {
    @JsonProperty("result")
    List<Staf> staf;

    public List<Staf> getStaf() {
        return staf;
    }
}
