package com.ezio.service;

import java.util.List;

import com.ezio.model.Dealer;

public interface DealerService {

	
	public List<Dealer> findAllDealer();
	public List<Dealer> FindByDealerDist(String dist);
	public void saveDealer(Dealer dealer);
	public Dealer getDealerByID(Long dlr_id);
	public void deletDealer(Long dlr_id);
	public long findGoldDealer();
	public long totalDealerCount();
	public long findSilverDealer();
	public long findBronzeDealer();
	public List<String> findbyDistList();
	public List<Long> findbyDistCount();
	public long findByDistCount(String dist);
	public List<Dealer> findAllDistributers();
}
