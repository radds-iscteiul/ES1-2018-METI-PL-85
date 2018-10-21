package engine;

import java.util.Date;

public class Tweet extends MyMessage{

	public Tweet(String from, Date time, String message) {
		super(from, time, message);
	}

	@Override
	public String toString() {
		
		return this.getTime() + "--"+ this.getFrom() + ":" + this.getMessage()+ "\n";
	}
}
