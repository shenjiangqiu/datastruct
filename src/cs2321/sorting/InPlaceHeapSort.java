package cs2321.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {


	public static void main(String[] args) {
		InPlaceHeapSort<Integer> sort=new InPlaceHeapSort<>();
		Integer[] a={1,5,9,7,2,1,3,6,15,15151,87,15,15,15,151,51,89,3565,22};
		sort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	public void sort(K[] array) {
		int length=array.length;
		for(int i=0;i<length;i++){
			upHeap(array,i+1);
		}
		for(int i=length-1;i>0;i--){
			K temp=array[0];
			array[0]=array[i];
			array[i]=temp;
			downHeap(array,i);
		}
	}
	private void upHeap(K[] array,int size){
		int i=size-1;
		while(i>0){

			int parent=(i-1)/2;
			if(array[i].compareTo(array[parent])>0){
				K temp=array[i];
				array[i]=array[parent];
				array[parent]=temp;
				i=parent;
			}else{
				break;
			}
		}
	}
	private void downHeap(K[] array,int size){
		int start=0;
		while(start*2+1<size){
			int max=start*2+1;
			if(start*2+2<size){
				if(array[start*2+1].compareTo(array[start*2+2])<0){
					max=start*2+2;
				}
			}
			if(array[start].compareTo(array[max])<0){
				K temp=array[start];
				array[start]=array[max];
				array[max]=temp;
				start=max;
			}else{
				break;
			}
		}
	}

}
