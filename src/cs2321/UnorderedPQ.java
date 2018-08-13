package cs2321;

import java.util.Comparator;



import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Jiangqiu Shen
 */

public class UnorderedPQ<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	public static void main(String[] args) {
		UnorderedPQ<Integer,Integer> myPq=new UnorderedPQ<>();
		myPq.insert(1, 1);
		myPq.insert(2, 2);
		myPq.insert(3, 3);
		myPq.insert(1, 1);
		myPq.insert(2, 2);
		myPq.insert(3, 3);
		myPq.insert(1, 1);
		myPq.insert(2, 2);
		while(myPq.size()!=0){
			System.out.println(myPq.removeMin().getValue());
		}

	}
	private Comparator<K> comp;

	private DoublyLinkedList<Entry<K,V>> vals=new DoublyLinkedList<>();
	private class MyEntry implements Entry<K,V>{
		K k;
		V v;
		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}
		public MyEntry(K tk,V tv){
			k=tk;
			v=tv;
		}
	

	};
	
	public UnorderedPQ() {
		this(new DefaultComparator<K>());
	}
	
	public UnorderedPQ(Comparator<K> c) {
			comp=c;
	}

	@Override
	public int size() {
		
		return vals.size();
	}

	@Override
	public boolean isEmpty() {
		return vals.size()==0;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		Entry<K,V> entry=new MyEntry(key, value);
		vals.addLast(entry);
		return entry;
	}
	private Position<Entry<K,V>> findMin(){
		Position<Entry<K,V>> min=vals.first();
		for(Position<Entry<K, V>> i:vals.positions()){
			if(comp.compare(i.getElement().getKey(), min.getElement().getKey())<0)
			{
				min=i;
			}
		}
		if(isEmpty()){
			return null;
		}else{
			return min;
		}
	}
	@Override
	public Entry<K, V> min() {
		return findMin().getElement();
		
	}

	@Override
	public Entry<K, V> removeMin() {
		return vals.remove(findMin());
	}
	
	

}
