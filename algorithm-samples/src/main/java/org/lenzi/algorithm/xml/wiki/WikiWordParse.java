package org.lenzi.algorithm.xml.wiki;

import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.WikiXMLParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Parses english wiki dumps
 * 
 * e.g., http://dumps.wikimedia.org/enwiki/latest/enwiki-20150304-pages-articles_2.xml.bz2
 * 
 * @author slenzi
 */
public class WikiWordParse {

	private String wikiFilePath = null;
	
	public static void main(String[] args) {

		//
		// parse 50 gigabyte file!
		//
		WikiWordParse parser = new WikiWordParse("F:/Downloads/wiki dump/enwiki-20150304-pages-articles_2.xml/enwiki-20150304-pages-articles_2.xml");
		
		parser.dumpTest();

	}
	
	public WikiWordParse(String f) {
		wikiFilePath = f;
	}
	
	public void genWordCount(){
		
		File wikiFile = new File(wikiFilePath);
		
		Charset charset = Charset.forName("ISO8859-1");
		
		Path inputPath = Paths.get(wikiFile.getAbsolutePath());
		
		long lineCount = 0;
		long MAX_LINES = 10000;
		
		// read words and store in tree set
		BufferedReader reader = null;
		try {
			reader = Files.newBufferedReader(inputPath, charset);
			String line, normal = null;
			while ((line = reader.readLine()) != null && lineCount < MAX_LINES) {
				System.out.println(line);
				lineCount++;
			}
		} catch (MalformedInputException e){
			System.err.println("MalformedInputException. " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException. " + e.getMessage());
		} finally{
			try {
				reader.close();
			} catch (IOException e) {

			}
		}		
		
	}
	
	public void dumpTest(){
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new Runnable() {			
			
		    public void run() {
		    	
				
				File wikiFile = new File(wikiFilePath);
				File output = new File(wikiFile.getParent() + File.separator + "out2.txt");
				PrintStream pout = null;
				try {
					pout = new PrintStream(output);
				} catch (FileNotFoundException e1) {
					System.err.println("Error opening print steam for " + output.getAbsolutePath());
				}		    	

				try {
		            IArticleFilter handler = new ArticleTextFilter(pout);
		            WikiXMLParser wxp = new WikiXMLParser(wikiFilePath, handler);
		            wxp.parse();
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
				
				pout.close();
		    	
		    }
		});
		

		//executorService.shutdown();
		
	}

}
