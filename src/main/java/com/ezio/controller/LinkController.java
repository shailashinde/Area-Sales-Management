package com.ezio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {

	@RequestMapping("/")
	public String LoginPageView() {
		return "login_page"; // AllAdmin  //login_page
	}
	
	@GetMapping("/dashboard-api")
	public String homePageView() {
		return "home";}
	
	@GetMapping("/save-dealer-api")
	public String savedealerPageView() {
		return "savedealer";}
	
	@GetMapping("/show-dealer-api")
	public String showdealerPageView() {
		return "AllDealerList";}
	
	@GetMapping("/show-profile-api")
	public String showProfilePageView() {
		return "profile";}
	
	@GetMapping("/show-DistTable-api")
	public String showDistTablePageView() {
		return "AllDistrictList";}
	
	@GetMapping("/show-DistributerTable-api")
	public String showDistributerTablePageView() {
		return "AllDistributersList";}
	@GetMapping("/	")
	public String showAdminDahsboardPageView() {
		return "admindashboard";}
	@GetMapping("/show-all-admin-api")
	public String showAllAdmin() {
		return "AllAdmin";}
	
}
