package engine;

import java.util.Date;

public abstract class MyMessage {

	private String from;
	private Date time;
	private String message;
	
	public MyMessage(String from, Date time, String message) {
		this.from= from;
		this.time=time;
		this.message=message;
	}
	
	public String getFrom() {
		return from;
	}
	
	public Date getTime() {
		return time;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
