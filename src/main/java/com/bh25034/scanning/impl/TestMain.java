package com.bh25034.scanning.impl;

import java.io.IOException;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScanningImpl scanner = new ScanningImpl("https://www.theguardian.com/uk", 300000);

		try {
			
			scanner.scanLinks(null);
		
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
