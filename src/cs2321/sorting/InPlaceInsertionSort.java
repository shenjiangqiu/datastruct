package cs2321.sorting;

import java.util.Arrays;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	public static void main(String[] args) {
		InPlaceInsertionSort<Integer> sort=new InPlaceInsertionSort<>();
		Integer[] a={1,5,9,7,2,1,3,6,15,15151,87,15,15,15,151,51,89,3565,22};
		sort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	
	public void sort(K[] array) {
		for(int i=0;i<array.length;i++){
			insertTheLast(array,i+1);
		}
	}
	private void insertTheLast(K[] array,int size){
		K val=array[size-1];
		int walk=size-2;
		while (walk>=0){
			if(array[walk].compareTo(array[walk+1])>0){
				K temp=array[walk];
				array[walk]=array[walk+1];
				array[walk+1]=temp;
				walk--;
			}else {
				break;
			}
		}
	}

}
