package cs2321;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K,V> implements Map<K,V>{
	public static void main(String[] args) {
		UnorderedMap<Integer,Integer> map=new UnorderedMap<>();
		for(int i=0;i<100;i++){
			map.put(i,i);
		}
		map.put(100,2);
		map.put(50,2);
		System.out.println(map.get(50));
		System.out.println(map.get(100));

		Iterator<Entry<Integer, Integer>> iterator=map.entrySet().iterator();
		for(int i=0;i<20;i++){
			System.out.println(iterator.next().getKey());

		}
	}


	private Comparator<K> comp;
	private ArrayList<MapEntry<K,V>> arrayList=new ArrayList<>();
	private class MapEntry<K, V> implements Entry<K,V>{
		K key;
		V value;
		private void setKey(K k){
			key=k;
		}
		private void setValue(V v){
			value=v;
		}
		MapEntry(K k,V v){
			key=k;
			value=v;
		}
		/**
		 * Returns the key stored in this entry.
		 *
		 * @return the entry's key
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * Returns the value stored in this entry.
		 *
		 * @return the entry's value
		 */
		@Override
		public V getValue() {
			return value;
		}
	}


	public UnorderedMap() {
		this(new DefaultComparator<K>());
	}


	
	/* 
	 * @param comparator - on construction, set comparator
	 */
	public UnorderedMap(Comparator<K> comparator) {
		comp=comparator;
	}
	
	/*
	 * @param comparator - the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		comp=comparator;
	}
	

	@Override
	public int size() {

		return arrayList.size();
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}
	private int getIndex(K k){
		for(int i=0;i<size();i++){
			if(comp.compare(k,arrayList.get(i).getKey())==0){
				return i;
			}
		}
		return -1;
	}
	@Override
	public V get(K key) {
		int j=getIndex(key);
		if(j==-1){
			return  null;
		}else {
			return arrayList.get(j).getValue();
		}
	}

	@Override
	public V put(K key, V value) {
		int j=getIndex(key);
		if(j==-1) {
			arrayList.add(size(), new MapEntry<>(key, value));
			return null;
		}else {
			V temp=arrayList.get(j).getValue();
			arrayList.get(j).setValue(value);
			return temp;
		}
	}

	@Override
	public V remove(K key) {
		int j=getIndex(key);
		if(j==-1)
			return null;
		else{
			V temp=arrayList.get(j).getValue();
			arrayList.remove(j);
			return temp;
		}
	}


	private class KeyIterator implements Iterator<K>{
		private Iterator<Entry<K,V>> iterator=entrySet().iterator();
		/**
		 * Returns {@code true} if the iteration has more elements.
		 * (In other words, returns {@code true} if {@link #next} would
		 * return an element rather than throwing an exception.)
		 *
		 * @return {@code true} if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public K next() {
			return iterator.next().getKey();
		}
	}
	private class KeyIterable implements Iterable<K>{

		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}
	}
	@Override
	public Iterable<K> keySet() {
		return new KeyIterable();
	}


	private class ValueIterator implements Iterator<V>{
		private Iterator<Entry<K,V>> iterator=entrySet().iterator();
		/**
		 * Returns {@code true} if the iteration has more elements.
		 * (In other words, returns {@code true} if {@link #next} would
		 * return an element rather than throwing an exception.)
		 *
		 * @return {@code true} if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public V next() {
			return iterator.next().getValue();
		}
	}
	private class ValueIterable implements Iterable<V>{

		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}


	private class UnorderedMapIterator implements Iterator<Entry<K,V>>{

		int curr=0;
		/**
		 * Returns {@code true} if the iteration has more elements.
		 * (In other words, returns {@code true} if {@link #next} would
		 * return an element rather than throwing an exception.)
		 *
		 * @return {@code true} if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return curr<UnorderedMap.this.size();
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public Entry<K, V> next() throws NoSuchElementException {
			if(curr>=size()){
				throw new NoSuchElementException();
			}
			return arrayList.get(curr++);
		}
	}
	private class UnorderedMapIterable implements Iterable<Entry<K,V>>{

		/**
		 * Returns an iterator over elements of type {@code T}.
		 *
		 * @return an Iterator.
		 */
		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new UnorderedMapIterator();
		}
	}
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new UnorderedMapIterable();
	}

}
