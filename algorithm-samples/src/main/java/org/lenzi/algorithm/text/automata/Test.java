package org.lenzi.algorithm.text.automata;

import java.util.Locale;

public class Test {

	public Test() {
	
	}
	
	public static void main(String[] args){
		new Test().doTest();
	}

	private void doTest() {
	
		doAutomataTest();
		
	}
	
	private void doAutomataTest(){
		
		FiniteAutomata automata = new FiniteAutomata();
		
		
		runAutomataTest(automata,"ababaca","abccbababacabbb");
			
		runAutomataTest(automata,"Fox","The quick brown fox jumped over the lazy dog. This made the fox tired.");
		
		runAutomataTest(automata,"Fox","The quick brown fox jumped over the lazy dog. This made the fox tired.");
		
		String pattern	= "GGCCTCCTGC";
		String sequence = "ACGGCCTCCTGCAAGATGCCATTGTCCCCCGGCCTCCTGCTGCTGCTGCTCTCCGGGGCCTCCTGCGGCCACGGCCACCGCTGCGGCCTCCTGCCCTGCC";
				
		runAutomataTest(automata, pattern, sequence);
		
	}
	
	private void runAutomataTest(FiniteAutomata automata, String pattern, String text){
		
		int[] indexes = automata.search(FiniteAutomata.ASCII, pattern, text, true);
		
		for(int i=0; i< indexes.length; i++){
			System.out.println("Found match at index: " + indexes[i]);
		}
		System.out.println();
	}
	
	private void toCharTest(){
		
		for(int i=0; i<256; i++){
			
			// %1$-5d = pad first value with 5 spaces to the right, as decimal integer
			// %2$-2c = pad second value with 2 spaces to the right, as unicode character
			System.out.println( String.format( Locale.US, "%1$-5d = %2$-2c", i, (char)i ) );

		}
		
		System.out.println();
		
		String input = "`~!@#$%^&*()-_=+{[}]|\\:;\"'<,>.?/0123456789abcdefghijklmnopqrstuvwxyz";
		char[] alpha = input.toCharArray();
		for(int i=0; i<alpha.length; i++){
			System.out.println(alpha[i] + " = " + (int)alpha[i]);
		}		
		
	}

}
