package com.solution;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hsqldb.lib.StringUtil;

import com.solution.service.LogService;

public class Main {
	
	private static Logger LOGGER = Logger.getLogger(Main.class.getName()); 
	
	public static void main(String[] args) {
//		String fileName = "d://logfile.txt";
		String fileName = args[0];
		if(fileName!=null || !StringUtil.isEmpty(fileName)) {
			LogService logService = new LogService();
			logService.readAndSaveLogs(fileName);
			logService.readTableData();
		} else {
			LOGGER.log(Level.SEVERE, "No File Name Entered");
		}
		
	}

	
}
