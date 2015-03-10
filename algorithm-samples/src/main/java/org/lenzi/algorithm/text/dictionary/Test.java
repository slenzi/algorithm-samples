package org.lenzi.algorithm.text.dictionary;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Test {

	private SpellChecker checker;

	public static void main(String[] args) {
		
		new Test().doTests();

	}
	
	public Test() {
		initChecker();
	}	
	
	private void initChecker(){
		
		//File f = new File("short_alpha.txt");
		//File f = new File("unsorted_words.txt");
		File f = new File("dictionary.txt");
		
		Dictionary dict = new Dictionary();
		dict.load(f);
		System.out.println("Dictionary size = " + dict.dictionarySize());
		
		checker = new SpellChecker(dict);		
		
	}
	
	public void doTests(){
			
		//doEditsTest();
		
		doSpellingTests();
		
	}
	
	public void doEditsTest(){
		
		Set<String> n1Edits = checker.getEdits("multparticpant"); // multiparticipant
		System.out.println("n-1 edits => " + n1Edits.size());
		for(String s : n1Edits){
			System.out.println(s);
		}
		
		Set<String> n2Edits = checker.getEdits(n1Edits);
		System.out.println("n-2 edits => " + n2Edits.size());
		for(String s : n2Edits){
			System.out.println(s);
		}		
		
	}
	
	public void doSpellingTests(){

		String[] misspelled = new String[]{
				"hous",				// house
				"house",			// house
				"stp",				// stop
				"stopp",			// stop
				"stoppp",			// stop
				"fu",				// fun
				"can ned",			// canned
				"acamodation",		// accommodation
				"acomodation",		// accommodation
				"kinsthesilogists", // kinesthesiologists
				"asspect",			// aspect
				"multparticpant",	// multiparticipant
				"aquitane",			// aquitaine
				"veitnamese",		// vietnamese
				"festivties",		// festivities
				"incredditable",	// increditable
				"nongovrnmentl",	// nongovernmental
				"presciptibillity",	// prescriptibility
				"salloon",			// saloon
				"trakeostomy",		// tracheostomy
				"cermugeonnnnn",	// curmudgeon
				"épalière",			// épaulière
				
				"supercalifragalisticexpaladocious" // supercalifragilisticexpialidocious
		};
	
		/*
		String[] misspelled = new String[]{
				"multparticpant",	// multiparticipant
		};
		*/
		
		long startTime, endTime, totalStartTime, totalEndTime = 0;
		
		totalStartTime = Calendar.getInstance().getTimeInMillis();
		
		List<String> suggestions = null;
		for(String s : misspelled){
			startTime = Calendar.getInstance().getTimeInMillis();
			suggestions = checker.correct(s);
			endTime = Calendar.getInstance().getTimeInMillis();
			System.out.println(  String.format("Suggestion for %1$-20s = %2$s (%3$s ms)", s, suggestions, (endTime - startTime)) );
		}
		
		totalEndTime = Calendar.getInstance().getTimeInMillis();
		
		System.out.println("Time in seconds = " + ((totalEndTime - totalStartTime) / 1000));
		
	}

}
