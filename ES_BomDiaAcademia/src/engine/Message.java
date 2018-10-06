package engine;

import java.util.Date;

public class Message {
	
	private String from;
	private Date time;
	private String header;
	private String message;
	
	
	public Message(String f, Date d, String h, String m) {
		this.from = f;
		this.time = d;
		this.header = h;
		this.message = m;
	}


	public String getFrom() {
		return from;
	}


	public Date getTime() {
		return time;
	}


	public String getHeader() {
		return header;
	}


	public String getMessage() {
		return message;
	}
	

}
