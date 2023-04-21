package com.ezio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezio.model.Admin;

@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<Admin	, Long>{

	
	@Query(value="select * from admin where a_email=:a_email and a_pass=:a_pass", nativeQuery =true)
	Admin findByEmailAndPass(String a_email, String a_pass);

	
}
