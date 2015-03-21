package com.scrapingon.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.scrapingon.model.Page;

public class PageScraperJSOUP extends PageScraper{

	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36";
	private Document doc = null;
	private String url;

	public PageScraperJSOUP(Page resultPage) {
		super(resultPage);
		url = resultPage.getUrl();
	}

	public Document visitPage() {
		try {
			doc = Jsoup
					.connect(url)
					.userAgent(USER_AGENT)
					.get();
		} catch (IOException e) {
			System.out.println("Cannot create connection to: " + url);
		}
		return doc;
	}
}
