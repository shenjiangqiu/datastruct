package cs2321;
import java.util.Iterator;
import java.util.NoSuchElementException;


import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> mDoublyLinkedList=new DoublyLinkedList<>();
		for(int i=0;i<100;i++){
			mDoublyLinkedList.addLast(i);
		}
		for(Position<Integer> i:mDoublyLinkedList.positions() ){
			System.out.println(i.getElement());
		}
		System.out.println(mDoublyLinkedList.size());
		Position<Integer> p=mDoublyLinkedList.first();
		mDoublyLinkedList.remove(p);
		p=mDoublyLinkedList.last();
		mDoublyLinkedList.remove(p);
		System.out.println(mDoublyLinkedList.size());
		System.out.println("dll end");

	}
	private static class Node<E> implements Position<E>{
		private E val;
		private Node<E> pre;
		private Node<E> next;
		public Node(E e,Node<E> p,Node<E> n){
			val=e;
			pre=p;
			next=n;
		}
		@Override
		public E getElement() throws IllegalStateException {
			if(next==null){
				throw new IllegalStateException("invalid position");
			}
			return val;
		}
		public Node<E> getPre(){
			return pre;

		}
		public Node<E> getNext(){
			return next;
		}
		public void setPre(Node<E> p){
			pre=p;
		}
		public void setNext(Node<E> n){
			next=n;
		}
		public void setVal(E e){
			val=e;
		}


	}
	private Node<E> check(Position<E> p){
		if(!(p instanceof DoublyLinkedList.Node)){
			throw new IllegalArgumentException("p is not Node");
		}
		Node<E> node=(Node<E>)p;
		if(node.next==null){
			throw new IllegalArgumentException("p is not a valid");
		}
		return node;
	}
	private Node<E> head;
	private Node<E> tail;
	int size;
	private Position<E> getPosition(Node<E> node)
	{
		if(node==head||node==tail){
			return null;
		}else{
			return (Position<E>)(node);
		}
	}
	public DoublyLinkedList() {
		head=new Node<E>(null,null,null);
		tail=new Node<E>(null,head,null);
		head.setNext(tail);
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
	public Position<E> first() {
		Node<E> node=head.getNext();
		return getPosition(node);
	}

	@Override
	public Position<E> last() {
		Node<E> node=tail.getPre();
		return getPosition(node);
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		check(p);
		Node<E> node=(Node<E>)p;

		return getPosition(node.pre);
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		check(p);
		Node<E> node=(Node<E>)p;

		return getPosition(node.next);
	}
	private Position<E> add(E e,Node<E> before,Node<E> next){
		Node<E> newnode=new Node<>(e,before,next);
		before.setNext(newnode);
		next.setPre(newnode);
		size++;
		return newnode;
	}
	@Override
	public Position<E> addFirst(E e) {
		return add(e,head,head.getNext());
		 
	}

	@Override
	public Position<E> addLast(E e) {
		return add(e,tail.getPre(),tail);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node=check(p);
		return add(e,node.getPre(),node);
	
	
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node=check(p);
		
		return add(e,node,node.getNext());

	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node=check(p);
		E ret=node.getElement();
		node.setVal(e);
		return ret;
		
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node=check(p);
		E e=node.getElement();
		node.getPre().setNext(node.getNext());
		node.getNext().setPre(node.getPre());
		node.setNext(null);
		node.setPre(null);
		
		node.setVal(null);
		size--;
		return e;
	}
	 private class DoublyLinkedListIterator implements Iterator<E>{
		Position<E> tnext;
		DoublyLinkedListIterator(){
			tnext=first();
		}
		@Override
		public boolean hasNext() {
			return tnext!=null;
		}

		@Override
		public E next() {
			if(tnext==null){
				throw new NoSuchElementException();
			}
			E ret=tnext.getElement();
			tnext=DoublyLinkedList.this.after(tnext);
			return ret;
		}
		

	}
	@Override
	public Iterator<E> iterator() {
		
		return new DoublyLinkedListIterator();
	}
	private class PositionIterator implements Iterator<Position<E>>{
		Position<E> tnext=first();
		@Override
		public boolean hasNext() {
			return tnext!=null;
		}

		@Override
		public Position<E> next() {
			if(tnext==null){
				throw new NoSuchElementException();
			}
			Position<E> ret=tnext;
			tnext=after(tnext);
			return ret;
			
		}

	

	}
	private class PositionIterable implements Iterable<Position<E>>{

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}

	}
	@Override
	public Iterable<Position<E>> positions() {
		
		return new PositionIterable();
	}

}
