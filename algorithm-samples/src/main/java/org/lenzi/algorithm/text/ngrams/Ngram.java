package org.lenzi.algorithm.text.ngrams;

import java.util.ArrayList;
import java.util.List;

public class Ngram {

	public Ngram() {
		
	}

	/**
	 * 
	 * @param n - ngram length
	 * @param str - input
	 * @param regex - regex to split the ngrams
	 * @return
	 */
    public static List<String> ngrams(int n, String str, String regex) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(regex);
        for (int i = 0; i < words.length - n + 1; i++){
            ngrams.add(concat(words, i, i+n));
        }
        return ngrams;
    }

    private static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++){
            sb.append((i > start ? " " : "") + words[i]);
        }
        return sb.toString();
    }	
	
}
