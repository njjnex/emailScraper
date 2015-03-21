package com.scrapingon.model;

import java.util.regex.Pattern;

public abstract class Page {
	private String url;
	private Pattern extractionPattern;	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Pattern getExtractionPattern() {
		return extractionPattern;
	}

	public void setExtractionPattern(Pattern extractionPattern) {
		this.extractionPattern = extractionPattern;
	}
	
	
}



