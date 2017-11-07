package com.bh25034.scraping.impl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bh25034.scraping.Scraping;

public class ScrapingImpl implements Scraping {

	private String baseURL;
	private String extension;
	private Document mainDocument;
	private int timeout = 0;
	
	public ScrapingImpl(String baseURL, String extension, int timeout) {
		super();
		this.baseURL = baseURL;
		this.extension = extension;
		this.timeout = timeout;
	}
	
	public ScrapingImpl(String baseURL, String extension) {
		super();
		this.baseURL = baseURL;
		this.extension = extension;
		this.timeout = 300000;
	}

	public ScrapingImpl() { super(); }
	
	public void scrapeLinks() throws IOException {
		
		String text = "";
		int index = 0;
		String URL = "";
		
		try {
			
			if (this.timeout <= 0) this.timeout = 300000;
			
			this.mainDocument = Jsoup.connect(this.baseURL)
					.data("query", "Java")
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(this.timeout)
					.get();
			
			/*
			Elements elements = this.mainDocument.getAllElements();
			Elements heads = this.mainDocument.select("head");
			this.printDetails(heads);
			Elements bodies = this.mainDocument.select("body");
			this.printDetails(bodies);
			Elements divs = this.mainDocument.select("div");
			this.printDetails(divs);
			*/
			
			Elements links = this.mainDocument.select("a[href]");
	        this.printDetails(links);
			
	        //Elements media = this.mainDocument.select("[src]");
	        //Elements imports = this.mainDocument.select("link[href]");

			
			
		}
		
		catch (HttpStatusException hse) {
			
			pl("Error getting data for " + URL);
			pl(hse.getMessage());
			hse.printStackTrace();
			
		}
		
	}
	
	public void scrapeFileTypes(String extension) {
		
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
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
		
		pl("ELEMENTS FOUND: " + elements.size());
		
		for (Element element : elements) {
			
			pl("CSS SELECTOR: " + element.cssSelector());
			pl("ID: " + element.id());
			pl("NODE NAME: " + element.nodeName());
			pl("TAG NAME: " + element.tagName());
			pl("HTML: " + element.html());
			pl("DATA: " + element.data());
			pl("TEXT: " + element.text());
			pl("ABS: " + element.attr("abs:href"));
			pl();
			
		}
		
	}

	private void p(String s) { System.out.print(s); }
	
	private void pl() { System.out.println(); }
	
	private void pl(String s) { System.out.println(s); }
	
}
