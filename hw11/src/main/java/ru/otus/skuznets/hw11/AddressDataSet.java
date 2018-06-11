package ru.otus.skuznets.hw11;

import javax.persistence.*;

@Entity
@Table(name = "address")

public class AddressDataSet extends DataSet {

	@Column(name = "street")
	private String street;
	
	public AddressDataSet() {
	}

	public AddressDataSet(String street) {
		this.street = street;
	} 
	
	public String getStreet() {
		return street;
	}
	
	public String toString() {
		return String.format("AddressDataSet {street = %s}", street);
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
}
