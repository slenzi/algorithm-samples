package org.lenzi.algorithm.text.dictionary;

import java.util.ArrayList;
import java.util.List;

import org.lenzi.algorithm.text.levenshtein.DamerauLevenshtein;
import org.lenzi.algorithm.text.levenshtein.Levenshtein;
import org.lenzi.algorithm.text.util.StringUtil;

public class SpellChecker {

	private Dictionary dict = null;
	
	public SpellChecker(Dictionary dict) {
		this.dict = dict;
	}
	
	/**
	 * Returns the word, or list of words, with the lowest edit distance
	 * from the provided word.
	 * 
	 * @param word
	 * @return
	 */
	public List<String> correct(final String word){
		
		if(dict.hasWord(word)){
			return new ArrayList<String>(){{
				add(word);
			}};
		}
		
		List<String> suggestions = new ArrayList<String>();
		int newDistance = 0;
		int prevDistance = Integer.MAX_VALUE;
		
		for(String s : dict.words()){
			
			// only check words that have a size difference of less than two characters.
			if(Math.abs(s.length() - word.length()) <= 2){
			
				//newDistance = Levenshtein.altGetEditDistance(word, s);
				newDistance = DamerauLevenshtein.compare(word, s, DamerauLevenshtein.ASCII_EXTENDED);
				
				if(newDistance < prevDistance){
					// lower edit distance. clear previous suggestions and start over
					prevDistance = newDistance;
					suggestions = new ArrayList<String>();
					suggestions.add(s);
				}else if(newDistance == prevDistance){
					// same edit distance. keep as possible suggestions
					suggestions.add(s);
				}
				
			}
		}		
		return suggestions;
	}
	
	private List<String> getEdits(String word){
		
		List<String> edits = new ArrayList<String>();
		
		List<String> deletes = StringUtil.permDeletes(word);
		//System.out.println("deletes:");
		//this.printList(deletes);
		edits.addAll(deletes);
		
		List<String> replaces = StringUtil.permReplaces(word);
		//System.out.println("replaces:");
		//this.printList(replaces);
		edits.addAll(replaces);
		
		List<String> transposes = StringUtil.permTranspose(word);
		//System.out.println("transposes:");
		//this.printList(transposes);
		edits.addAll(transposes);
		
		List<String> additions = StringUtil.permAdditions(word);
		//System.out.println("additions:");
		//this.printList(additions);
		edits.addAll(additions);
		
		return edits;
	}
	
	/**
	 * Print debugging
	 * 
	 * @param list
	 */
	private void printList(List<String> list){
		if(list == null)
			return;
		for(String s : list){
			System.out.println(s);
		}
	}
	
}
