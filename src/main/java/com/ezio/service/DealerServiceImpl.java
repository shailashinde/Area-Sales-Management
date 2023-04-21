package com.ezio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezio.model.Dealer;
import com.ezio.repository.DealerRepository;

@Service
public  class DealerServiceImpl implements DealerService{

	@Autowired
	private DealerRepository dealerrepo;
	
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
}
