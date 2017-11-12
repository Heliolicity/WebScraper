package com.bh25934.utilities.impl;

import com.bh25034.utilities.SystemFunctions;

public class SystemFunctionsImpl implements SystemFunctions {

	private String operatingSystem;
	
	public SystemFunctionsImpl() { 
		super();
		this.operatingSystem = System.getProperty("os.name").toLowerCase();
	}
	
	public boolean isWindows() {
		
		if (this.operatingSystem.indexOf("win") >= 0) return true;
		else return false;
		
	}
	
	public boolean isMac() {
		
		if (this.operatingSystem.indexOf("mac") >= 0) return true;
		else return false;
		
	}
	
	public boolean isSolaris() {
		
		if (this.operatingSystem.indexOf("sunos") >= 0) return true;
		else return false;
		
	}
	
	public boolean isUnix() {
		
		if (this.operatingSystem.indexOf("win") >= 0 || 
				this.operatingSystem.indexOf("nux") >= 0 || 
				this.operatingSystem.indexOf("aix") > 0) return true;
		else return false;
		
	}
	
}
