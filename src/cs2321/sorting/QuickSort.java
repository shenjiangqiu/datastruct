package cs2321.sorting;

import java.util.Arrays;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

	public static void main(String[] args) {
		QuickSort<Integer> sort=new QuickSort<>();
		Integer[] a={1,5,9,7,2,1,3,6,15,15151,87,15,15,15,151,51,89,3565,22};
		sort.sort(a);
		System.out.println(Arrays.toString(a));
	}
	private  void mySort(E[] array,int s,int e){

		int start=s;
		int end=e;
		if(end-start<1){
			return;
		}
		int now=start;
		while(start<end){
			while (array[end].compareTo(array[now])>=0 && end>start){
				end--;
			}

			if(array[end].compareTo(array[now])<0) {
				E temp = array[now];
				array[now] = array[end];
				array[end] = temp;
				now = end;
				start++;
			}
			while (array[start].compareTo(array[now])<=0&& start<end){
				start++;

			}
			if(array[start].compareTo(array[now])>0) {
				E temp = array[now];
				array[now] = array[start];
				array[start] = temp;
				now = start;
				end--;
			}
		}
		mySort(array,s,now-1);
		mySort(array,now+1,e);
	}
	public void sort(E[] array) {
		int start=0;
		int end=array.length-1;
		mySort(array,start,end);
	}
}
