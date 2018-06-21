package ru.otus.skuznets.hw12.cache;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {
	
	private static final int TIME_THRESHOLD_MS = 5;
	private int maxElements;
	private int missCount;
	private int hitCount;
	
	private final Map<K, SoftReference<Element<K, V>>> elements = new HashMap<>();
	
	public CacheEngineImpl(int maxElements) {
		this.maxElements = maxElements;
	}
	
	@Override
	public void put(Element<K, V> element) {
		if(elements.size() == maxElements) {
			K firstKey = elements.keySet().iterator().next();
			elements.remove(firstKey);
			System.out.printf("Element with id %s was removed", firstKey);
		}
		
		K key = element.getKey();
		elements.put(key, new SoftReference<>(element));
		
	}

	public V get(K key) {
		if(elements.size() == 0) {
			missCount++;
			return null;
		}
		SoftReference<Element<K, V>> element = elements.get(key);
		if(element != null) {
			Element<K, V> myElement = element.get();
			if(elements.containsKey(key)) {
				return myElement.getValue();
			}
		}
		missCount++;
		return null;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getMissCount() {
		return missCount;
	}
	
	private TimerTask getTimerTask(final K key, final Function<SoftReference<Element<K, V>>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<Element<K, V>> element = elements.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }
	
	private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
	
	public void setCacheHitCount (int hitCount) {
		this.hitCount = hitCount;
	}

	@Override
	public int getElements() {
		return maxElements;
	}
	
}
