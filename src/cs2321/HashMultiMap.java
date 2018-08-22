package cs2321;

import net.datastructures.List;
import net.datastructures.Map;
import net.datastructures.Entry;

public class HashMultiMap <K, V> {
	public static void main(String[] args) {
		HashMultiMap<Integer,Integer> hashMap=new HashMultiMap<>();
		for(int i=0;i<100;i++){
			hashMap.put(1,i%20);
		}
		System.out.println(hashMap.size());
		for(Integer key:hashMap.keySet()){
			for(Integer v:hashMap.get(key)){
				System.out.println("( key: "+ key+" , value: "+v+" )");
			}
		}
		for(int i=0;i<10;i++) {
			hashMap.remove(1, i);
		}
		System.out.println(hashMap.size());
		for(Integer key:hashMap.keySet()){
			for(Integer v:hashMap.get(key)){
				System.out.println("( key: "+ key+" , value: "+v+" )");
			}
		}
	}
	Map<K, List<V>> map=new HashMap<>(100);
	int total=0;
	public int size() {
		return total;
	}


	public boolean isEmpty() {
		return total==0;
	}

	/* 
	 * Returns a collection of all values associated with key k in the map.
	 * Don't return null, but return a collection that hold no data.     
	 */
	public Iterable<V> get(K key) {
		return map.get(key)==null?new ArrayList<>():map.get(key);
	}

	
	/*
	 * Adds a new entry to the multimap associating key k with value v, without overwriting any existing mappings for key k.
	 */
	public void put(K key, V value) {
		List<V> arrayList=map.get(key);
		if(arrayList==null){
			arrayList=new ArrayList<>();
			map.put(key,arrayList);


		}
		arrayList.add(arrayList.size(),value);
		total++;


	}


	/*
	 * Removes an entry mapping key k to value v from the multimap. 
	 */
	public boolean remove(K key, V value) {
		List<V> list=map.get(key);
		if(list==null){
			return false;
		}else{
			boolean removed=false;
			for(int i=0;i<list.size();i++){
				if(list.get(i)==value){
					list.remove(i);
					total--;
					removed=true;
					i--;
				}
			}
			if(list.size()==0){
				map.remove(key);
			}
			return removed;
		}

	}

	/*
	 * Removes all entries having key equal to k from the multimap. 
	 */
	public Iterable<V> removeAll(K key) {
		if(map.get(key)==null){
			return new ArrayList<>();
		}else {
			List<V> ret= map.remove(key);
			total-=ret.size();
			return  ret;
		}
	}

	/*
	 * Returns a non-duplicative collection of keys in the multimap. 
	 */
	public Iterable<K> keySet() {
		return map.keySet();
	}

	/* 
	 * Returns a collection of values for all entries int the multimap 
	 */
	public Iterable<V> values() {
		List<V> buffer=new ArrayList<>();
		for(List<V> list:map.values()) {
			for (V v : list) {
				buffer.add(buffer.size(),v );
			}
		}
		return buffer;
	}
	

}
