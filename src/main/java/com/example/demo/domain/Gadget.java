package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Gadget {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String gadgetId;
	
	
	private String gadgetName;
	
	
	private double gadgetPrice;
	
	
	private String description;
	
	@OneToMany(mappedBy="gadget",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Accessory> accessories=new ArrayList<>();
	
	
	public String getGadgetId() {
		return gadgetId;
	}
	public void setGadgetId(String gadgetId) {
		this.gadgetId = gadgetId;
	}
	public String getGadgetName() {
		return gadgetName;
	}
	public void setGadgetName(String gadgetName) {
		this.gadgetName = gadgetName;
	}
	public double getGadgetPrice() {
		return gadgetPrice;
	}
	public void setGadgetPrice(double gadgetPrice) {
		this.gadgetPrice = gadgetPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Accessory> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<Accessory> accessories) {
		this.accessories = accessories;
		for(Accessory a : accessories) {
			a.setGadget(this);
		}
	}
	
	
	
}
