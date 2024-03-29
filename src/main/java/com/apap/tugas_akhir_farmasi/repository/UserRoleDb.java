package com.apap.tugas_akhir_farmasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas_akhir_farmasi.model.UserRoleModel;

@Repository
public interface UserRoleDb extends JpaRepository<UserRoleModel,Long> {
	UserRoleModel findByUsername(String username);
}
