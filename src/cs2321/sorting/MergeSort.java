package cs2321.sorting;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	public static void main(String[] args) {
		MergeSort<Integer> sort=new MergeSort<>();
		Integer[] a={1,5,9,7,2,1,3,6,15,15151,87,15,15,15,151,51,89,3565,22};
		sort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	private void merge(E[] array,int start,int end,int middle){
		E[] temp=Arrays.copyOfRange(array,start,end);
		int i=start;
		int j=middle;
		int k=start;
		while(i<middle&&j<end){
			if(temp[i-start].compareTo(temp[j-start])<0){
				array[k++]=temp[(i++)-start];
			}else{
				array[k++]=temp[(j++)-start];
			}
		}
		while (i<middle){
			array[k++]=temp[(i++)-start];
		}
		while (j<end){
			array[k++]=temp[(j++)-start];
		}
	}
	private void mySort(E[]array,int start,int end){
		if(end-start>1){
			int start1=start;
			int end1=(start+end)/2;
			int start2=end1;
			int end2=end;
			mySort(array,start1,end1);
			mySort(array,start2,end2);
			merge(array,start,end,start2);
		}
	}

	public void sort(E[] array) {

		try{
		mySort(array,0,array.length);
		}catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
}

