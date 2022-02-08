package com.solution.models;

public class LogEvent {
	
//	@JsonProperty("id")
	private String id;
	
//	@JsonProperty("state")
	private String state;
	
//	@JsonProperty("type")
	private String type;
	
//	@JsonProperty("host")
	private String host;
	
//	@JsonProperty("timestamp")
	private long timestamp;

	
	
	public LogEvent(String id, String state, String type, String host, long timestamp) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public long getTimeStamp() {
		return timestamp;
	}

	public void setTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "LogEvent [id=" + id + ", state=" + state + ", type=" + type + ", host=" + host + ", timeStamp="
				+ timestamp + "]";
	}
	
	
}
