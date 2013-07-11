package com.ses.util.collection.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ObservableMap<K, V> implements Map<K, V> {
	
	private final Map<K, V> map;
	
	private Collection<MapObserver> observers = new ArrayList<MapObserver>();
	
	// TODO protected fires

	public ObservableMap(Map<K, V> map) {
		super();
		this.map = map;
	}
	
	public Collection<MapObserver> getObservers() {
		return observers;
	}
	
	public void setObservers(Collection<MapObserver> observers) {
		this.observers = observers;
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public V get(Object key) {
		return map.get(key);
	}

	public V put(K key, V value) {
		return map.put(key, value);
	}

	public V remove(Object key) {
		return map.remove(key);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		map.putAll(m);
	}

	public void clear() {
		map.clear();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	public Collection<V> values() {
		return map.values();
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	public boolean equals(Object o) {
		return map.equals(o);
	}

	public int hashCode() {
		return map.hashCode();
	}
	
	

}
