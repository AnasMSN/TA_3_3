package com.apap.tugas_akhir_farmasi.data_model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StafResponse {
    @JsonProperty("result")
    Staf staf;

    public Staf getStaf() {
        return staf;
    }
}
