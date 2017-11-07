package com.bh25034.scraping.impl;

import java.io.IOException;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScrapingImpl scraper = new ScrapingImpl("https://www.theguardian.com/uk", ".txt");
		
		try {
		
			scraper.scrapeLinks();
		
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
