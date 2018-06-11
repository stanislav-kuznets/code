package ru.otus.skuznets.hw11;

public class Element<K, V> {
	
	private final K key;
	private final V value;
	
	public Element(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

}
