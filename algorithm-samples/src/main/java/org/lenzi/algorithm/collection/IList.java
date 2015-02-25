package org.lenzi.algorithm.collection;

public interface IList<E> {

	public void add(E element);
	
	public void add(E[] elements);
	
	public E get(int index);
	
	public E remove(int index);
	
	public int size();
	
}
