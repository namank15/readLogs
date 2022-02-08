package com.solution.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.solution.dao.HSQLDao;
import com.solution.models.Event;
import com.solution.models.LogEvent;

public class LogService {

	private static Logger LOGGER = Logger.getLogger(LogService.class.getName());

	public void readAndSaveLogs(String fileName) {
		Gson g = new Gson(); 
		List<LogEvent> logs = new ArrayList<LogEvent>();
		
		Map<String, Event> events = new HashMap<String,Event>();
		HSQLDao.createTable();
		Consumer<String> modify = line ->
        {
        	LogEvent logEvent = g.fromJson(line, LogEvent.class);
        	if(events.containsKey(logEvent.getId())) {
        		Event tempEvent = events.get(logEvent.getId());
        		long duration = tempEvent.getDuration() - logEvent.getTimeStamp();
        		duration= duration < 0 ? duration * -1 : duration;
        		boolean alert = duration> 4 ? true:false;
        		tempEvent.setDuration(duration);
        		tempEvent.setAlert(alert);
        		events.put(tempEvent.getId(), tempEvent);
        		HSQLDao.saveEvent(tempEvent);
        	} else {
        		Event event = new Event(logEvent.getId(), logEvent.getTimeStamp(), logEvent.getType(), logEvent.getHost(), false);
        		events.put(event.getId(), event);
        	}
        	logs.add(g.fromJson(line, LogEvent.class));
        };
        LOGGER.log(Level.INFO, "Reading Log file: "+fileName);
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(modify);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error reading file "+e.getMessage());
		}
	}

	public void readTableData() {
		try{
			HSQLDao.readTable();
		}  catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error reading Table Data "+e.getMessage());
		}
		
	} 
}
