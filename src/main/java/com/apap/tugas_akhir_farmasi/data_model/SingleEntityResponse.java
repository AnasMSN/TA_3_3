package com.apap.tugas_akhir_farmasi.data_model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class SingleEntityResponse {
    @JsonProperty("result")
    private Staf staf;

    public Staf getStaf() {
        return staf;
    }
}
