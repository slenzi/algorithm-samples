/**
 * 
 */
package org.lenzi.algorithm.text.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slenzi
 *
 */
public abstract class StringUtil {

	/**
	 * Remove consecutive characters from a string
	 * 
	 *  e.g.
	 *  aabb, 1 => ab
	 *  aaabba , 2 => aabba
	 *  aaabcccee, 1 => abce
	 * 
	 * @param input
	 * @param count
	 * @return
	 */
	public static String removeConsecutive(String input, int count){
		
		if(input == null)
			return null;
		if(input.equals("") || count == 0)
			return input;
		
		StringBuffer buf = new StringBuffer();
		char currChar, lastChar;
		int charCount = 1;
		
		currChar = input.charAt(0);
		buf.append(currChar);
		lastChar = currChar;
		
		for(int i=1; i<input.length(); i++){
			currChar = input.charAt(i);
			if(currChar == lastChar){
				// same char
				charCount++;
				if(charCount <= count){
					// keep
					buf.append(currChar);
				}
				// discard
			}else{
				// diff char. keep.
				buf.append(currChar);
				charCount = 1;
			}
			lastChar = currChar;
		}
		return buf.toString();
	}
	
	public static boolean hasConsecutive(String input, int maxCount){
		
		if(input == null)
			return false;
		if(input.length() < 2)
			return false;
		
		char currChar, lastChar;
		int charCount = 1;
		currChar = input.charAt(0);
		lastChar = currChar;		
		
		for(int i=1; i<input.length(); i++){
			currChar = input.charAt(i);
			if(currChar == lastChar){
				charCount++;
				if(charCount > maxCount){
					return true;
				}
			}else{
				charCount = 0;
			}
			lastChar = currChar;
		}
		return false;
	}
	
	/**
	 * Delete on char from the word, for every position, and
	 * return all variations.
	 * 
	 * @param word
	 * @return
	 */
	public static List<String> permDeletes(String word){
		List<String> edits = new ArrayList<String>();
		if(word == null || word.length() == 0){
			return edits;
		}else if(word.length() == 1){
			edits.add(word);
			return edits;
		}
		String edit = null;
		for(int i=0; i<word.length(); i++){
			edit = word.substring(0, i) + word.substring(i + 1);
			edits.add(edit);
		}
		return edits;
	}
	
	/**
	 * Swaps adjacent letters, return all variations
	 * 
	 * @param word
	 * @return
	 */
	public static List<String> permTranspose(String word){
		List<String> edits = new ArrayList<String>();
		if(word == null || word.length() == 0){
			return edits;
		}else if(word.length() == 1){
			edits.add(word);
			return edits;
		}
		String edit = null;
		for(int i=0; i<word.length()-1; i++){
			edit = word.substring(0,i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2);
			edits.add(edit);
		}
		return edits;
	}
	
	/**
	 * Replaces each letter in the word with all alpha values a-z,
	 * return all variations.
	 * 
	 * @param word
	 * @return
	 */
	public static List<String> permReplaces(String word){
		List<String> edits = new ArrayList<String>();
		if(word == null || word.length() == 0)
			return edits;
		String edit = null;
		for(int i=0; i< word.length(); i++){
			for(char c = 'a'; c <= 'z'; c++){
				edit = word.substring(0,  i) + c + word.substring(i + 1);
				edits.add(edit);
			}
		}
		return edits;
	}
	
	/**
	 * Adds all characters a-z to every position in the word, returning
	 * all variations.
	 * 
	 * @param word
	 * @return
	 */
	public static List<String> permAdditions(String word){
		List<String> edits = new ArrayList<String>();
		if(word == null || word.length() == 0)
			return edits;
		String edit = null;
		for(int i=0; i<= word.length(); i++){
			for(char c = 'a'; c <= 'z'; c++){
				edit = word.substring(0,  i) + c + word.substring(i);
				edits.add(edit);
			}
		}
		return edits;
	}

}
