package cs2321;

import java.util.Comparator;

import net.datastructures.*;

public class HashMap<K, V> implements Map<K, V> {
	
	/**
	 * Constructor that takes a hash size
	 * @param hashsize The number of buckets to initialize in the HashMap
	 * @param comparator - on construction, set comparator
	 */
	public HashMap(int hashsize, Comparator<K> comparator){
		// TODO: Add necessary initialization
	}

	/**
	 * Constructor that takes a hash size
	 * @param hashsize The number of buckets to initialize in the HashMap
	 */
	public HashMap(int hashsize) {
		// TODO Add necessary initialization
	}
	
	/*
	 * @param comparator - the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		// TODO Add necessary initialization
	}
	
	public HashMap(){
		// TODO: Be sure to initialize the bucket array
		//       using the default hash size provided.
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}



}
