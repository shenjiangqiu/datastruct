package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	public static void main(String[] args) {
		ArrayList<Integer> mylist=new ArrayList<>();
		for(int i=0;i<100;i++){
			mylist.add(i,i);
		}
		for (Integer i : mylist) {
			System.out.println(i*2);
		}
		

	}
	private E[] vals;
	private int size;
	private int capacity;
	private void resize()
	{
		capacity*=2;
		E[] newvals=(E[])new Object[capacity];
		for(int i=0;i<size;i++){
			newvals[i]=vals[i];
		}
		vals=newvals;
	}
	private void check(int i,int n){
		if(i<0||i>n)
		{
			throw new IndexOutOfBoundsException("i is invalid,i= "+i+" and n is "+n);
		}
	}
	private class ArrayListIter implements Iterator<E>{
		private int next=0;

		@Override
		public boolean hasNext() {
			return next<ArrayList.this.size;
		}

		@Override
		public E next() {
			return ArrayList.this.vals[next++];
		}

	}
	public ArrayList() {
		size=0;
		capacity=16;
		vals=(E[])new Object[capacity];


	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean isEmpty() {

		return size==0;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {

		check(i, size-1);
		return vals[i];
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {

		check(i, size-1);
		E temp=vals[i];
		vals[i]=e;
		return temp;

	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {

		check(i, size);
		if(size>=capacity){
			resize();
		}
		for(int idx=size;idx>i;idx--){
			vals[idx]=vals[idx-1];
		}
		vals[i]=e;
		size++;
	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		check(i, size-1);
		E temp=vals[i];
		for(int idx=i;idx<size-2;idx++){
			vals[idx]=vals[idx+1];
		}
		size--;
		return temp;
	}

	@Override
	public Iterator<E> iterator() {

		return new ArrayListIter();
	}

}
