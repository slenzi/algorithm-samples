/**
 * 
 */
package org.lenzi.algorithm.collection.arraylist;

import java.util.Arrays;

import org.lenzi.algorithm.collection.IList;

/**
 * @author slenzi
 *
 */
public class MyArrayList<E> implements IList<E> {

	private static int INITIAL_SIZE = 10;
	
	private int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	private Object[] data;
	
	private int size;
	
	/**
	 * 
	 */
	public MyArrayList() {
		this(INITIAL_SIZE);
	}
	
	public MyArrayList(int initialSize){
		if(initialSize < 0){
			throw new IllegalArgumentException("Must initialize the arralist with a value >= 0.");
		}
		data = new Object[initialSize];
	}

	/* (non-Javadoc)
	 * @see org.lenzi.interviewcake.custom.arraylist.IList#add(java.lang.Object)
	 */
	public void add(E element) {
		ensureCapacity(size + 1);
		data[size] = element;
		size++;
	}
	
	/**
	 * Grow array up to max integer size.
	 * 
	 * @param minimumSize
	 */
	private void ensureCapacity(int minimumSize){
		if(minimumSize <= data.length){
			return;
		}
		// make data array bigger by some degree
		int oldCapacity = data.length;
		int newIncrease = oldCapacity >> 1; // add half the current size
		int newCapacity = sumMaxInt(oldCapacity,newIncrease);
		if(newCapacity > MAX_ARRAY_SIZE){
			newCapacity = MAX_ARRAY_SIZE;
		}
		System.out.println("New capacity = " + newCapacity);
		data = Arrays.copyOf(data, newCapacity);
	}
	
	/**
	 * Sum a and b. If the sum exceeds Integer.MAX_VALUE then Integer.MAX_VALUE is returned.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int sumMaxInt(int a, int b){
		if ( ((b > 0) ? a > (Integer.MAX_VALUE - b) : a < (Integer.MIN_VALUE - b)) ) {
			return Integer.MAX_VALUE;
		}
		return a + b;
	}

	/* (non-Javadoc)
	 * @see org.lenzi.interviewcake.custom.arraylist.IList#add(java.lang.Object[])
	 */
	public void add(E[] elements) {
		if(elements == null || elements.length == 0){
			return;
		}
		for(int i=0; i<elements.length; i++){
			add(elements[i]);
		}
	}

	/* (non-Javadoc)
	 * @see org.lenzi.interviewcake.custom.arraylist.IList#get(int)
	 */
	public E get(int index) {
		rangeCheck(index);
		return (E)data[index];
	}
	
	private void rangeCheck(int index){
		if(index > size){
			throw new IllegalArgumentException("Index out of bounds.");
		}
	}

	/* (non-Javadoc)
	 * @see org.lenzi.interviewcake.custom.arraylist.IList#remove(int)
	 */
	public E remove(int index) {
		
		rangeCheck(index);
		
		E value = (E)data[index];
		int itemsToMove = size - index - 1;
		if(itemsToMove > 0){
			System.arraycopy(data, index+1, data, index, itemsToMove);
		}
		data[size] = null;
		size--;
		return null;
	}

	/* (non-Javadoc)
	 * @see org.lenzi.interviewcake.custom.arraylist.IList#size()
	 */
	public int size() {
		return size;
	}

}
