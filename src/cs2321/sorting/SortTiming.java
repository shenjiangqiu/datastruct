package cs2321.sorting;

import cs2321.ArrayList;

import java.util.Random;

/**
 * A test driver for Sorts.
 * 
 * Course: CS2321 Section ALL
 * Assignment: #4
 * @author Chris Brown (cdbrown@mtu.edu)
 */

public class SortTiming {

	public static void main(String [] args){
		
		//#Examples of using testSort
		Random rand=new Random();
		Integer[] testee = new Integer[100000];
		for(int i=0;i<100000;i++){
			testee[i]=rand.nextInt();
		}
		ArrayList<Sorter<Integer>> sorters=new ArrayList<>();
		int navi=0;
		sorters.add(navi++, new QuickSort<>());
		sorters.add(navi++ ,new MergeSort<>());
		sorters.add(navi++ ,new InPlaceHeapSort<>());
		sorters.add(navi++ ,new HeapSort<>());
		sorters.add(navi++ ,new InPlaceInsertionSort<>());
		sorters.add(navi++ ,new InPlaceSelectionSort<>());
		sorters.add(navi++ ,new InsertionSort<>());
		sorters.add(navi++, new SelectionSort<>());
		for(int i=0;i<8;i++){
			double start=System.nanoTime();
			sorters.get(i).sort(testee.clone());
			double end=System.nanoTime();
			System.out.print((end-start)/1000000);
			System.out.print(" ");
		}
		System.out.println();


		
	}

	/**
	 * Algorithm: testSort
	 * @param arr - an array of Integers to use for empirical measurement of a sort
	 * @param sortClass - the Class representing the sorting algorithm to be run
	 * @param iterations - the number of times the sort is repeated
	 * @return average time taken for a single execution of a sort (in nanoseconds)
	 * 
	 * A copy (clone) of the array is made to test over, so that the original may be reused.
	 */
	public static double testSort(Integer[] arr, Sorter<Integer> sortClass){
		long startTime = 0, endTime = 0;
		int samples = 0;

		System.gc();
		startTime = System.nanoTime();
		//#repeated measurements (no less than .5 seconds worth of repeats)
		do{
			//create a copy of the array for each test case
			Integer[] testCase = arr.clone();
			//the sorting algorithm, based on the Sorter Class
			sortClass.sort(testCase);
			
			samples++;
			endTime = System.nanoTime();
		}while(endTime - startTime < 500000000);
				
		return (double)(endTime - startTime) / samples;
	}
	
}
