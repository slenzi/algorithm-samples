/**
 * 
 */
package org.lenzi.algorithm.text.levenshtein;

import org.lenzi.algorithm.math.util.MathUtil;

/**
 * @author slenzi
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new Test()).doAllTest();

	}
	
	public void doAllTest(){
		doFactorialTests();
		doLevenshteinTests();
	}
	
	public void doFactorialTests(){
		long x = 1000;
		System.out.println("Factorial of " + x + " = " + MathUtil.factorial(x));
		
	}
	
	public void doLevenshteinTests(){
		
		//
		// Compare strings
		//
		String[] a1 = new String[]{
			"This is a test",
			"The quick brown fox jumped over the lazy dog",
			"900 Commonwealth Ave, Boston MA, 02215",
			"87 Electric Ave, Apt. 2, Somerville MA, 02144",
			"Metro Rock, 69 Norman Street #9 Everett, MA 02149",
			"How now brown cow",
			"Frontier Science and Technology, 900 Commonwealth Ave, Boston MA 02215",
			"A seashell or sea shell, also known simply as a shell, is a hard, protective outer layer created by an animal "
					+ "that lives in the sea. The shell is part of the body of the animal. Empty seashells are often found washed up "
					+ "on beaches by beachcombers.",
			"Sally saw seashells at the seashore.",
			"123456789"
		};
		
		//
		// Slight variations of a1
		//
		String[] a2 = new String[]{
			// extra space after 'a', and a period at the end
			"This is a  test.",
			// "quickest" instead of "quick", addition of "red"
			"The quickest brown fox jumped over the lazy red dog",
			// "avenue" instead of "ave", one less comma
			"900 Commonwealth Avenue, Boston MA 02215",
			// "#2" instead of apt.
			"87 Electric Ave, #2, Somerville MA, 02144",
			// perfect match...
			"Metro Rock, 69 Norman Street #9 Everett, MA 02149",
			// everything is different...
			"Sally saw seashells",
			"Frontier Science, 900 Commonwealth Ave, Boston, MA 02215",
			"A seashell or C shell, also known simply as a shell, is a wicked hard, protective outer layer created by an animal "
					+ "that lives in the ocean. The shell is part of the body of the beast. Empty seashells are often found washed up "
					+ "on beaches by beach bumbs.",
			"Sally saw seashells at the seashore.",
			"1  2  3  4  5  6  7  8  9" // two space between each character
		};
	
		
		
		System.out.println("Optimal string alignment distance matrix...\n");
		int[][] om = OptimalStringAlignmentDistance.getDistanceMatrix("pantera","aorta");
		MathUtil.printMatrix( om );
		System.out.println("Edit distance = " + om[om.length-1][om[0].length - 1]);
		
		System.out.println();
		
		System.out.println("Levenshtein distance matrix...\n");
		int[][] lm = Levenshtein.altGetEditDistanceMatrix("pantera","aorta");
		MathUtil.printMatrix( lm );
		System.out.println("Edit distance = " + lm[lm.length-1][lm[0].length - 1]);
		
		System.out.println();
		
		System.out.println("Damerau Levenshtein distance matrix...\n");
		int[][] dlm = DamerauLevenshtein.getDistanceMatrix("pantera","aorta",DamerauLevenshtein.ASCII);
		DamerauLevenshtein.printMatrix( dlm );
		System.out.println("Edit distance = " + dlm[dlm.length-1][dlm[0].length - 1]);
		
		
	}

}
