/**
 * 
 */
package org.lenzi.algorithm.text.levenshtein;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.lenzi.algorithm.math.util.MathUtil;

/**
 * From wikipedia
 */
public abstract class DamerauLevenshtein {

	public final static int ASCII = 128;
	public final static int ASCII_EXTENDED = 256;
	
	public DamerauLevenshtein() {
		super();
	}

	/**
	 * Compare two strings
	 * 
	 * @param a
	 * @param b
	 * @param alphabetLength
	 * @return
	 */
	public static int compare(String a, String b, int alphabetLength) {
		
		int[][] matrix = null;
		
		try {
			
			matrix = getDistanceMatrix(a,b,alphabetLength);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error comparing words (" + a + ", " + b + "): " + e.getMessage());
		}
		
		return matrix[a.length() + 1][b.length() + 1];
		
	}
	
	public static int compare(String a, String b) {
		
		int[][] matrix = null;
		
		try {
			
			matrix = getDistanceMatrixMap(a,b);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error comparing words (" + a + ", " + b + "): " + e.getMessage());
		}
		
		return matrix[a.length() + 1][b.length() + 1];
		
	}	
	
	/**
	 * Get distance matrix for with know alphabet length for words a and b
	 * 
	 * @param a
	 * @param b
	 * @param alphabetLength
	 * @return
	 */
	public static int[][] getDistanceMatrix(String a, String b, int alphabetLength) {
		
		final int INFINITY = a.length() + b.length();
		
		int[][] matrix = new int[a.length() + 2][b.length() + 2];
		matrix[0][0] = INFINITY;
		
		for (int i = 0; i <= a.length(); i++) {
			matrix[i + 1][1] = i;
			matrix[i + 1][0] = INFINITY;
		}
		for (int j = 0; j <= b.length(); j++) {
			matrix[1][j + 1] = j;
			matrix[0][j + 1] = INFINITY;
		}
		
		int[] DA = new int[alphabetLength];
		Arrays.fill(DA, 0);
		
		for (int i = 1; i <= a.length(); i++) {
			int DB = 0;
			for (int j = 1; j <= b.length(); j++) {
				int i1 = DA[b.charAt(j - 1)];
				int j1 = DB;
				int d = ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1);
				if (d == 0){
					DB = j;
				}
				matrix[i + 1][j + 1] = MathUtil.min(
						matrix[i][j] + d,
						matrix[i + 1][j] + 1,
						matrix[i][j + 1] + 1,
						matrix[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
			}
			DA[a.charAt(i - 1)] = i;
		}

		return matrix;
		
	}
	
	/**
	 * Get distance matrix for with unknown alphabet length for words a and b.
	 * 
	 * A HasMap is used for tracking alphabet entries.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[][] getDistanceMatrixMap(String a, String b) {
		
		final int INFINITY = a.length() + b.length();
		
		int[][] matrix = new int[a.length() + 2][b.length() + 2];
		matrix[0][0] = INFINITY;
		
		for (int i = 0; i <= a.length(); i++) {
			matrix[i + 1][1] = i;
			matrix[i + 1][0] = INFINITY;
		}
		for (int j = 0; j <= b.length(); j++) {
			matrix[1][j + 1] = j;
			matrix[0][j + 1] = INFINITY;
		}
		
		Integer daInt = null;
		Map<Integer,Integer> DA = new HashMap<Integer,Integer>();
		
		for (int i = 1; i <= a.length(); i++) {
			int DB = 0;
			for (int j = 1; j <= b.length(); j++) {
				
				daInt = DA.get(b.charAt(j - 1));
				int i1 = ((daInt == null) ? 0 : daInt);
				int j1 = DB;
				
				int d = ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1);
				if (d == 0){
					DB = j;
				}
				matrix[i + 1][j + 1] = MathUtil.min(
						matrix[i][j] + d,
						matrix[i + 1][j] + 1,
						matrix[i][j + 1] + 1,
						matrix[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
			}
			DA.put((int)a.charAt(i - 1), i);
		}

		return matrix;
		
	}
	
	public static void printMatrix(int[][] m){
		int i = 0, j = 0;
		for (i = 0; i < m.length; i++){
			for(j = 0; j < m[i].length; j++){
				System.out.print(String.format("%1$-3d", m[i][j]));
			}
			System.out.println();
		}
		System.out.println();
	}	

}
