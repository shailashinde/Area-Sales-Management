package com.ezio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ezio.model.Dealer;
@Repository("DealerRepository")
public interface DealerRepository extends JpaRepository<Dealer	, Long>{

	@Query(value="SELECT COUNT(*) FROM dealer WHERE dlr_type = 'Gold' ", nativeQuery =true)
	long findByDlr_type_gold();
	@Query(value="SELECT COUNT(*) FROM dealer WHERE dlr_type = 'Silver' ", nativeQuery =true)
	long findByDlr_type_silver();
	@Query(value="SELECT COUNT(*) FROM dealer WHERE dlr_type = 'Bronze' ", nativeQuery =true)
	long findByDlr_type_bronze();
	
	@Query(value="SELECT DISTINCT dist FROM dealer", nativeQuery =true)
	 List<String> findAllDist();
	
	@Query(value="SELECT COUNT(*) FROM dealer WHERE dist = :dist ", nativeQuery =true)
	long findByDistCount(String dist);
	
	@Query(value="SELECT * FROM dealer WHERE dist= :dist ", nativeQuery =true)
	List<Dealer> FindByDealerDist(String dist);
	@Query(value="SELECT * FROM dealer WHERE dlr_type= 'distributer' ", nativeQuery =true)
	List<Dealer> findAllDistributers();

}
