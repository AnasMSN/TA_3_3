package com.apap.tugas_akhir_farmasi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "username" ,nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password" ,nullable = false)
    private String password;

    @NotNull
    @Size(max = 255)
    @Column(name = "role" ,nullable = false)
    private String role;



}
