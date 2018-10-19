package engine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmailMessage extends MyMessage {
	
	private String to;
	private String header;
		
	
	public EmailMessage(String from, String to, String header, Date time, String message) {
		super(from, time, message);
		this.to=to;
		this.header=header;
		// TODO Auto-generated constructor stub
	}

	public String getHeader() {
		return header;
	}
	
	public String getTo() {
		return to;
	}
	
	@Override
	public String toString() {
		return "From: " +this.getFrom() + " || Subject: " + this.getHeader();
	}
			
}
