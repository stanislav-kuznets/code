package ru.otus.skuznets.hw12.Cache;

public interface CacheEngine<K, V> {

	void put (Element<K, V> element);
	
	V get(K key);
	
	int getHitCount();
	
	int getMissCount();
	
	int getElements();
	
}
