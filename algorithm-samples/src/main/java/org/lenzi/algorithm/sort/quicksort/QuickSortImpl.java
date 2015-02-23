/**
 * 
 */
package org.lenzi.algorithm.sort.quicksort;

/**
 * @author sal
 *
 */
public class QuickSortImpl<T extends Comparable<T>> {
	
	public QuickSortImpl() {}
	
	/**
	 * Sort and array of comparable items.
	 * 
	 * @param array
	 */
	public void sort(T[] array){
		array = sort(array,0,array.length-1);
	}
	
	private T[] sort(T[] array, int low, int hi){
		System.out.println("sort");
		print(array);
		if(hi > low){
			int partitionPivotIndex = (int)(Math.random()*(hi-low) + low);
			int newPivotIndex = partition(array, low, hi, partitionPivotIndex);
			sort(array, low, newPivotIndex-1);
			sort(array, newPivotIndex+1, hi);
		}
		return array;
	}
	
	private int partition(T[] array, int lo, int hi, int pivotIndex){
		System.out.println("partition: pivot = " + pivotIndex);		
		T pivotValue = array[pivotIndex];
		swap(array, pivotIndex, hi); //send to the back
		int index = lo;
		for (int i = lo; i < hi; i++){
			if((array[i]).compareTo(pivotValue) <= 0){
				System.out.println("i = " + i + ", index = " + index + ", swap " + array[i] + " and " + array[index]);
				swap(array, i, index);
				index++;
			}
		}
		swap(array, hi, index);
		print(array);
		return index;		
	}
	
	/**
	 * Swaps elements array[i] and array[j].
	 * 
	 * @param array - An array of elements
	 * @param i - The element at position i to swap with element at position j.
	 * @param j - The element at position j to swap with element at position i.
	 */
	private void swap(T[] array, int i, int j){
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public void print(T[] array){
		for(T t : array){
			System.out.print(t + " ");
		}
		System.out.println();
	}
		
}