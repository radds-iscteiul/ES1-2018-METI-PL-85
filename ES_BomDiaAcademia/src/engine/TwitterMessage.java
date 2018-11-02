package engine;

import java.util.Date;

public class TwitterMessage extends MyMessage{

	public TwitterMessage(String from, Date time, String message) {
		super(from, time, message);
	}

	@Override
	public String toString() {
		
		return this.getTime() + "--"+ this.getFrom() + ":" + this.getMessage()+ "\n";
	}
}
