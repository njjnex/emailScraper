package com.scrapingon.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.scrapingon.searchEngineInteractor.GoogleConnector;

public class StartJSOUPEmailCrawler {

	public static void main(String[] args) throws IOException {

		long lStartTime = System.currentTimeMillis();

		System.out.println("Google emails scraper v 1.0. For details visit http://scrapingon.com");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter search phrase: ");
		String searchPhrase = br.readLine();
		
		System.out.print("Start from page: ");
		int startPage = 0;
		try {
			startPage = Integer.parseInt(br.readLine());
			
			System.out.print("Ends on page: ");
			int endPage = 0;
			try {
				endPage = Integer.parseInt(br.readLine());
				System.out.println("Starting scraper.");
				System.out.println("Connecting to Google...");
				GoogleConnector connector = new GoogleConnector("https://www.google.com", searchPhrase, startPage, endPage);
				connector.scanResults();
			} catch (IOException ioe) {
				System.out.println("Please provide correct number!");
				System.exit(1);
			}
		} catch (IOException ioe) {
			System.out.println("Please provide correct number!");
			System.exit(1);
		}
	
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;
		System.out.println("Elapsed milliseconds: " + difference);
	}

}
