package cs2321;

import net.datastructures.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LookupTable<K, V> implements SortedMap<K, V> {
	public static void main(String[] args) {
		SortedMap<Integer,Integer> map=new LookupTable<>();
		for(int i=100;i>0;i--){
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

		System.out.println("the sub map test");
		Iterable<Entry<Integer, Integer>> map1 = map.subMap(30, 40);
		for(Entry<Integer,Integer> i : map1){
			System.out.println(i.getKey());
		}
		System.out.println("test of 30");
		System.out.println(map.ceilingEntry(30).getKey());
		System.out.println(map.higherEntry(30).getKey());
		System.out.println(map.lowerEntry(30).getKey());
		System.out.println("test of 30");
		map.remove(30);
		System.out.println(map.ceilingEntry(30).getKey());
		System.out.println(map.higherEntry(30).getKey());
		System.out.println(map.lowerEntry(30).getKey());
		System.out.println("test	of 200");
		System.out.println(map.ceilingEntry(200));
		System.out.println(map.floorEntry(200).getKey());
		System.out.println(map.higherEntry(200));
		System.out.println(map.lowerEntry(200).getKey());

	}

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
	ArrayList<MapEntry<K,V>> arrayList=new ArrayList<>();
	Comparator<K> comp;
	public LookupTable(){
		this(new DefaultComparator<>());
	}
	
	/* 
	 * @param comparator - on construction, set comparator
	 */
	public LookupTable(Comparator<K> comparator) {
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
		return	arrayList.size();
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}
	private int findIndex(K key,int start,int end){
		if(end<start){
			return end+1;
		}
		int middle=(start+end)/2;
		if(comp.compare(key,arrayList.get(middle).getKey())==0){
			return middle;
		}else if(comp.compare(key,arrayList.get(middle).getKey())<0){
			return findIndex(key,start,middle-1);
		}else{
			return findIndex(key,middle+1,end);
		}
	}

	@Override
	public V get(K key) {
		int index=findIndex(key,0,size()-1);
		if(index==size()|| comp.compare(key,arrayList.get(index).getKey())!=0) return null;
		else return arrayList.get(index).getValue();
	}

	@Override
	public V put(K key, V value) {
		int index=findIndex(key,0,size()-1);
		if(index==size()||comp.compare(key,arrayList.get(index).getKey())!=0){
			arrayList.add(index,new MapEntry<>(key,value));
			return null;
		}else{
			V temp=arrayList.get(index).getValue();
			arrayList.get(index).setValue(value);
			return temp;
		}
	}

	@Override
	public V remove(K key) {
		int index=findIndex(key,0,size()-1);
		if(index==size()||comp.compare(key,arrayList.get(index).getKey())!=0){
			return null;
		}else{

			return arrayList.remove(index).getValue();

		}
	}
//key
	private class KeyIterator implements Iterator<K> {
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

	//value
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
		return  new ValueIterable();
	}


	private class EntryIterator implements Iterator<Entry<K,V>>{

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
			return curr<LookupTable.this.size();
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
	private class EntryIterable implements Iterable<Entry<K,V>>{

		/**
		 * Returns an iterator over elements of type {@code T}.
		 *
		 * @return an Iterator.
		 */
		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}

	private MapEntry<K,V> safeEntry(int index){
		if(index<0||index>=size()){
			return null;
		}else {
			return arrayList.get(index);
		}
	}
	@Override
	public Entry<K, V> firstEntry() {
		return safeEntry(0);
	}

	@Override
	public Entry<K, V> lastEntry() {
		return safeEntry(size()-1);
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		if(key==null){
			throw new IllegalArgumentException();
		}
		int index=findIndex(key,0,size()-1);
		return safeEntry(index);

	}

	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		if(key==null){
			throw new IllegalArgumentException();
		}
		int index=findIndex(key,0,size()-1);
		if(index==size()||comp.compare(key,arrayList.get(index).getKey())!=0)
			index--;
		return safeEntry(index);


	}

	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
		if(key==null){
			throw new IllegalArgumentException();
		}
		int index=findIndex(key,0,size()-1);
		return safeEntry(index-1);
	}

	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		if(key==null){
			throw new IllegalArgumentException();
		}
		int index=findIndex(key,0,size()-1);
		if(index==size()||comp.compare(key,arrayList.get(index).getKey())!=0){
			return safeEntry(index);
		}else{
			return safeEntry(index+1);
		}
	}

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey)
			throws IllegalArgumentException {
		if(fromKey==null||toKey==null){
			throw new IllegalArgumentException();
		}
		if(comp.compare(fromKey,toKey)>=0){
			throw new IllegalArgumentException();
		}
		ArrayList<Entry<K,V>> ret=new ArrayList<>();
		int fromIndex=findIndex(fromKey,0,size());
		while (fromIndex<size()&&comp.compare(arrayList.get(fromIndex).getKey(),toKey)<0){
			ret.add(ret.size(),arrayList.get(fromIndex++));
		}
		return ret;
	}


}
