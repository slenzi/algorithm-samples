/**
 * 
 */
package org.lenzi.algorithm.sort.quicksort;

/**
 * @author sal
 *
 */
public class SortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new SortTest()).doTest();
	}

	/**
	 * 
	 */
	public SortTest() {}	

	private void doTest() {
		
		Integer[] intItems = new Integer[]{6,9,2,4,8,7,1,5,0,3};
		QuickSortImpl<Integer> qsortInt = new QuickSortImpl<Integer>();
		qsortInt.sort(intItems);
		qsortInt.print(intItems);
		
		String[] stringItems = new String[]{"b","e","g","a","d","i","h","f","j","c"};
		QuickSortImpl<String> qsortString = new QuickSortImpl<String>();
		qsortString.sort(stringItems);
		qsortString.print(stringItems);		
		
	}	
	
}
