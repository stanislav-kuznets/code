package ru.otus.skuznets.hw10;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")

public class UserDataSet extends DataSet {

	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AddressDataSet address;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PhoneDataSet> phones;
	
	public UserDataSet() {
		
	}
	
	public UserDataSet(long id, String name, int age) {
		this.setId(-1);
		this.name = name;
		this.age = age;
	}
	
	public UserDataSet(String name, int age, AddressDataSet address, List<PhoneDataSet> phones) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phones = phones;
		for (PhoneDataSet phone: phones) {
			phone.setUser(this);
		}
	}
	
	@Override
	public String toString() {
		return String.format("UserDataSet{name: %s, age: %d, address: %s, phones: %s}", name, age, address, phones);
	}
}
