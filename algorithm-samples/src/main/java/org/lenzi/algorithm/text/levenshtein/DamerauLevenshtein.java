/**
 * 
 */
package org.lenzi.algorithm.text.levenshtein;

import java.util.Arrays;

import org.lenzi.algorithm.math.util.MathUtil;

/**
 * @author slenzi
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
		return matrix[a.length() + 1][b.length() + 1];
		
	}

}
