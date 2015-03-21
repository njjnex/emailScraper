package com.scrapingon.scraper;

import java.util.List;

import com.scrapingon.fileUtils.WriteToFile;
import com.scrapingon.model.Page;

public class EmailExtractor implements Runnable {

	private Page resultPage;

	public EmailExtractor(Page resultPage) {
		this.resultPage = resultPage;
	}

	public void run() {
		try {
			PageScraper pageScraper = new PageScraperJSOUP(resultPage);
			List<String> results = pageScraper.scrapData(pageScraper.visitPage());
			if (results.size() > 0) {
				for (String email : results) {
					System.out.println("Found: " + email);
				}
				WriteToFile.getInstance().writeToFile(results);
			}
		} catch (Exception e) {
			
		}

	}

}
