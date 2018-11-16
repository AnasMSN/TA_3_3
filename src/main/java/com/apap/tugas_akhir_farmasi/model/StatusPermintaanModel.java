package com.apap.tugas_akhir_farmasi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "status_permintaan")
public class StatusPermintaanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi",nullable = false)
    private String deskripsi;



}
