package com.apap.tugas_akhir_farmasi.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_account")
public class UserRoleModel implements Serializable {
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

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	
}
