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
	
	public String correct(String word){
		
		if(dict.hasWord(word)){
			return word;
		}
		
		String suggestion = word;
		int newDistance = 0;
		int prevDistance = Integer.MAX_VALUE;
		
		for(String s : dict.words()){
			newDistance = Levenshtein.altGetEditDistance(word, s);
			if(newDistance < prevDistance){
				prevDistance = newDistance;
				suggestion = s;
			}
		}
		
		List<String> edits = getEdits(word);
		
		return suggestion;
		
	}
	
	private List<String> getEdits(String word){
		
		List<String> edits = new ArrayList<String>();
		
		List<String> deletes = StringUtil.permDeletes(word);
		System.out.println("deletes:");
		this.printList(deletes);
		edits.addAll(deletes);
		
		List<String> replaces = StringUtil.permReplaces(word);
		System.out.println("replaces:");
		this.printList(replaces);
		edits.addAll(replaces);
		
		List<String> transposes = StringUtil.permTranspose(word);
		System.out.println("transposes:");
		this.printList(transposes);
		edits.addAll(transposes);
		
		List<String> additions = StringUtil.permAdditions(word);
		System.out.println("additions:");
		this.printList(additions);
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
