package ru.otus.skuznets.hw11;

public interface CacheEngine<K, V> {

	void put (Element<K, V> element);
	
	V get(K key);
	
	int getHitCount();
	
	int getMissCount();
	
}
