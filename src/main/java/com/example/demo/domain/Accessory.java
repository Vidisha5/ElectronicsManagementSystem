package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Accessory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String accessoryId;
	
	private String accessoryName;
	
	private double accessoryPrice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gadget_id")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Gadget gadget;

	public String getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryId(String accessoryId) {
		this.accessoryId = accessoryId;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public double getAccessoryPrice() {
		return accessoryPrice;
	}

	public void setAccessoryPrice(double accessoryPrice) {
		this.accessoryPrice = accessoryPrice;
	}

	public Gadget getGadget() {
		return gadget;
	}

	public void setGadget(Gadget gadget) {
		this.gadget = gadget;
	}
	
	
}
