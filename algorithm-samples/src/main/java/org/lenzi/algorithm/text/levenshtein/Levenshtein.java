/**
 * 
 */
package org.lenzi.algorithm.text.levenshtein;

import org.lenzi.algorithm.math.util.MathUtil;

/**
 * Levenshtein distance is a metric for measuring the amount of difference between two sequences
 * 
 * From wikipedia
 */
public abstract class Levenshtein {
	
	public Levenshtein() {
		super();
	}

	/**
	 * Computes the difference of s1 and s2 using the Levenshtein distance metric.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static double compare(final String s1, final String s2) {
		
		double retval = 0.0;
		
		final int n = s1.length();
		final int m = s2.length();
		
		if (0 == n) {
			retval = m;
		} else if (0 == m) {
			retval = n;
		} else {
			retval = 1.0 - (compare(s1, n, s2, m) / (Math.max(n, m)));
		}
		
		return retval;
	}

	/**
	 * 
	 * @param s1
	 * @param n
	 * @param s2
	 * @param m
	 * @return
	 */
	private static double compare(final String s1, final int n, final String s2, final int m) {
		
		int matrix[][] = new int[n + 1][m + 1];
		
		for (int i = 0; i <= n; i++) {
			matrix[i][0] = i;
		}
		for (int i = 0; i <= m; i++) {
			matrix[0][i] = i;
		}
		for (int i = 1; i <= n; i++) {
			int s1i = s1.codePointAt(i - 1);
			for (int j = 1; j <= m; j++) {
				int s2j = s2.codePointAt(j - 1);
				final int cost = s1i == s2j ? 0 : 1;
				matrix[i][j] = MathUtil.min(
						matrix[i - 1][j] + 1, 
						matrix[i][j - 1] + 1, 
						matrix[i - 1][j - 1] + cost);
			}
		}
		
		return matrix[n][m];
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
									ed[i - 1][j    ] + 1,
									ed[i    ][j - 1] + 1,
									ed[i - 1][j - 1] + 1
								);
					}
				}
			}
		}
		return ed;
		
	}
	
}