package com.scrapingon.searchEngineInteractor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.scrapingon.model.EmailPage;
import com.scrapingon.model.Page;
import com.scrapingon.scraper.EmailExtractor;

public class GoogleConnector {

	private HtmlUnitDriver driver;
	private int startPage, endPage;

	/**
	 * 
	 * @param url - http://google.com for google scraper 
	 * @param searchPhrase - text to insert into search engine Search field
	 * @param startPage - begin extract result from page
	 * @param endPage - end extracting on page
	 */
	public GoogleConnector(String url, String searchPhrase, int startPage, int endPage) {
		
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

		this.startPage = startPage;
		this.endPage = endPage;
		this.driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.setJavascriptEnabled(true);
		driver.get(url);
		WebElement searchField = driver.findElementByCssSelector("#lst-ib");
		searchField.sendKeys(searchPhrase);
		searchField.submit();
	}

	/**
	 * @param driver - HtmlUnitDriver
	 * @return - List of links extracted from one page
	 */
	public List<String> extractLinks(HtmlUnitDriver driver) {
		List<String> links = new ArrayList<String>();
		WebElement el = null;
		for (int i = 1; i < 5; i++) {
			int li = 1;
			while (li < 11) {
				try {
					el = driver.findElementByXPath("//*[@id=\"rso\"]/div[" + i + "]/li[" + li + "]/div/h3/a");
					System.out.println("Page" + el.getText() + " : " + el.getAttribute("href"));
					links.add(el.getAttribute("href"));

					final Page resultPage = new EmailPage();
					resultPage.setUrl(el.getAttribute("href"));
					(new Thread (new EmailExtractor(resultPage))).start();
					
					
				} catch (NoSuchElementException e) {

				}
				li++;
			}
		}
		return links;
	}

	/**
	 * Switch search engine pages  
	 */
	public void scanResults() {

		if (startPage == 1) {
			System.out.println("-------Scanning page " + driver.getCurrentUrl() + "----------");
			System.out.println(driver.getTitle() + " : " + driver.getCurrentUrl());
			extractLinks(driver);
			startPage++;
		}

		while (startPage <= endPage) {
			try {
				driver.get(driver.getCurrentUrl().replaceFirst("&start=.*", "") + "&start=" + (startPage - 1) + 0);
				System.out.println("-------Switching to next Google page ----------");
				extractLinks(driver);
				startPage++;
			} catch (NoSuchElementException e) {
				System.out.println("Cannot switch to page" + (startPage + 1));
				break;
			}
		}
	}

}
