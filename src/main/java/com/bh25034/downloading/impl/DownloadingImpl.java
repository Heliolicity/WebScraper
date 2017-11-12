package com.bh25034.downloading.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.bh25034.downloading.Downloading;
import com.bh25034.utilities.SystemFunctions;

public class DownloadingImpl implements Downloading {

	private String baseDir;
	private SystemFunctions sys;
	
	public DownloadingImpl(SystemFunctions sys) { 
		super();
		this.sys = sys;
		
		if (this.sys.isWindows()) this.baseDir = "C:/temp";
		else if (this.sys.isMac()) this.baseDir = "";
		else if (this.sys.isSolaris()) this.baseDir = "";
		else if (this.sys.isUnix()) this.baseDir = "";
		
	}
	
	public boolean downloadFile(String URL) {
		
		boolean downloaded = false;
		
		if ((! (this.baseDir == null)) && (! this.baseDir.equals(""))) { 
		
			try {
			
				pl(URL);
				URL url = new URL(URL);
				String fileName = url.getFile();
				//String destName = "C:/temp" + fileName.substring(fileName.lastIndexOf("/"));
				String destName = this.baseDir + fileName.substring(fileName.lastIndexOf("/"));
				pl(destName);
			 
				InputStream is = url.openStream();
				OutputStream os = new FileOutputStream(destName);
			 
				byte[] b = new byte[2048];
				int length;
			 
				while ((length = is.read(b)) != -1) 
					
					os.write(b, 0, length);
						 
				is.close();
				os.close();
				
				downloaded = true;
			
			}
			
			catch (IOException ioe) {
				
				downloaded = false;
				pl(ioe.getMessage());
				
			}
			
			catch (StringIndexOutOfBoundsException sioobe) {
				
				downloaded = false;
				pl(sioobe.getMessage());
				
			}
		
		}

		else {
			
			//Base directory not set
			downloaded = false;
			pl("Base directory not set");
			
		}
		
		return downloaded;
		
	}
	
	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	private void pl(String s) { System.out.println(s); }
	
}
