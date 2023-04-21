package com.ezio.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.model.Admin;
import com.ezio.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminrepo;
	@Autowired
	private HttpSession httpSession;

	@Override
	public Admin adminsignUp(Admin admin) {
		String a_email = admin.getA_email();
		String a_pass = admin.getA_pass();
		Admin admindata=adminrepo.findByEmailAndPass(a_email, a_pass);
		return admindata; 
	}
	
	@Override
	public List<Admin> findAllAdmin() {
		return adminrepo.findAll(); 	}
	
	@Override
	public void saveAdmin(Admin admin) {
		adminrepo.save(admin);	 }
	
	@Override
	public Admin getAdminByID(long a_id) {
		return adminrepo.findById(a_id).get(); }
	
	@Override
	public void deletAdmin(long a_id) {
		adminrepo.deleteById(a_id); 	}
}


