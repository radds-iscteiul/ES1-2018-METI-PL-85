package engine;

import java.util.Date;

public class TwitterMessage extends MyMessage{

	public TwitterMessage(String from, Date time, String message) {
		super(from, time, message);
	}

	@Override
	public String toString() {
		
		return super.toString() + "| "+this.getMessage();
	}
}
