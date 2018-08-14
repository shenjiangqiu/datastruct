package cs2321.sorting;

import java.util.Arrays;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	public static void main(String[] args) {
		InPlaceSelectionSort<Integer> sort=new InPlaceSelectionSort<>();
		Integer[] a={1,5,9,7,2,1,3,6,15,15151,87,15,15,15,151,51,89,3565,22};
		sort.sort(a);
		System.out.println(Arrays.toString(a));
	}

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	
	public void sort(K[] array) {
		for(int i=0;i<array.length;i++){
			int min=i;
			for(int j=i;j<array.length;j++){
				if(array[j].compareTo(array[min	])<0){
					min=j;
				}
			}
			K temp=array[i];
			array[i]=array[min];
			array[min]=temp;
		}
	}

}
