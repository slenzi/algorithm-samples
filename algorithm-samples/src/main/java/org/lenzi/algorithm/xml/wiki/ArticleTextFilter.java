package org.lenzi.algorithm.xml.wiki;

import java.io.OutputStream;
import java.io.PrintStream;
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
	
	public ArticleTextFilter(PrintStream out) {
		pout = out;
	}

	@Override
	public void process(WikiArticle article, Siteinfo siteInfo) throws SAXException {
		
		//System.out.println("Id => " + article.getId());
		System.out.println("Processin Title => " + article.getTitle());
		
		String data = article.getText();
		
		data = data.replaceAll("[=]+[A-Za-z+\\s-]+[=]+", " ")
				.replaceAll("\\{\\{[A-Za-z0-9+\\s-]+\\}\\}", " ")
				.replaceAll("(?m)<ref>.+</ref>", " ")
				.replaceAll("(?m)<ref name=\"[A-Za-z0-9\\s-]+\">.+</ref>", " ")
				.replaceAll("<ref>", " <ref>");
				
		
		data = wikiModel.render(new PlainTextConverter(), data).replaceAll("\\{\\{[A-Za-z+\\s-]+\\}\\}"," ");
		data = data.trim();
		data = data.replaceAll("[^A-Za-z ]", "")
				.replaceAll("\\s+", " ");
		
		if(!data.equals("")){
			pout.println(data);
		}
		
		/*
		Matcher regexMatcher = regex.matcher(plainStr);
		while (regexMatcher.find()) {
			
			// Get sentences with 6 or more words
			String sentence = regexMatcher.group();

			System.out.println(sentence);
			
		}
		*/		
		
		//System.out.println();

	}

}
