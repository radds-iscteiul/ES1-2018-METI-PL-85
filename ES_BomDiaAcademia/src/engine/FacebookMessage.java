package engine;

import java.util.Date;

public class FacebookMessage extends MyMessage {

	public FacebookMessage(String id, Date time, String message) {
		super(id, time, message);
	}
	
	

	@Override
	public String toString() {
		
		return "ID :"+this.getFrom() + "DATE :"+ this.getTime() + "Message :" + this.getMessage()+ "\n";
	}
	
}
