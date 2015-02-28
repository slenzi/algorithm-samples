package org.lenzi.algorithm.text.dictionary;

import java.io.File;

public class Test {

	public Test() {
	
	}

	public static void main(String[] args) {
		
		new Test().doTest();

	}
	
	public void doTest(){
		
		File f = new File("short_alpha.txt");
		Dictionary dict = new Dictionary();
		dict.load(f);
		System.out.println("Dictionary size = " + dict.dictionarySize());
		
		SpellChecker checker = new SpellChecker(dict);
		
		String word = "asspect"; // aspect
		
		String suggestion = checker.correct(word);
		
		System.out.println("Suggestion = " + suggestion);
		
		
	}

}
