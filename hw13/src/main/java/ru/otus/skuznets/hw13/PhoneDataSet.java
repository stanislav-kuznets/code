package ru.otus.skuznets.hw13;

import javax.persistence.*;

@Entity
@Table(name = "phone")

public class PhoneDataSet extends DataSet {

	@Column(name = "number")
	private String number;
	@ManyToOne
	private UserDataSet	user;
	
	public PhoneDataSet() {
	}
	
	public PhoneDataSet(String number) {
		this.number = number;
	}
	
	public String toString() {
		return String.format("PhoneDataSet {number = %s}", number);
	}
	
	void setUser(UserDataSet user) {
		this.user = user;
	}
}
