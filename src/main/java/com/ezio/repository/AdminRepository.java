package com.ezio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.model.Admin;

@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<Admin, Long>{

	boolean existsByAdminemail(String email);
	
	Admin findByAdminemailAndAdminpass(String email, String pass);

	
	
}
