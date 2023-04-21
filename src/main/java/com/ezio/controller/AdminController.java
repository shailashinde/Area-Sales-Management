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

	// Sign IN
		@PostMapping("/admin-sign-up-API")
		public String adminSignIn(@ModelAttribute("admin") Admin admin,HttpSession session, Model model) {
			String emaildata=admin.getA_email();
			Admin result = adminimpl.adminsignUp(admin);
			if(result != null) {
					String email=result.getA_email();
				
					if (email.equals(emaildata)) {
					session.setAttribute("a_role", result.getA_role());
					session.setAttribute("a_name", result.getA_name());
					model.addAttribute("successmsg","Successfully Login");
					return "home";
					
					} else {
						model.addAttribute("error", "An error occurred");
						return "login_page"; 
					}
			  } else {
				return "login_page";
			}
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
		public void saveAdmin(Admin admin) {
			adminimpl.saveAdmin(admin);
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
		public Admin getByAdminId(Long a_id) {
			System.err.println("admin id=" + a_id);
			Admin admin = adminimpl.getAdminByID(a_id);
			System.err.println("Admin Data" + admin);
			return admin;
		}

//Delete
		@GetMapping(value = "/delete-admin-API")
		@ResponseBody
		public void deleteAdmin(Long a_id) {
			System.out.println("Admin ID=" + a_id);
			adminimpl.deletAdmin(a_id);
		}

}
