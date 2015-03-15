package org.lenzi.algorithm.xml.wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.lenzi.algorithm.text.util.StringUtil;

public class WikiFreqClean {

	public WikiFreqClean() {

	}

	public static void main(String[] args) {
		
		WikiFreqClean c = new WikiFreqClean();
		
		File wordFreq = new File("F:/Downloads/wiki dump/enwiki-20150304-pages-articles_2.xml/wiki_word_freq_4.txt");
		
		File cleanOutput = new File(wordFreq.getParentFile().getAbsolutePath() + File.separator + "wiki_cleaned.txt");
		
		try {
			c.doClean(wordFreq, cleanOutput);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public void doClean(File input, File output) throws IOException {
		
		System.out.println("Cleaning " + input.getAbsolutePath());
		System.out.println("Saving results to " + output.getAbsolutePath());
		
		// Construct BufferedReader from FileReader
		BufferedReader br = new BufferedReader(new FileReader(input));
	 
		String line = null;
		String[] parts = null;
		String word = null;
		String sfreq = null;
		long freq = 0;
		
		long minFreq = 10;
		
		Map<String,Long> wordFreq = new TreeMap<String,Long>();
		
		while ((line = br.readLine()) != null) {
			parts = line.split(",");
			if(parts != null && parts.length == 2){
				
				word = parts[0].trim();
				sfreq = parts[1].trim();
				freq = Long.parseLong(sfreq);
				
				if(word.length() == 1){
					if(word.equals("a") || word.equals("i")){
						// no other 1 letter words
						wordFreq.put(word, freq);
					}
				}else{
					if(!StringUtil.hasConsecutive(word,2) && freq > minFreq){
						// no word as more that 2 consecutive characters that are the same
						wordFreq.put(word, freq);
					}					
				}
				
			}
		}
	 
		br.close();
		
		PrintStream pout = new PrintStream(output);
		Set<String> wordSet = wordFreq.keySet();
		for(String w : wordSet){
			pout.println(w + ", " + wordFreq.get(w));
		}
		pout.flush();
		pout.close();
		
		System.out.println("Done cleaning");
		
	}

}
