package ru.otus.skuznets.hw12;

public interface DBService {

	void save(UserDataSet user);
	
	UserDataSet load(long id);
	
	void getCacheData();
	
	void close();
	
}
