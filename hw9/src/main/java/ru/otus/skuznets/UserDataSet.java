package ru.otus.skuznets.hw9;

public class UserDataSet extends DataSet {
	
	public String name;
	public int age;
	
	public UserDataSet() {	
	}
	
	public UserDataSet(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return String.format("Имя: %s \nВозраст: %d", name, age);
	}

	public void setName (String name) {
		this.name = name;
	}
	
	public void setAge (int age) {
		this.age = age;
	}
}
