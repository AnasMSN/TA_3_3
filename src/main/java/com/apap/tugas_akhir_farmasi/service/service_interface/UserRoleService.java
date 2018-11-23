package com.apap.tugas_akhir_farmasi.service.service_interface;

import com.apap.tugas_akhir_farmasi.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	UserRoleModel getUserByUsername(String name);
	boolean cekPassword(String passwordLama, String encodedPassword);
}
