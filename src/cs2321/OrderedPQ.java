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

public class OrderedPQ<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	public static void main(String[] args) {
		System.out.println("Ordered");
		OrderedPQ<Integer,Integer> myPq=new OrderedPQ<>();
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
	
	public OrderedPQ() {
		this(new DefaultComparator<>());
	}
	
	public OrderedPQ(Comparator<K> c) {
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

	protected void Check(K key) throws IllegalArgumentException{
		try {
			if(comp.compare(key, key)!=0){
				throw new IllegalArgumentException();
			}
			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException();
		}
	}
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		Check(key);

		Position<Entry<K, V>> walk=vals.last();
		while(walk!=null&&comp.compare(walk.getElement().getKey(), key)>0){
			walk=vals.before(walk);
		}
		if(walk!=null){
			return vals.addAfter(walk, new MyEntry(key, value)).getElement();
		}else{
			return vals.addFirst(new MyEntry(key, value)).getElement();
		}
	}
	
	@Override
	public Entry<K, V> min() {
		if(isEmpty()){
			return null;
		}else{
			return vals.first().getElement();
		}
	
	}

	@Override
	public Entry<K, V> removeMin() {
		if(isEmpty()){
			return null;

		}else{
			return vals.remove(vals.first());
		}
	}

}
