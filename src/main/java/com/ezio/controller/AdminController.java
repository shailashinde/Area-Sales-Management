package com.ezio.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ezio.model.Admin;
import com.ezio.service.AdminServiceImpl;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminServiceImpl adminimpl;
	String uploadProductDirectory = System.getProperty("user.dir") + "/uploads/";

	
//sign in
	@PostMapping(value = "/admin-sign-in-API")
	public String signInAdmin(@ModelAttribute("admin") Admin admin,HttpSession session, Model model) {	
		Admin result= adminimpl.adminSignIn(admin);
		if(result != null) {
			session.setAttribute("adminrole", result.getAdminrole());
			session.setAttribute("a_name",result.getAdminname());
			return "home";}
			else 
				return "login_page";	
		
		
	}
		 
//find All
		@GetMapping(value = "/find-all-admin-api")
		@ResponseBody
		public List<Admin> findAllAdmin(){
			return adminimpl.findAllAdmin();
		}
//save	
		@PostMapping(value = "/save-admin-API")
		@ResponseBody
		public boolean saveAdmin(Admin admin) {
			System.err.println("Admin"+admin);
			String email=admin.getAdminemail();
			
			boolean result=adminimpl.existByEmail(email);
			
			System.err.println("email exist :"+result);
			if(result) {
				return false;// email Exist
			}else {
				adminimpl.saveAdmin(admin);
				return true;
			}
			
	}
		/*
		 * public void saveAdmin(Admin admin,@RequestParam("a_img") MultipartFile a_img)
		 * { System.err.println("1st call"); File f = new File(uploadProductDirectory);
		 * if (!f.exists()) { f.mkdirs(); } System.err.println("2nd call"); String
		 * dateName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()); String
		 * adm_img = dateName + "-" + a_img .getOriginalFilename().replace(" ",
		 * "-").toLowerCase(); Path fileNameAndPath_a_img =
		 * Paths.get(uploadProductDirectory, adm_img);
		 * System.err.println("3rd call"+dateName+"   "+ adm_img+
		 * "  "+fileNameAndPath_a_img); try { Files.write(fileNameAndPath_a_img,
		 * a_img.getBytes()); }catch (Exception e) { e.printStackTrace(); } String
		 * str=""; admin.setA_img(str + fileNameAndPath_a_img);
		 * System.out.println("save method call  " + admin); adminimpl.saveAdmin(admin);
		 * }
		 * 
		 *///Get by ID
		@PostMapping("/get-by-admin-id")
		@ResponseBody
		public Admin getByAdminId(Long adminid) {
			//System.err.println("admin id=" + adminid);
			Admin admin = adminimpl.getAdminByID(adminid);
			//System.err.println("Admin Data" + admin);
			return admin;
		}

//Delete
		@GetMapping(value = "/delete-admin-API")
		@ResponseBody
		public void deleteAdmin(Long adminid) {
			System.out.println("Admin ID=" + adminid);
			adminimpl.deletAdmin(adminid);
		}

}
