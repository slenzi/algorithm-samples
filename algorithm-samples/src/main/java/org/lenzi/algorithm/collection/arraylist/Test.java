package org.lenzi.algorithm.collection.arraylist;

import org.lenzi.algorithm.collection.IList;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		(new Test()).doWork();
	}
	
	public void doWork(){
		//doBitShiftTest();
		doOverflowTest();
		doCustomArrayTest();
		System.out.println("Done");
	}
	
	private void doCustomArrayTest() {
		
		IList<String> myArray = new MyArrayList<String>();
		
		//long maxFill = Integer.MAX_VALUE + 25L;
		//long maxFill = Integer.MAX_VALUE - (Integer.MAX_VALUE >> 1);
		long maxFill = 50;
		
		System.out.println("Fill array with " + maxFill + " elements.");
		for(long i=0; i<=maxFill; i++){
			//System.out.println("Adding " + i);
			myArray.add(i + "");
		}
		
		System.out.println("myArray size = " + myArray.size());
		
		System.out.println("Element at 20 = " + myArray.get(20));
		
		System.out.println("Deleting element at index 20 (twice)");
		myArray.remove(20);
		myArray.remove(20);
		
		System.out.println("Element at 20 = " + myArray.get(20));
		
		for(int i=0; i<myArray.size(); i++){
			System.out.println(i + " = " + myArray.get(i));
		}
		
	}

	public void doBitShiftTest(){
		
		int start = 1;
		
		for(int i = start; i<1000; i*=2){
			System.out.println(i + " in binary = " + getBinaryString(i));
			System.out.println(i + " >> 1 = " + (i >> 1));
			System.out.println((i >> 1) + " in binary = " + getBinaryString((i >> 1)));
			System.out.println();
		}
		
	}
	
	public void doOverflowTest(){
		
		int maxInt = Integer.MAX_VALUE;
		
		int a = Integer.MAX_VALUE - 100;
		int b = 75;
		int c = 150;
		
		System.out.println("Max int size = " + maxInt);
		System.out.println("Max int + 1 = " + (maxInt+1));
		
		System.out.println("sumMax(" + a + "," + b + ") = " + sumMax(a,b));
		System.out.println("sumMax(" + a + "," + c + ") = " + sumMax(a,c));
			
	}
	
	private int sumMax(int a, int b){
		if ( ((b > 0) ? a > (Integer.MAX_VALUE - b) : a < (Integer.MIN_VALUE - b)) ) {
			return Integer.MAX_VALUE;
		}
		return a + b;
	}
	
	static final int safeAdd(int left, int right) throws ArithmeticException {
		if ( ((right > 0) ? left > (Integer.MAX_VALUE - right) : left < (Integer.MIN_VALUE - right)) ) {
			throw new ArithmeticException("Integer overflow");
		}
		return left + right;
	}
	
	public String getBinaryString(int i){
		return String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
	}

}
