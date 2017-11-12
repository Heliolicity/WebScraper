package com.bh25034.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.bh25034.downloading.impl.DownloadingImpl;
import com.bh25034.scanning.impl.ScanningImpl;
import com.bh25034.scraping.impl.ScrapingImpl;
import com.bh25034.utilities.SystemFunctions;
import com.bh25934.utilities.impl.SystemFunctionsImpl;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScanningImpl scanner = new ScanningImpl("https://www.theguardian.com/uk", 300000);
		SystemFunctions sysFunc = new SystemFunctionsImpl();
		DownloadingImpl downloader = new DownloadingImpl(sysFunc);
		
		try {
			
			pl("Started getting all links for the targeted website");
			scanner.scanLinks(null);
			pl("Finished getting all links for the targeted website");
			pl("Scraping media now");
			ScrapingImpl scraper = new ScrapingImpl(scanner.getLinksMap(), "source", 300000);
			scraper.scrapeFileTypes();
			pl("Scraped media");
			HashMap<String, String> map = scraper.getExtensionsMap();
			
			Set set = map.entrySet();
		    Iterator iterator = set.iterator();
		    String link = "";
		    
		    while (iterator.hasNext()) {
	    	
		    	Map.Entry entry = (Map.Entry) iterator.next();
		    	link = (String) entry.getKey();
		    	downloader.downloadFile(link);
		    	
		    }
		    
			
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void pl(String s) { System.out.println(s); }
	
}
