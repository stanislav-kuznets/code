package ru.otus.skuznets.hw10;

public interface DBService {

	void save(UserDataSet user);
	
	UserDataSet load(long id);
	
}
