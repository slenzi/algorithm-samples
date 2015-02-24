/**
 * 
 */
package org.lenzi.algorithm.text.levenshtein;

import java.util.Arrays;

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
	 * Compare with 256 possible characters
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int compare(String a, String b){
		return compare(a,b,ASCII);
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
		//printMatrix(matrix);
		return matrix[a.length() + 1][b.length() + 1];
		
	}
	
	/**
	 * Alternative edit distance algorithm.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int altGetEditDistance(String a, String b){
		
		int[][] ed = altGetEditDistanceMatrix(a, b);
		
		return ed[ed.length-1][ed[0].length - 1];
		
	}
	
	/**
	 * Get the full edit distance matrix
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[][] altGetEditDistanceMatrix(String a, String b){
		
		int[][] ed = new int[a.length() + 1][b.length() + 1];
		
		int i = 0, j = 0;
		for (i = 0; i < ed.length; i++){
			for (j = 0; j < ed[i].length; j++){
				if (i == 0){
					// fill first row with 1, 2, 3, ... N where is length of b.
					ed[i][j] = j;
				} else if (j == 0){
					// fill first column with 1, 2, 3, ....- N where is length of a.
					ed[i][j] = i;
				} else {
					// fill all other cells with 0
					ed[i][j] = 0;
				}
				if (i > 0 && j > 0){
					if (a.charAt(i - 1) == b.charAt(j - 1)){
						// they are equal. take the previous edit distance [i-1][j-1]
						ed[i][j] = ed[i - 1][j - 1];
					} else {
						// they are different. add 1 to the three edit distances [i-1][j-1] + 1, [1][j-1] + 1, [i-1][j] + 1
						// then use the minimum of the three.
						ed[i][j] = MathUtil.min(
									ed[i - 1][j] + 1,
									ed[i][j - 1] + 1,
									ed[i - 1][j - 1] + 1
								);
					}
				}
			}
		}
		return ed;
		
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
