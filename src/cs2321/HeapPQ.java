package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Sequence. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Jiangqiu Shen
 */

public class HeapPQ<K extends Comparable<K>,V> implements AdaptablePriorityQueue<K,V> {
	private Comparator<K> comp;
	private ArrayList<Entry<K,V>> myList=new ArrayList<>();
	private class MyEntry<K,V> implements Entry<K,V>{
		K k;
		V v;
		int index;
		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}
		public MyEntry(K tk,V tv,int i){
			k=tk;
			v=tv;
			index=i;
		}
		public void setIndex(int i){
			index=i;
		}

		public int getIndex() {
			return index;
		}
		public void setK(K key){
			k=key;
		}

		public void setV(V v) {
			this.v = v;
		}
	}
	private void check(K key) throws IllegalArgumentException{
		try {
			if(comp.compare(key, key)!=0){
				throw new IllegalArgumentException();
			}

		} catch (ClassCastException e) {
			throw new IllegalArgumentException();
		}
	}
	protected int compare(Entry<K,V> e1,Entry<K,V> e2){
		return comp.compare(e1.getKey(), e2.getKey());
	}
	public HeapPQ() {
		this(new DefaultComparator<>());
	}
	
	public HeapPQ(Comparator<K> c) {
		comp=c;
	}
	
	private int parent(int j){
		return (j-1)/2;
	}
	private int left(int j){
		return 2*j+1;
	}
	private int right(int j){
		return 2*j+2;
	}
	private void swap(int i,int j){
		MyEntry temp1 = (MyEntry)myList.get(i);
		temp1.setIndex(j);
		((MyEntry)myList.get(j)).setIndex(i);
		myList.set(i,myList.get(j));
		myList.set(j,temp1);

	}
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param j move the entry at index j higher if necessary, to restore the heap property
	 */
	public void upheap(int j){
		while(j>0){
			int up=parent(j);
			if(compare(myList.get(j),myList.get(up))<0){
				swap(up,j);
				j=up;
			}else{
				break;
			}
			
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param j move the entry at index j lower if necessary, to restore the heap property
	 */
	public void downheap(int j){
		while (left(j)<size()){
			int min=left(j);
			if(right(j)<size()){
				if(compare(myList.get(left(j)),myList.get(right(j)))>0){
					min=right(j);
				}
			}
			if(compare(myList.get(j),myList.get(min))>0){
				swap(j,min);
				j=min;
			}else{
				break;
			}
		}
	}

	@Override
	public int size() {
		return myList.size();
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		check(key);
		Entry<K,V> et=new MyEntry(key,value,size());
		myList.add(size(),et);
		upheap(size()-1);

		return null;
	}

	@Override
	public Entry<K, V> min() {
		if(isEmpty()) return null;
		return myList.get(0);

	}

	@Override
	public Entry<K, V> removeMin() {
		if(isEmpty()) return null;
		Entry<K,V> ret=min();
		swap(0,size()-1);
		myList.remove(size()-1);
		downheap(0);

		return ret;
	}
	protected void bubble(int j){
		if(j>0&&compare(myList.get(j),myList.get(parent(j)))<0){
			upheap(j);
		}else{
			downheap(j);
		}
	}
	protected MyEntry<K,V> valid(Entry<K,V> entry)throws IllegalArgumentException{
		if(!(entry instanceof MyEntry)){
			throw  new IllegalArgumentException();
		}
		MyEntry<K,V> myEntry=(MyEntry<K,V>)entry;
		int index=myEntry.getIndex();
		if(index>=size()||myList.get(index)!=myEntry){
			throw new IllegalArgumentException();
		}
		return  myEntry;
	}

	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		MyEntry<K,V> myEntry=valid(entry);
		int index=myEntry.getIndex();
		if(index==size()-1){
			myList.remove(index);
		}else{
			swap(index,size()-1);
			myList.remove(size()-1);
			bubble(index);
		}

		
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		MyEntry<K,V> myEntry=valid(entry);
		check(key);
		myEntry.setK(key);
		bubble(myEntry.getIndex());

		
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		MyEntry<K,V> myEntry=valid(entry);
		myEntry.setV(value);
		
	}
	
	


}
