package com.ezio.service;

import java.util.List;

import com.ezio.model.Admin;

public interface AdminService {

	
	public List<Admin> findAllAdmin();
	public void saveAdmin(Admin admin);
	public Admin getAdminByID(long a_id);
	public void deletAdmin(long a_id);
}
