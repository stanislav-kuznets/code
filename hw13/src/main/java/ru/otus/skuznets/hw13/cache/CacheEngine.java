package ru.otus.skuznets.hw13.cache;

public interface CacheEngine<K, V> {

	void put (Element<K, V> element);
	
	V get(K key);
	
	int getHitCount();
	
	int getMissCount();
	
	int getElements();
	
}
