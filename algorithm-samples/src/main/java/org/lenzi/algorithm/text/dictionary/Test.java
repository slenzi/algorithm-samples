package org.lenzi.algorithm.text.dictionary;

import java.io.File;

public class Test {

	public Test() {
	
	}

	public static void main(String[] args) {
		
		new Test().doTest();

	}
	
	public void doTest(){
		
		//File f = new File("short_alpha.txt");
		//File f = new File("unsorted_words.txt");
		File f = new File("sorted_words.txt");
		
		Dictionary dict = new Dictionary();
		dict.load(f);
		System.out.println("Dictionary size = " + dict.dictionarySize());
		
		SpellChecker checker = new SpellChecker(dict);
		
		String[] misspelled = new String[]{
				"kinsthesilogists", // kinesthesiologists
				"asspect",			// aspect
				"multparticpant",	// multiparticipant
				"aquitane",			// aquitaine
				"veitnamese",		// vietnamese
				"festivties",		// festivities
				"incredditable",	// increditable
				"nongovrnmentl",	// nongovernmental
				"presciptibillity"	// prescriptibility
		};
		
		for(String s : misspelled){
			System.out.println("Suggestion = " + checker.correct(s));
		}		
		
	}

}
