package com.bh25034.scraping;

import java.io.IOException;

public interface Scraping {

	public void scrapeLinks() throws IOException;
	
	public void scrapeFileTypes(String extension);
	
}
