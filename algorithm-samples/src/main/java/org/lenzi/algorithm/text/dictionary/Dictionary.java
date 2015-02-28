package org.lenzi.algorithm.text.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class Dictionary {

	private HashMap<String,String> dict = new HashMap<String,String>();
	
	public Dictionary() {
		
	}
	
	/**
	 * @param f word file
	 */
	public void load(File f){
		if(f == null || !f.exists())
			return;
		System.out.println("Loading dictionary");
		try {
			String nextWord = null;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((nextWord = br.readLine()) != null){
				dict.put(nextWord, nextWord);
			}
		} catch (FileNotFoundException e) {
			System.err.print(f.getAbsolutePath() + " not found");
		} catch (IOException e){
			System.err.print("Error reading from " + f.getAbsolutePath());
		}
	}
	
	public boolean hasWord(String word){
		return dict.containsKey(word);
	}
	
	public int dictionarySize(){
		return dict.size();
	}
	
	public Collection<String> words(){
		return dict.values();
	}

}
