/**
 * 
 */
package org.lenzi.algorithm.text.ngrams;

/**
 * @author sal
 *
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
		new Test().doTest();
	}
	
	public void doTest(){
		
		int ngramSize = 4;
		String input = "The quick   brown fox jumped  over the   lazy dog.";
		String split = " "; 
		
		input = input.replaceAll("\\s+", split);
		
		for (int n = 1; n <= ngramSize; n++) {
            for (String ngram : Ngram.ngrams(n, input, split)){
                System.out.println(ngram);
            }
            System.out.println();
        }		
		
	}

}
