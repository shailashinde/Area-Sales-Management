package com.ezio.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dealer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dlr_id;
	private String owner_name;
	private String cmp_name;
	private String mobile;
	private String landline;
	private String state;
	private String email;
	private String dist;
	private String city;
	private String buy_from;
	private String month_sale;
	private String dlr_type;
	private String comment;
	private String address;
	private String dlr_code;
	
}
