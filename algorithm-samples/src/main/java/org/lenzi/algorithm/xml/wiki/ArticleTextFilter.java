package org.lenzi.algorithm.xml.wiki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.SAXException;

import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;
import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;

public class ArticleTextFilter implements IArticleFilter {

	// Convert to plain text
	private WikiModel wikiModel = new WikiModel("${image}", "${title}");
	private final static Pattern regex = Pattern.compile("[A-Z][\\p{L}\\w\\p{Blank},\\\"\\';\\[\\]\\(\\)-]+[\\.!]", Pattern.CANON_EQ);
	private PrintStream pout = null;
	private String data = null;
	private Map<String,Long> wordFreq = new TreeMap<String,Long>();
	private String[] words = null;
	private File outFile = null;
	private long articleProcessedCount = 1;
	private PlainTextConverter plainTextConverter = null;
	
	public ArticleTextFilter(File output) {
		
		outFile = output;
		plainTextConverter = new PlainTextConverter();
		
	}
	
	private void initOutputStream(){
		
		try {
			pout = new PrintStream(outFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error opening print steam for " + outFile.getAbsolutePath() + ". " + e.getMessage());
		}		
		
	}
	
	private void closeOutputStream(){
		
		if(pout != null){
			pout.flush();
			pout.close();
		}
		
	}

	@Override
	public void process(WikiArticle article, Siteinfo siteInfo) throws SAXException {
		
		if(article == null){
			return;
		}
		
		String data = article.getText();
		
		if(data == null){
			return;
		}
		
		// remove headers (e.g., ==History==)
		//data = data.replaceAll("[=]+[A-Za-z+\\s-]+[=]+", " ");
		data = data.replaceAll("={2,}.*={2,}", " ");
		// remove curly bracketed items (e.g., {{Redr|move|from CamelCase|up}} )
		data = data.replaceAll("\\{\\{.*\\}\\}", " ");
		// remove bracketed items (e.g., [[Assistive_technology]] )
		data = data.replaceAll("\\[\\[.*\\]\\]", " ");
		// remove <ref> data
		data = data.replaceAll("(?i)<ref[^>]*>", " ");
		data = data.replaceAll("</ref>", " ");	
		// remove #REDIRECT
		data = data.replaceAll("#REDIRECT|#redirect", " ");
	
		data = wikiModel.render(plainTextConverter, data).replaceAll("\\{\\{[A-Za-z+\\s-]+\\}\\}"," ");
		data = data.replaceAll("[^A-Za-z ]", " ").replaceAll("\\s+", " ");
		data = data.replaceAll("\\s{1}[^aAiI]\\s{1}", " ");
		data = data.trim();		
		
		// add words to frequency map
		if(!data.equals("")){
			words = data.split(" ");
			for(String w : words){
				w = w.trim().toLowerCase();
				if(wordFreq.containsKey(w)){
					wordFreq.put(w, wordFreq.get(w) + 1L);
				}else{
					wordFreq.put(w, 1L);
				}
			}
		}
		
		// every 100,000 articles, save word frequency map to file
		if( articleProcessedCount % 100000 == 0 ){

			saveCurrentWordFreq();
			
			System.out.println("\nProcessed " + articleProcessedCount + " articles...");
		
		}else if( articleProcessedCount % 1000 == 0 ){
			System.out.print(".");
		}
		
		/*
		 
		data = data.replaceAll("[=]+[A-Za-z+\\s-]+[=]+", " ")
				.replaceAll("\\{\\{[A-Za-z0-9+\\s-]+\\}\\}", " ")
				.replaceAll("(?m)<ref>.+</ref>", " ")
				.replaceAll("(?m)<ref name=\"[A-Za-z0-9\\s-]+\">.+</ref>", " ")
				.replaceAll("<ref>", " <ref>");		 
		 
		Matcher regexMatcher = regex.matcher(plainStr);
		while (regexMatcher.find()) {
			
			// Get sentences with 6 or more words
			String sentence = regexMatcher.group();

			System.out.println(sentence);
			
		}
		*/
		
		articleProcessedCount++;

	}
	
	private void saveCurrentWordFreq(){
		
		initOutputStream();
		
		Set<String> wordSet = wordFreq.keySet();
		for(String w : wordSet){
			pout.println(w + ", " + wordFreq.get(w));
		}
		
		closeOutputStream();		
		
	}

}
