package com.bh25034.app;

import java.io.IOException;

import com.bh25034.scanning.impl.ScanningImpl;
import com.bh25034.scraping.impl.ScrapingImpl;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScanningImpl scanner = new ScanningImpl("https://www.theguardian.com/uk", 300000);
		
		try {
			
			pl("Started getting all links for the targeted website");
			scanner.scanLinks(null);
			pl("Finished getting all links for the targeted website");
			pl("Scraping media now");
			ScrapingImpl scraper = new ScrapingImpl(scanner.getLinksMap(), "", 300000);
			scraper.scrapeFileTypes();
			pl("Scraped media");
			
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void pl(String s) { System.out.println(s); }
	
}
