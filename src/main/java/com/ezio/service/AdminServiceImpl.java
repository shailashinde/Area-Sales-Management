package com.ezio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ezio.model.Admin;
import com.ezio.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminrepo;
	@Autowired

	
	@Override
	public List<Admin> findAllAdmin() {
		return adminrepo.findAll(); 	}
	
	@Override
	public void saveAdmin(Admin admin) {
		adminrepo.save(admin);	 }
	
	@Override
	public Admin getAdminByID(long adminid) {
		return adminrepo.findById(adminid).get(); }
	
	@Override
	public void deletAdmin(long adminid) {
		adminrepo.deleteById(adminid); 	}

	public boolean existByEmail(String email) {
		return adminrepo.existsByAdminemail(email);
		
	}

	public Admin adminSignIn(Admin admin) {
		String email=admin.getAdminemail();
		String pass= admin.getAdminpass();
		Admin admins= adminrepo.findByAdminemailAndAdminpass(email,pass);
		System.err.println("impl"+admins);
		 return admins;
	}}




