package ru.otus.skuznets.hw13;

public interface DBService {

	void save(UserDataSet user);
	
	UserDataSet load(long id);
	
	void getCacheData();
	
	void close();
	
}
