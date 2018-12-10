package com.apap.tugas_akhir_farmasi.service.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.apap.tugas_akhir_farmasi.model.UserRoleModel;
import com.apap.tugas_akhir_farmasi.repository.UserRoleDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.UserRoleService;

@Service
@Transactional
@Qualifier(value = "UserRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass =encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}
	
	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel getUser(String username) {
		return userDb.findByUsername(username);
	}
	
	
	
	@Override
	public boolean usernameCheck(String username) {
		List <UserRoleModel> userList = userDb.findAll();
		for (int i=0;i<userList.size();i++) {
			if(userList.get(i).getUsername().equals(username)) {
				return true;
			}
		}
	return false;	
	}
	
	@Override
	public boolean cekPassword(String passwordLama, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(passwordLama, encodedPassword);
		
	}
	
}
