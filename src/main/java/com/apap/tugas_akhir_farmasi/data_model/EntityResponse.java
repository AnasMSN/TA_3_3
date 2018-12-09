package com.apap.tugas_akhir_farmasi.data_model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityResponse {
    @JsonProperty("result")
    List<Staf> staf;

    public List<Staf> getStaf() {
        return staf;
    }
}
