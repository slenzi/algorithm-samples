/**
 * 
 */
package org.lenzi.algorithm.text.util;

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

}
