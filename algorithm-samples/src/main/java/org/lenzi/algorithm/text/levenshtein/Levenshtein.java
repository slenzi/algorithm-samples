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
}