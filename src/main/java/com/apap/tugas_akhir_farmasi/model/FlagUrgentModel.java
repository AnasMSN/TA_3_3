package com.apap.tugas_akhir_farmasi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "flag_urgent")
public class FlagUrgentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "flag",nullable = false)
    private short flag;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi_flag_urgent")
    private String deskripsiFlagUrgent;
}
