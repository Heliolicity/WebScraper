package com.bh25034.scanning.impl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bh25034.scanning.Scanning;

public class ScanningImpl implements Scanning {

	private String baseURL;
	private Document mainDocument;
	private int timeout = 0;
	private List<String> links;
	private HashMap<String, String> linksMap;
	
	public ScanningImpl(String baseURL, int timeout) {
		super();
		this.baseURL = baseURL;
		this.timeout = timeout;
		this.links = new ArrayList<String>();
		this.linksMap = new HashMap<String, String>();
	}

	public ScanningImpl() { 
		super();
		this.links = new ArrayList<String>();
		this.linksMap = new HashMap<String, String>();
	}
	
	public void scanLinks(String URL) throws IOException {
	
		if (URL == null) URL = this.baseURL;
		
		try {
			
			if (this.timeout <= 0) this.timeout = 300000;
			
			this.mainDocument = Jsoup.connect(URL)
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(this.timeout)
					.get();
			
			Elements links = this.mainDocument.select("a[href]");
			String text = "";
			
			for (Element link : links) {
				
				text = link.attr("abs:href");
				//this.links.add(text);
				//pl("Link found: " + text);
				
				if (! this.linksMap.containsKey(text)) {
				
					pl("Link found: " + text);
					this.linksMap.put(text, text);
					this.scanLinks(text);
				
				}
				
			}
			
		}
		
		catch (HttpStatusException hse) {
			
			pl("Error getting data for " + URL);
			pl(hse.getMessage());
			hse.printStackTrace();
			
		}

		catch (UnsupportedMimeTypeException umte) {
			
			pl(umte.getMessage());
			pl("Not a valid URL");
			
		}
		
		catch (IllegalArgumentException iae) {
			
			pl(iae.getMessage());
			pl("Not a valid URL");
			
		}
		
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public Document getMainDocument() {
		return mainDocument;
	}

	public void setMainDocument(Document mainDocument) {
		this.mainDocument = mainDocument;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	public HashMap<String, String> getLinksMap() {
		return linksMap;
	}

	public void setLinksMap(HashMap<String, String> linksMap) {
		this.linksMap = linksMap;
	}

	private void p(String s) { System.out.print(s); }
	
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
