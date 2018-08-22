package cs2321;

import java.util.Comparator;
import java.util.Iterator;

import net.datastructures.*;

public class HashMap<K, V> implements Map<K, V> {

	public static void main(String[] args) {
		HashMap<Integer,Integer> hashMap=new HashMap<>(20);
		for(int i=0;i<100;i++){
			hashMap.put(i,i);
		}
		System.out.println(hashMap.size());
		for(Entry<Integer,Integer> entry: hashMap.entrySet())
		{
			System.out.println("( key: "+entry.getKey()+" , value: "+ entry.getValue()+" )");
		}
		for(int j=20;j<50;j++){
			hashMap.remove(j);
		}
		System.out.println(hashMap.size());
		for(Entry<Integer,Integer> entry: hashMap.entrySet())
		{
			System.out.println("( key: "+entry.getKey()+" , value: "+ entry.getValue()+" )");
		}
	}

	int hashsize;
	Comparator<K> comparator;
	int size=0;
	UnorderedMap<K,V>[] myMap;
	private int index(K k){
		return k.hashCode()%hashsize;
	}

	/**
	 * Constructor that takes a hash size
	 * @param hashsize The number of buckets to initialize in the HashMap
	 * @param comparator - on construction, set comparator
	 */
	public HashMap(int hashsize, Comparator<K> comparator){
		this.hashsize=hashsize;
		this.comparator=comparator;
		myMap=(UnorderedMap<K, V>[]) new UnorderedMap[hashsize];
	}

	/**
	 * Constructor that takes a hash size
	 * @param hashsize The number of buckets to initialize in the HashMap
	 */
	public HashMap(int hashsize) {
		this(hashsize,new DefaultComparator<>());

	}
	
	/*
	 * @param comparator - the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		this.comparator=comparator;
	}

	public HashMap(){

		//       using the default hash size provided.
		this(100);

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public V get(K key) {
		UnorderedMap<K,V> unorderedMap=myMap[index(key)];
		if(unorderedMap==null){
			unorderedMap=myMap[index(key)]=new UnorderedMap<>();
		}
		return unorderedMap.get(key);

	}

	@Override
	public V put(K key, V value) {

		UnorderedMap<K, V> unorderedMap = myMap[index(key)];

		if (unorderedMap == null) {
			unorderedMap = myMap[index(key)] = new UnorderedMap<>();
		}
		int oldSize=unorderedMap.size();
		V ret= unorderedMap.put(key, value);
		int newSize=unorderedMap.size();

		size=size+(newSize-oldSize);
		return ret;
	}

	@Override
	public V remove(K key) {
		UnorderedMap<K, V> unorderedMap = myMap[index(key)];

		if (unorderedMap == null) {
			unorderedMap = myMap[index(key)] = new UnorderedMap<>();
		}
		int oldSize=unorderedMap.size();
		V ret=unorderedMap.remove(key);
		int newSize=unorderedMap.size();
		size=size+(newSize-oldSize);
		return ret;

	}


	@Override
	public Iterable<K> keySet() {
		ArrayList<K> buffer=new ArrayList<>();
		for(Entry<K,V> entry : entrySet()){
			buffer.add(buffer.size(),entry.getKey());
		}
		return buffer;
	}

	@Override
	public Iterable<V> values() {
		ArrayList<V> buffer=new ArrayList<>();
		for(Entry<K,V> entry : entrySet()){
			buffer.add(buffer.size(),entry.getValue());
		}
		return buffer;
	}


	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer=new ArrayList<>();

		for(int i=0;i<hashsize;i++){
			if(myMap[i]!=null){
				for(Entry<K,V> entry:myMap[i].entrySet()){
					buffer.add(buffer.size(),entry);
				}
			}
		}
		return  buffer;

	}



}
