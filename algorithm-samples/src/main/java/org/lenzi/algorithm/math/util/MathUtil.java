/**
 * 
 */
package org.lenzi.algorithm.math.util;

import java.math.BigInteger;

/**
 * @author slenzi
 */
public abstract class MathUtil {

	/**
	 * Returns the minimum of a series of integers.
	 * 
	 * @param nums 1 or more integers
	 * @return The smallest of all integers passed to the method.
	 */
	public static int min(int... nums) {
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			min = Math.min(min, num);
		}
		return min;
	}
	
	/**
	 * Returns the maximum of a series of integers.
	 * 
	 * @param nums 1 or more integers.
	 * @return The largest of all integers passed to the method.
	 */
	public static int max(int... nums) {
		int max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}
	
	/**
	 * Factorial with BigInteger
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger factorial(long l){
		BigInteger fact = BigInteger.ONE;
		for(long index = 2; index<=l; index++){
			fact = fact.multiply(BigInteger.valueOf(index));
		}
		return fact;
	}
	
	/**
	 * Recursive implementation of factorial.
	 * 
	 * @param n
	 * @return
	 */	
	public static long recursiveFactorial(long n){
		if(n == 0 || n == 1){
			return 1;
		}else{
			return n * recursiveFactorial(n-1);
		}
	}
	
	/**
	 * Recursive implementation of fibonacci.
	 * 
	 * @param f
	 * @return
	 */
	public static int fibRecursive(int f){
		if(f == 0){
			return 0;
		}else if(f == 1 || f == 2){
			return 1;
		}
		return fibRecursive(f-1) + fibRecursive(f-2);
	}
	
	/**
	 * Iterative implementation of fibonacci.
	 * 
	 * @param f
	 * @return
	 */
	public static int fibLoop(int f){
		if(f == 0)
			return 0;
		if(f == 1 || f == 2)
			return 1;
		int fib = 0, fibMinus1 = 1, fibMinus2 = 1;
		for(int i=2; i<f; i++){
			fib = fibMinus1 + fibMinus2;
			fibMinus2 = fibMinus1;
			fibMinus1 = fib;
		}
		return fib;
	}	

}