package org.lenzi.algorithm.text.dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lenzi.algorithm.text.levenshtein.DamerauLevenshtein;
import org.lenzi.algorithm.text.levenshtein.Levenshtein;
import org.lenzi.algorithm.text.util.StringUtil;

public class SpellChecker {

	private Dictionary dict = null;
	
	private int MAX_EDIT_DISTANCE 	= 2;
	private int MAX_LENGTH_DIFF		= 2;
	
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
		
		// generate n <= 2 edit distance permutations, and filter unknown
		Set<String> edits = getEdits(word);
		edits = getEdits(edits);
		edits = filterUnknown(edits);
		
		if(edits != null && edits.size() > 0){
			
			for(String s : edits){
				newDistance = DamerauLevenshtein.compare(word, s);
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
			
		} else {
		
			// brute force check against all words in dictionary. very slow.
			for(String s : dict.words()){
				// only check words that have a size difference of less than two characters.
				if(Math.abs(s.length() - word.length()) <= MAX_LENGTH_DIFF){
					newDistance = DamerauLevenshtein.compare(word, s);
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
			
		}
		
		return suggestions;
	}
	
	/**
	 * Filters all words from the list that are not in the dictionary.
	 * 
	 * @param words
	 * @return
	 */
	public Set<String> filterUnknown(Set<String> words){
		if(words == null){
			return null;
		}
		if(words.size() == 0){
			return words;
		}
		Set<String> knownWords = new HashSet<String>();
		for(String word : words){
			if(dict.hasWord(word)){
				knownWords.add(word);
			}
		}
		return knownWords;
	}
	
	/**
	 * Get all edit variations for a word.
	 * 
	 * + all permutations having one character deleted.
	 * + all permutations having one character replaced with another [a-z].
	 * + all permutations having one character added [a-z]
	 * + all permutations by transposing neighboring characters (n1,n2) => (n2,n1)
	 * 
	 * @param word
	 * @return
	 */
	public Set<String> getEdits(final String word){
		Set<String> edits = new HashSet<String>(){{
			addAll(StringUtil.permDeletes(word));
			addAll(StringUtil.permReplaces(word));
			addAll(StringUtil.permTranspose(word));
			addAll(StringUtil.permAdditions(word));
		}};
		return edits;
	}
	
	/**
	 * Get all edit variations for all words in the list.
	 * 
	 * + all permutations having one character deleted.
	 * + all permutations having one character replaced with another [a-z].
	 * + all permutations having one character added [a-z]
	 * + all permutations by transposing neighboring characters (n1,n2) => (n2,n1)
	 * 
	 * @param word
	 * @return
	 */	
	public Set<String> getEdits(final Set<String> words){
		Set<String> edits = new HashSet<String>();
		for(String word : words){
			edits.addAll(StringUtil.permDeletes(word));
			edits.addAll(StringUtil.permReplaces(word));
			edits.addAll(StringUtil.permTranspose(word));
			edits.addAll(StringUtil.permAdditions(word));
		}
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
