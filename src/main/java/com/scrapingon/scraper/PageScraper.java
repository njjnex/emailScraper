package com.scrapingon.scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.scrapingon.model.Page;

public abstract class PageScraper{

	private Pattern pattern;

	public PageScraper(Page resultPage) {
		pattern = resultPage.getExtractionPattern();
	}

	public abstract Document visitPage();

	public List<String> scrapData(Document doc) {
		List<String> emailList = new ArrayList<String>();
		Elements emails = doc.getElementsMatchingOwnText(pattern);
		if (emails.size() > 0) {
			for (Element el : emails) {
				try {
					Matcher m = pattern.matcher(el.text());
					while (m.find()) {
						emailList.add(m.group());
					}
				} catch (NullPointerException e) {
					System.out.println("Cannot extract email");
				}
			}
		}
		return emailList;
	}
}
