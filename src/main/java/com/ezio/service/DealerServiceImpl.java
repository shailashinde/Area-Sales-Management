package com.ezio.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.ezio.model.Dealer;
import com.ezio.model.EmailDetails;
import com.ezio.repository.DealerRepository;

@Service
public  class DealerServiceImpl implements DealerService{

	@Autowired
	private DealerRepository dealerrepo;
	@Autowired private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}") private String sender;

	
	@Override
	public void saveDealer(Dealer dealer) {
		dealerrepo.save(dealer);	}

	@Override
	public List<Dealer> findAllDealer() {
		List<Dealer> dealer=dealerrepo.findAll();
		return dealer;   }

		@Override
	public long totalDealerCount() {
		return dealerrepo.count();	}

	@Override
	public long findSilverDealer() {
		return dealerrepo.findByDlr_type_silver();
	}

	@Override
	public long findBronzeDealer() {
		return dealerrepo.findByDlr_type_bronze();
	}

	@Override
	public Dealer getDealerByID(Long dlr_id) {
		return dealerrepo.findById(dlr_id).get();
	}

	@Override
	public void deletDealer(Long dlr_id) {
		dealerrepo.deleteById(dlr_id);
		
	}

	@Override
	public long findGoldDealer() {
		return dealerrepo.findByDlr_type_gold();
	}

	@Override
	public List<Long> findbyDistCount() {
		List<String> dist= dealerrepo.findAllDist();
		List<Long> countdist=new  ArrayList<>();
		for(String d: dist) {
		long count=dealerrepo.findByDistCount(d);
		countdist.add(count);
		}
		return countdist;
	}
	@Override
	public List<String> findbyDistList() {
		return dealerrepo.findAllDist(); }

	@Override
	public long findByDistCount(String dist) {
		return dealerrepo.findByDistCount(dist);
	}

	@Override
	public List<Dealer> FindByDealerDist(String dist) {
		return dealerrepo.FindByDealerDist( dist);
	}

	@Override
	public List<Dealer> findAllDistributers() {
		return dealerrepo.findAllDistributers();
	}

	
//simple mail send
	@Override
	public String sendSimpleMail(EmailDetails details) {
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage= new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			
			return "Mail Sent Successfully...";
		}catch (Exception e) {
			return "Error while Sending Mail";
		}

	}

	// Method 2
	// To send an email with attachment
			
	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		// Creating a mime message
					MimeMessage mimeMessage	= javaMailSender.createMimeMessage();
					MimeMessageHelper mimeMessageHelper;

					try {

						// Setting multipart as true for attachments to
						
						mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
						mimeMessageHelper.setFrom(sender);
						mimeMessageHelper.setTo(details.getRecipient());
						mimeMessageHelper.setText(details.getMsgBody());
						mimeMessageHelper.setSubject(
							details.getSubject());

						// Adding the attachment
						FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

						mimeMessageHelper.addAttachment(file.getFilename(), file);

						// Sending the mail
						javaMailSender.send(mimeMessage);
						return "Mail sent Successfully";
					}catch (MessagingException e) {
						// Display message when exception occurred
						return "Error while sending mail!!!";
					}
	}

	@Override
	public Dealer findById(long dlr_id) {
		return dealerrepo.findById(dlr_id).get();
	}
}
