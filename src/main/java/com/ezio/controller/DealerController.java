package com.ezio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ezio.model.Dealer;
import com.ezio.model.EmailDetails;
import com.ezio.model.Response;
import com.ezio.service.DealerServiceImpl;

@Controller
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	private DealerServiceImpl dealerimpl;
	

	// Sending a simple Email
	@PostMapping("/send-mail-API")
	@ResponseBody
	public String sendMail( long dlr_id)
	{
		System.err.println("dealar send mail Id"+dlr_id);	
		Dealer dealer=dealerimpl.findById(dlr_id);
		System.err.println("Dealer Data"+ dealer);
		EmailDetails emaildetails=new EmailDetails();
		emaildetails.setRecipient(dealer.getEmail());
		emaildetails.setMsgBody("Hello"+ dealer.getOwner_name()+"welcome");
		emaildetails.setSubject("Product Details");
		String status= dealerimpl.sendSimpleMail(emaildetails);
		System.err.println("Ststus"+status);
		return status;
	}
	
	
	
	
	

	// Sending email with attachment
	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details)
	{
		String status= dealerimpl.sendMailWithAttachment(details);
		return status;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Find all Distributers
	@GetMapping(value = "/find-all-Distributers-API")
	@ResponseBody
	public List<Dealer> findAllDistributers() {
		System.err.println(" check value " + dealerimpl.findAllDistributers());
		return dealerimpl.findAllDistributers();
	}

//Dealer List District Wise
	@PostMapping(value = "/get-dealer-distwise-API")
	@ResponseBody
	public List<Dealer> findByDist(String dist) {

		System.err.println("dist========" + dist);
		List<Dealer> listdlr = dealerimpl.FindByDealerDist(dist);

		System.err.println("Dealer List dist wise" + listdlr);
		return listdlr;
	}

//District List with count
	@GetMapping(value = "/dist-list-API")
	@ResponseBody
	public List<Response> findAllDist() {
		List<String> dist = dealerimpl.findbyDistList();
		List<Response> response = new ArrayList<>();
		for (String dist1 : dist) {
			Response resp = new Response();
			long count = dealerimpl.findByDistCount(dist1);
			resp.setDistrict(dist1);
			resp.setCount(count);
			response.add(resp);
		}
		return response;
	}

	@PostMapping(value = "/dist-count-API")
	@ResponseBody
	public Long findDistCount(String dist) {
		// System.err.println("dist from ui= " + dist);
		// System.err.println(dealerimpl.findByDistCount(dist));
		return dealerimpl.findByDistCount(dist);
	}

	// Find all
	@GetMapping(value = "/find-all-dealer-API")
	@ResponseBody
	public List<Dealer> findAllDealer() {
		// System.err.println(" check value " + dealerimpl.findAllDealer());
		return dealerimpl.findAllDealer();
	}

//save
	@PostMapping(value = "/save-dealer-API")
	@ResponseBody
	public String saveDealer(@ModelAttribute(" dealer") Dealer dealer,HttpSession session) {
			char cmp_letter= dealer.getCmp_name().charAt(0);
			Random random=new Random();
		    String generatedString =cmp_letter+ "-"+random.nextInt();
		    System.out.println(generatedString);
		    dealer.setDlr_code(generatedString);
		System.out.println("save method call  " + dealer);
		dealerimpl.saveDealer(dealer);
		session.setAttribute("alert", " Dealer submitted successfully! ");
		return "Dealer submitted successfully!";
	}

//Get by ID
	@PostMapping("/get-by-dealer-id")
	@ResponseBody
	public Dealer getByDealerId(Long dlr_id) {
		System.err.println("dlr id=" + dlr_id);
		Dealer dealer = dealerimpl.getDealerByID(dlr_id);
		System.err.println("Dlr Data" + dealer);
		return dealer;
	}

//Delete
	@GetMapping(value = "/delete-dealer-API")
	public String deleteDealer(Long dlr_id) {
		// System.out.println("dlr_id delete=" + dlr_id);
		dealerimpl.deletDealer(dlr_id);
		return "AllDealerList";
	}

// find total Dealer Count
	@GetMapping("/total-dealer-count-API")
	@ResponseBody
	public long getDealerCount(Model model) {
		long counttotal = dealerimpl.totalDealerCount();
		System.err.println("total count" + counttotal);
		model.addAttribute("counttotal", counttotal);
		return counttotal;
	}

//gold dealer
	@GetMapping(value = "/find-gold-dealer-API")
	@ResponseBody
	public long getGoldDealer(Model model) {
		long countgold = dealerimpl.findGoldDealer();
		System.err.println("gold count" + countgold);
		model.addAttribute("countgold", countgold);
		return countgold;
	}

//Silver dealer
	@GetMapping(value = "/find-silver-dealer-API")
	@ResponseBody
	public long getSilverDealer(Model model) {
		long countsilver = dealerimpl.findSilverDealer();
		System.err.println("silver count" + countsilver);
		model.addAttribute("countsilver", countsilver);
		return countsilver;
	}

//Bronze dealer
	@GetMapping(value = "/find-bronze-dealer-API")
	@ResponseBody
	public long getBronzeDealer(Model model) {
		long countBronze = dealerimpl.findBronzeDealer();
		System.err.println("bronze count" + countBronze);
		model.addAttribute("count", countBronze);
		return countBronze;
	}
}
