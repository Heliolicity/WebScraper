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
	private HashMap<String, String> extensionsMap;
	
	public ScrapingImpl(HashMap<String, String> linksMap, String extension, int timeout) {
		super();
		this.linksMap = linksMap;
		this.extension = extension;
		this.timeout = timeout;
		this.extensionsMap = new HashMap<String, String>();
	}
	
	public ScrapingImpl(HashMap<String, String> linksMap, String extension) {
		super();
		this.linksMap = linksMap;
		this.extension = extension;
		this.timeout = 300000;
		this.extensionsMap = new HashMap<String, String>();
	}

	public ScrapingImpl() { 
		super(); 
		this.extensionsMap = new HashMap<String, String>();
	}
	
	public void scrapeFileTypes() throws IOException {
		
		Set set = this.linksMap.entrySet();
	    Iterator iterator = set.iterator();
	    String link = "";
	    String text = "";
	    
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
				
				/*Elements all = this.mainDocument.getAllElements();
				
				for (Element element : all) {
					
					this.printDetail(element);
					
				}*/
				
				/*Elements par = this.mainDocument.select("p");
				
				for (Element element : par) {
					
					this.printDetail(element);
					
				}*/
				
				//text = this.mainDocument.body().text();
				//pl(text);
				
				/*Elements media = this.mainDocument.select("[src]");
	        	this.printDetails(media);
				
				for (Element src : media) {
					
					text = src.attr("abs:src");
					
					if ((this.extension.equals(src.tagName())) && (! this.extensionsMap.containsKey(text))) {
						
						this.extensionsMap.put(text, text);
						//this.printDetail(src);
						
					}
					
				}
				*/
				
				Elements elements = this.mainDocument.getAllElements();
				
				for (Element element : elements) {
					
					text = element.text();
					
					if ((this.extension.equals(element.tagName())) && (! this.extensionsMap.containsKey(text))) {
						
						//pl("ADDED");
						//this.printDetail(element);
						//this.extensionsMap.put(text, text);
						this.extensionsMap.put(text, "Y");
						
					}
					
				}
				
	        }
			
			catch (HttpStatusException hse) {
				
				pl(hse.getMessage());
				pl("Error getting data for: " + link);
				
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

	public HashMap<String, String> getExtensionsMap() {
		return extensionsMap;
	}

	public void setExtensionsMap(HashMap<String, String> extensionsMap) {
		this.extensionsMap = extensionsMap;
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
				
				//pl("CSS SELECTOR: " + element.cssSelector());
				//pl("ID: " + element.id());
				//pl("NODE NAME: " + element.nodeName());
				//pl("TAG NAME: " + element.tagName());
				//pl("HTML: " + element.html());
				//pl("DATA: " + element.data());
				pl("TEXT: " + element.text());
				//pl("HREF: " + element.attr("abs:href"));
				//pl("SRC: " + element.attr("abs:src"));
				//pl("ALT: " + element.attr("alt"));
				pl();
				
			}
		
		}
		
		catch (Exception e) {
			
			pl(e.getMessage());
			
		}
		
	}
	
	private void printDetail(Element element) {
		
		try {
				
			//pl("CSS SELECTOR: " + element.cssSelector());
			//pl("ID: " + element.id());
			//pl("NODE NAME: " + element.nodeName());
			pl("TAG NAME: " + element.tagName());
			//pl("HTML: " + element.html());
			//pl("DATA: " + element.data());
			pl("TEXT: " + element.text());
			//pl("HREF: " + element.attr("abs:href"));
			//pl("SRC: " + element.attr("abs:src"));
			//pl("ALT: " + element.attr("alt"));
			//pl();
			
		}
		
		catch (Exception e) {
			
			pl(e.getMessage());
			
		}
		
	}

	private void p(String s) { System.out.print(s); }
	
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
