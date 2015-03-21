package com.scrapingon.model;

import java.util.regex.Pattern;

public class EmailPage extends Page {
	private final Pattern extractionPattern = Pattern.compile("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");

	public Pattern getExtractionPattern() {
		return extractionPattern;
	}
	
}
