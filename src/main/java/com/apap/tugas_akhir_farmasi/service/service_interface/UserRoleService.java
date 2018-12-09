package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.util.List;

import com.apap.tugas_akhir_farmasi.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	UserRoleModel getUser(String username );
	public String encrypt(String password);
	public boolean usernameCheck(String username);
}
