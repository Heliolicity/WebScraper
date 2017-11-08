package com.bh25034.scraping.impl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.SSLHandshakeException;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bh25034.scraping.Scraping;

public class ScrapingImpl implements Scraping {

	private String extension;
	private Document mainDocument;
	private int timeout = 0;
	private HashMap<String, String> linksMap;
	
	public ScrapingImpl(HashMap<String, String> linksMap, String extension, int timeout) {
		super();
		this.linksMap = linksMap;
		this.extension = extension;
		this.timeout = timeout;
	}
	
	public ScrapingImpl(HashMap<String, String> linksMap, String extension) {
		super();
		this.linksMap = linksMap;
		this.extension = extension;
		this.timeout = 300000;
	}

	public ScrapingImpl() { super(); }
	
	public void scrapeFileTypes() throws IOException {
		
		Set set = this.linksMap.entrySet();
	    Iterator iterator = set.iterator();
	    String link = "";
	    
	    while (iterator.hasNext()) {
	         
	    	Map.Entry entry = (Map.Entry) iterator.next();
	        link = (String) entry.getKey();
	        
	        try {
	        
	        	if (this.timeout <= 0) this.timeout = 300000;
				
				this.mainDocument = Jsoup.connect(link)
						.userAgent("Mozilla")
						.cookie("auth", "token")
						.timeout(this.timeout)
						.get();
				
				Elements media = this.mainDocument.select("[src]");
	        	this.printDetails(media);
				
				/*for (Element src : media) {
					
		            if (src.tagName().equals("img"))
		                pl(src.tagName() + " " + src.attr("abs:src") + " " + src.attr("width") + " " + src.attr("height") + " " + trim(src.attr("alt"), 20));
		            else
		                pl(src.tagName() + " " + src.attr("abs:src"));
		            
		        }*/
				
	        }
			
			catch (HttpStatusException hse) {
				
				pl("Error getting data for " + link);
				pl(hse.getMessage());
				hse.printStackTrace();
				
			}

			catch (UnsupportedMimeTypeException umte) {
				
				pl(umte.getMessage());
				pl("Not a valid URL: " + link);
				
			}
			
			catch (IllegalArgumentException iae) {
				
				pl(iae.getMessage());
				pl("Not a valid URL: " + link);
				
			}
	        
			catch (SSLHandshakeException she) {
				
				pl(she.getMessage());
				pl("Ignoring security handshake excetion for: " + link);
				
			}

	        
	    }
		
	}

	public HashMap<String, String> getLinksMap() {
		return linksMap;
	}

	public void setLinksMap(HashMap<String, String> linksMap) {
		this.linksMap = linksMap;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Document getMainDocument() {
		return mainDocument;
	}

	public void setMainDocument(Document mainDocument) {
		this.mainDocument = mainDocument;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	private void printDetails(Elements elements) {
		
		try {
		
			pl("ELEMENTS FOUND: " + elements.size());
			
			for (Element element : elements) {
				
				pl("CSS SELECTOR: " + element.cssSelector());
				pl("ID: " + element.id());
				pl("NODE NAME: " + element.nodeName());
				pl("TAG NAME: " + element.tagName());
				pl("HTML: " + element.html());
				pl("DATA: " + element.data());
				pl("TEXT: " + element.text());
				pl("HREF: " + element.attr("abs:href"));
				pl("SRC: " + element.attr("abs:src"));
				pl("ALT: " + element.attr("alt"));
				pl();
				
			}
		
		}
		
		catch (Exception e) {
			
			pl(e.getMessage());
			
		}
		
	}

	private void p(String s) { System.out.print(s); }
	
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
